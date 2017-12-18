package com.ifreetv.datacenter.grab.framework;

/*******************************   
 * @Title: AbstractStandardGraber
 * @package com.ifreetv.datacenter.grab.framework
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 14:57
 * @version
 ********************************/
public abstract class AbstractStandardGraber<T> implements IStandardGraber<T>{
    String desc;

    /**
     * 获取描述信息
     * @return 描述信息
     */
    @Override
    public String getDesc() {
        return "《复杂任务抓取:" + desc + "[" + getClass().getName() + "]》";
    }
}
