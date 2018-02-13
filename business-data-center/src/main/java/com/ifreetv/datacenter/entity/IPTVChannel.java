package com.ifreetv.datacenter.entity;

/*******************************
 * @Title IPTVChannel
 * @package com.ifreetv.datacenter.entity
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 13:47
 * @version
 ********************************/
public class IPTVChannel {
    /**
     * <pre>
     *
     * 表字段 : t_freetv_program.id
     * </pre>
     *
     */
    private Integer id;

    /**
     * <pre>
     * 节目类型
     * 表字段 : t_freetv_program.classify_name
     * </pre>
     *
     */
    private String classifyName;

    /**
     * <pre>
     * 频道名
     * 表字段 : t_freetv_program.channel_name
     * </pre>
     *
     */
    private String channelName;

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

    /**
     * <pre>
     * 抓取日期
     * 表字段 : t_freetv_program.parse_date
     * </pre>
     *
     */
    private String parseDate;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_freetv_program.update_time
     * </pre>
     *
     */
    private String updateTime;

    /**
     * <pre>
     * 备注
     * 表字段 : t_freetv_program.memo
     * </pre>
     *
     */
    private String memo;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_freetv_program.create_time
     * </pre>
     *
     */
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

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

    public String getParseDate() {
        return parseDate;
    }

    public void setParseDate(String parseDate) {
        this.parseDate = parseDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "IPTVChannel{" +
                "id=" + id +
                ", classifyName='" + classifyName + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channelLineUrl='" + channelLineUrl + '\'' +
                ", channelMenu='" + channelMenu + '\'' +
                ", parseDate='" + parseDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", memo='" + memo + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
