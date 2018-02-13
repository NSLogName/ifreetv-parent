package com.ifreetv.webcenter.entity;

/*******************************
 * @Title ChannelContent
 * @package com.ifreetv.webcenter.entity
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 10:29
 * @version
 ********************************/
public class ChannelContent {
    /**
     * 频道地址
     */
    private String channelUrl;

    /**
     * 频道节目菜单
     */
    private String channelMenu;

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public String getChannelMenu() {
        return channelMenu;
    }

    public void setChannelMenu(String channelMenu) {
        this.channelMenu = channelMenu;
    }

    @Override
    public String toString() {
        return "ChannelContent{" +
                "channelUrl='" + channelUrl + '\'' +
                ", channelMenu='" + channelMenu + '\'' +
                '}';
    }
}
