package com.ifreetv.baseutils.utils;


/**
 * DaemonThread<br/>
 * 描述:不退出的线程
 *
 * @author huangwei
 * @since 2016-1-6<br/>
 */
public class NotQuitThread extends Thread {

    private final Object obj = new Object();

    @Override
    public void run() {
        synchronized (this.obj) {
            try {
                this.obj.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
