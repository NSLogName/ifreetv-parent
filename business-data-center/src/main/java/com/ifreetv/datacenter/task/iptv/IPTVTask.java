package com.ifreetv.datacenter.task.iptv;

import com.ifreetv.datacenter.entity.IPTVChannel;
import com.ifreetv.datacenter.grab.framework.IGrabProcessor;
import com.ifreetv.datacenter.task.AbstractDataCenterGrabTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*******************************
 * @Title IPTVTask
 * @package com.ifreetv.datacenter.task.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 16:03
 * @version
 ********************************/
@Component("IPTVTask")
public class IPTVTask extends AbstractDataCenterGrabTask<IPTVChannel> {
    @Autowired
    @Qualifier("IPTVGrabProcessor")
    IGrabProcessor<IPTVChannel> iptvGrabProcessor;

    @Override
    public void processData() {

    }

    @Override
    public IGrabProcessor<IPTVChannel> getGrabProcessor() {
        return iptvGrabProcessor;
    }
}
