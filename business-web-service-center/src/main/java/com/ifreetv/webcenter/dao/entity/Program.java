package com.ifreetv.webcenter.dao.entity;

public class Program {
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
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getParseDate() {
        return parseDate;
    }

    public void setParseDate(String parseDate) {
        this.parseDate = parseDate == null ? null : parseDate.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}