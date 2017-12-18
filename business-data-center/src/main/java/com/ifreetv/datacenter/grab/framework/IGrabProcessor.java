package com.ifreetv.datacenter.grab.framework;

import com.ifreetv.datacenter.grab.result.GrabProcessorResult;

/*******************************
 * @Title: IGrabProcessor
 * @package com.ifreetv.datacenter.grab.framework
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 14:39
 * @version
 ********************************/
public interface IGrabProcessor<T> {
    /**
     * 处理接口
     */
    GrabProcessorResult<T> execute();

    /**
     * 获取处理器执行的间隔时间
     * @return 处理器执行的间隔时间
     */
    Long getExeIntervalTime();

    /**
     * 获取描述信息
     * @return 描述信息
     */
    String getDesc();

    /**
     * 获取可接受最大运行时间
     * @return 可接受最大运行时间
     */
    Long getMaxExecTime();
}
