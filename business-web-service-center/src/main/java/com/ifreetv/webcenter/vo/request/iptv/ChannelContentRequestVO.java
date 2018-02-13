package com.ifreetv.webcenter.vo.request.iptv;

/*******************************
 * @Title ChannelContentRequestVO
 * @package com.ifreetv.webcenter.vo.request.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 9:52
 * @version
 ********************************/
public class ChannelContentRequestVO {
    /**
     * 频道名
     */
    private String channelName;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return "ChannelContentRequestVO{" +
                "channelName='" + channelName + '\'' +
                '}';
    }
}
