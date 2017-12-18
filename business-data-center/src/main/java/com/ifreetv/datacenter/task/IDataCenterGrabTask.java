package com.ifreetv.datacenter.task;

/*******************************   
 * @Title: IDataCenterGrabTask
 * @package com.ifreetv.datacenter.task
 * @Description:
 * 抓取线程接口，只有一个方法，每个独立的抓取实现该接口
 * 在数据中心，只要是涉及到抓取的线程都需要实现该接口
 *
 * @author XCTY
 * @date 2017/12/18 13:57
 * @version
 ********************************/
public interface IDataCenterGrabTask {

    /**
     * 处理所有的抓取，入库等事务
     */
    void execute();
}
