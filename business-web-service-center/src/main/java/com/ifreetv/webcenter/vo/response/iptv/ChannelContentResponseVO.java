package com.ifreetv.webcenter.vo.response.iptv;

import com.ifreetv.webcenter.entity.ChannelLine;

import java.util.List;

/*******************************
 * @Title ChannelContentResponseVO
 * @package com.ifreetv.webcenter.vo.response.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 9:54
 * @version
 ********************************/
public class ChannelContentResponseVO {
    /**
     * 频道线路
     */
    private List<ChannelLine> channelContentList;

    /**
     * 频道节目预告
     */
    private List<String> channelMenuList;

    public List<ChannelLine> getChannelContentList() {
        return channelContentList;
    }

    public void setChannelContentList(List<ChannelLine> channelContentList) {
        this.channelContentList = channelContentList;
    }

    public List<String> getChannelMenuList() {
        return channelMenuList;
    }

    public void setChannelMenuList(List<String> channelMenuList) {
        this.channelMenuList = channelMenuList;
    }

    @Override
    public String toString() {
        return "ChannelContentResponseVO{" +
                "channelContentList=" + channelContentList +
                ", channelMenuList=" + channelMenuList +
                '}';
    }
}
