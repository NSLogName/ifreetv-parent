package com.ifreetv.webcenter.dao.entity;

public class ProgramWithBLOBs extends Program {
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
        this.channelLineUrl = channelLineUrl == null ? null : channelLineUrl.trim();
    }

    public String getChannelMenu() {
        return channelMenu;
    }

    public void setChannelMenu(String channelMenu) {
        this.channelMenu = channelMenu == null ? null : channelMenu.trim();
    }
}