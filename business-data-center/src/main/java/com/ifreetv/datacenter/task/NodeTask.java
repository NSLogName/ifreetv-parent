package com.ifreetv.datacenter.task;

import com.ifreetv.baseutils.utils.DatetimeUtil;
import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.datacenter.vo.ChildThreadVo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*******************************
 * @Title: NodeTask
 * @package com.ifreetv.datacenter.task
 * @Description:
 * 主任务，负责节点的取锁和解锁操作，监护每个子线程执行状态
 *
 * @author XCTY
 * @date 2017/12/18 15:13
 * @version
 ********************************/
@Component("nodeTask")
public class NodeTask {
    /**
     * 子线程执行状态
     */
    private static Map<String,ChildThreadVo> childTreadMap = new HashMap<String,ChildThreadVo>();

    /**
     * 线程汇报最新时间
     */
    private static Map<String, String> childThreadCurrentReportTimeMap = new HashMap<String, String>();

    /**
     * 子线程执行错误次数统计
     */
    private static Map<String, Integer> childThreadWarnMap = new HashMap<String, Integer>();

    /**
     * 记录子线程上次汇报时间
     */
    private static Map<String, String> childThreadPrevReportTimeMap = new HashMap<String, String>();

    /** 子线程执行记录 */
    public static void saveExeChildThread(ChildThreadVo vo) {
        String currentTime = DatetimeUtil.getNowStr();
        if(vo == null) {
            return;
        }
        childTreadMap.put(vo.getChildThreadName(), vo);
        if(childThreadCurrentReportTimeMap.get(vo.getChildThreadName()) != null){
            childThreadPrevReportTimeMap.put(vo.getChildThreadName(), childThreadCurrentReportTimeMap.get(vo.getChildThreadName()));
        }
        childThreadCurrentReportTimeMap.put(vo.getChildThreadName(), currentTime);
    }

    /**
     * 数据抓取主节点执行任务
     */
    public void execute() {
        try {
            Iterator<ChildThreadVo> it = childTreadMap.values().iterator();
            boolean exeWarn = false;
            Integer type = 0;
            StringBuilder errorMsg = new StringBuilder();
            while(it.hasNext()) {
                ChildThreadVo vo = it.next();
                long now = System.currentTimeMillis();
                long interval = now - vo.getExeTime();
                //如果超过最大允许时间，则认定是超时错误
                if(interval > vo.getMaxProcessTime() && vo.getExeTime() != 0L){
                    exeWarn = true;
                    type = 1;
                }
                //如果连续出现执行错误
                if(isExeManyTimesError(vo)){
                    exeWarn = true;
                    type = 2;
                }

                if(exeWarn) {
                    errorMsg.append("《").append(vo.getChildThreadName()).append("》");
                    StringBuilder sb = new StringBuilder();
                    sb.append("\r\n线程上次执行时间：").append(vo.getExeTime()).append("\r\n线程上次执行结果：").append(vo.isExeResult());
                    if(type.equals(1)){
                        sb.append("\r\n出错类型:执行时间超过最大时间。");
                    }else if(type.equals(2)){
                        sb.append("\r\n出错类型:连续多次出错。");
                    }
                    LoggerUtils.getLogger().error(sb.toString());
                    errorMsg.append(sb.toString());
                    LoggerUtils.getLogger().error("数据中心发生错误：" + errorMsg.toString());
                    childThreadWarnMap.put(vo.getChildThreadName(), 0);
                }
            }
        } catch (Exception e) {
            LoggerUtils.getLogger().error("NodeTask execute Exception："+e.getMessage(), e);
        }
    }


    /**
     * 根据线程汇报情况判断是否需要报警
     * @param vo 子线程对象
     * @return 是否需要报警
     */
    private boolean isExeManyTimesError(ChildThreadVo vo){
        String currentReportTime = childThreadCurrentReportTimeMap.get(vo.getChildThreadName());
        String prevReportTime = childThreadPrevReportTimeMap.get(vo.getChildThreadName());

        //如果运行结果正常，则清除己有的错误次数记录
        if(vo.isExeResult()){
            childThreadWarnMap.put(vo.getChildThreadName(), 0);
            return false;
        }

        //如果汇报的不是同一次，并且本次仍然是错的,错误次数+1，如果错误次数大于3，则认定错误次数过多
        //同时清理错误次数
        if(currentReportTime != null && prevReportTime != null
                && !currentReportTime.equals(prevReportTime)
                && !vo.isExeResult()){
            Integer warnCount = childThreadWarnMap.get(vo.getChildThreadName());
            if(warnCount == null){
                warnCount = 1;
            }else{
                warnCount = warnCount + 1;
            }
            childThreadWarnMap.put(vo.getChildThreadName(), warnCount);
            if(warnCount > 20){
                childThreadWarnMap.put(vo.getChildThreadName(), 0);
                return true;
            }
        }
        return false;
    }
}
