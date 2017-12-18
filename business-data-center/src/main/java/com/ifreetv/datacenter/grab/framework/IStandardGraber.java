package com.ifreetv.datacenter.grab.framework;

import com.ifreetv.datacenter.grab.result.GroupGrabResult;

/*******************************
 * @Title: IStandardGraber
 * @package com.ifreetv.datacenter.grab.framework
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 14:39
 * @version
 ********************************/
public interface IStandardGraber<T> {
    /**
     * 抓取的结果
     */
    GroupGrabResult<T> execute();

    /**
     * 获取描述信息
     * @return
     */
    String getDesc();
}
