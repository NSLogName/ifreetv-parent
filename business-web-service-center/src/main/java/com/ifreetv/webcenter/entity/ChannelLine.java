package com.ifreetv.webcenter.entity;

/*******************************
 * @Title ChannelLine
 * @package com.ifreetv.webcenter.entity
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 9:53
 * @version
 ********************************/
public class ChannelLine {
    /**
     * 线路名称
     */
    private String lineName;

    /**
     * 线路地址
     */
    private String lineUrl;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineUrl() {
        return lineUrl;
    }

    public void setLineUrl(String lineUrl) {
        this.lineUrl = lineUrl;
    }

    @Override
    public String toString() {
        return "ChannelLine{" +
                "lineName='" + lineName + '\'' +
                ", lineUrl='" + lineUrl + '\'' +
                '}';
    }
}
