package com.ifreetv.webcenter.vo.response.iptv;

import java.util.List;

/*******************************
 * @Title ChannelResponseVO
 * @package com.ifreetv.webcenter.vo.response.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 9:50
 * @version
 ********************************/
public class ChannelResponseVO {
    /**
     * 频道列表
     */
    private List<String> channelList;

    public List<String> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<String> channelList) {
        this.channelList = channelList;
    }

    @Override
    public String toString() {
        return "ChannelRequest{" +
                "channelList=" + channelList +
                '}';
    }
}
