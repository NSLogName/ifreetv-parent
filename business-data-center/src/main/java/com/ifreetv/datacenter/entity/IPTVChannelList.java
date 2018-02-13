package com.ifreetv.datacenter.entity;

import java.util.List;

/*******************************
 * @Title IPTVChannelList
 * @package com.ifreetv.datacenter.entity
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 13:54
 * @version
 ********************************/
public class IPTVChannelList {
    private List<String> channelList;

    public List<String> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<String> channelList) {
        this.channelList = channelList;
    }

    @Override
    public String toString() {
        return "IPTVChannelList{" +
                "channelList=" + channelList +
                '}';
    }
}
