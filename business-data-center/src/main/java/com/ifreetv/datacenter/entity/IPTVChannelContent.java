package com.ifreetv.datacenter.entity;

/*******************************
 * @Title IPTVChannelContent
 * @package com.ifreetv.datacenter.entity
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 14:56
 * @version
 ********************************/
public class IPTVChannelContent {
    /**
     * <pre>
     * 频道线路
     * 表字段 : t_freetv_program.channel_line_url
     * </pre>
     *
     */
    private String channelLineUrl;

    /**
     * <pre>
     * 频道菜单
     * 表字段 : t_freetv_program.channel_menu
     * </pre>
     *
     */
    private String channelMenu;

    public String getChannelLineUrl() {
        return channelLineUrl;
    }

    public void setChannelLineUrl(String channelLineUrl) {
        this.channelLineUrl = channelLineUrl;
    }

    public String getChannelMenu() {
        return channelMenu;
    }

    public void setChannelMenu(String channelMenu) {
        this.channelMenu = channelMenu;
    }

    @Override
    public String toString() {
        return "IPTVChannelContent{" +
                "channelLineUrl='" + channelLineUrl + '\'' +
                ", channelMenu='" + channelMenu + '\'' +
                '}';
    }
}
