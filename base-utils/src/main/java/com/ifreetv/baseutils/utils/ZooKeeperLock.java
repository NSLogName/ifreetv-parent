package com.ifreetv.baseutils.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 基于ZooKeeper的分布式锁，一个线程只能获取一把锁，不能在锁释放前获取另一把异名锁
 * <p>
 * Created by Cowboy on 2016/4/6.
 */
public class ZooKeeperLock {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String connectString;
    private int baseSleepTimeMs;
    private int maxRetries;
    private int timeoutMs;

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public void setTimeoutMs(int timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    private ThreadLocal<CuratorFramework> client = new ThreadLocal<CuratorFramework>();
    private ThreadLocal<InterProcessMutex> lock = new ThreadLocal<InterProcessMutex>();

    /**
     * 获取默认时间配置的分布式锁
     *
     * @param path 锁名，ZK的节点路径
     * @return true，获取成功；false，获取失败
     */
    public boolean lock(String path) {
        return lock(path, timeoutMs);
    }

    /**
     * 获取指定时间配置的分布式锁
     *
     * @param path      锁名，ZK的节点路径
     * @param timeoutMs 等待超时时间，单位毫秒
     * @return true，获取成功；false，获取失败
     */
    public boolean lock(String path, long timeoutMs) {
        InterProcessMutex mutex = lock.get();
        if (mutex == null) {
            CuratorFramework curatorFramework = client.get();
            if (curatorFramework == null) {
                curatorFramework = CuratorFrameworkFactory.newClient(connectString, new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries));
                client.set(curatorFramework);
                curatorFramework.start();
            }
            mutex = new InterProcessMutex(curatorFramework, path);
            lock.set(mutex);
        }
        try {
            return mutex.acquire(timeoutMs, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 释放锁
     */
    public void unlock() {
        InterProcessMutex mutex = lock.get();
        if (mutex != null) {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }
        lock.set(null);
        CuratorFramework curatorFramework = client.get();
        if (curatorFramework != null) {
            curatorFramework.close();
        }
        client.set(null);
    }
}
