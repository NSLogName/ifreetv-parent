package com.ifreetv.datacenter.task;

import com.alibaba.druid.support.monitor.MonitorClient;
import com.ifreetv.baseutils.utils.DatetimeUtil;
import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.datacenter.exception.HtmlParseException;
import com.ifreetv.datacenter.grab.framework.IGrabProcessor;
import com.ifreetv.datacenter.grab.result.GrabProcessorResult;
import com.ifreetv.datacenter.vo.ChildThreadVo;

/*******************************
 * @Title: AbstractDataCenterGrabTask
 * @package com.ifreetv.datacenter.task
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 14:01
 * @version
 ********************************/
public abstract class AbstractDataCenterGrabTask<T> implements IDataCenterGrabTask {
    @Override
    public void execute() {
        long startTime = System.currentTimeMillis();
        IGrabProcessor<T> grabProcessor = getGrabProcessor();
        ChildThreadVo childThreadVO = new ChildThreadVo();
        childThreadVO.setMaxProcessTime(grabProcessor.getMaxExecTime());
        childThreadVO.setExeIntervalTime(grabProcessor.getExeIntervalTime());
        childThreadVO.setChildThreadName(grabProcessor.getDesc());
        GrabProcessorResult<T> grabProcessorResult = new GrabProcessorResult<T>();
        try{
            childThreadVO.setExeResult(true);
            grabProcessorResult = grabProcessor.execute();
            childThreadVO.setExeResult(grabProcessorResult.isProcessSuccesss());
            processData();
            childThreadVO.setExeTime(System.currentTimeMillis());
            long endTime = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n=========================================================\r\n")
                    .append(grabProcessor.getDesc()).append(":完成时间:").append(DatetimeUtil.getNowStr())
                    .append("\r\n共计耗时:").append(String.valueOf((endTime - startTime))).append("毫秒");

            if(grabProcessorResult.getGrabFollowUpResult() != null){
                sb.append("\r\n后续处理耗时:").append(grabProcessorResult.getGrabFollowUpResult().getFollowUpTime()).append("毫秒");
            }
            if((endTime - startTime) > (childThreadVO.getMaxProcessTime() / 2)){
                sb.append("较慢");
            }
            sb.append("\r\n执行结果:").append(childThreadVO.isExeResult())
                    .append("\r\n=========================================================");
            LoggerUtils.getLogger().info(sb.toString());
        }catch(HtmlParseException e){
            childThreadVO.setExeResult(false);
            childThreadVO.setExeTime(System.currentTimeMillis());
            LoggerUtils.getLogger().error(grabProcessor.getDesc() + "解析html出现错误:" + e.getMessage(), e);
        }catch(Exception ex){
            childThreadVO.setExeResult(false);
            childThreadVO.setExeTime(System.currentTimeMillis());
            LoggerUtils.getLogger().error(grabProcessor.getDesc() + "抓取中出现错误:" + ex.getMessage(), ex);
        }
        NodeTask.saveExeChildThread(childThreadVO);
    }

    /**
     * 后续的数据处理
     */
    public abstract void processData();

    /**
     * 获取抓取者
     * @return
     */
    public abstract IGrabProcessor<T> getGrabProcessor();
}
