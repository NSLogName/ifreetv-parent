package com.ifreetv.datacenter.grab.framework;

/*******************************   
 * @Title: AbstractGrabProcessor
 * @package com.ifreetv.datacenter.grab.framework
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 14:56
 * @version
 ********************************/
public abstract class AbstractGrabProcessor<T> implements IGrabProcessor<T> {
    protected String desc;

    /**
     * 获取描述信息
     * @return 描述信息
     */
    @Override
    public String getDesc() {
        return "《抓取定时任务:" + desc + "[" + getClass().getName() + "]》";
    }
}
