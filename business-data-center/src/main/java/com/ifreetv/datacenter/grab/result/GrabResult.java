package com.ifreetv.datacenter.grab.result;

import java.util.List;

/*******************************
 * @Title: GrabResult
 * @package com.ifreetv.datacenter.grab.result
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 15:00
 * @version
 ********************************/
public class GrabResult<T> {
    /**
     * 描述
     */
    private String desc;

    /**
     * 抓取是否成功
     */
    private Boolean result;

    /**
     * 抓取用时
     */
    private Long grabTime = 0L;


    /**
     * 解析用时
     */
    private Long parseTime = 0L;

    /**
     * 抓取到的原始data数据
     */
    private String grabData;

    /**
     * 抓取结果
     */
    private List<T> grabList;

    /**
     * 是否使用缓存
     */
    private Boolean isUseCahce;

    /**
     * 抓取是否成功
     */
    private Boolean isGrabSuccess;

    /**
     * 解析是否成功
     */
    private Boolean isParseSuccess;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Long getGrabTime() {
        return grabTime;
    }

    public void setGrabTime(Long grabTime) {
        this.grabTime = grabTime;
    }

    public Long getParseTime() {
        return parseTime;
    }

    public void setParseTime(Long parseTime) {
        this.parseTime = parseTime;
    }

    public String getGrabData() {
        return grabData;
    }

    public void setGrabData(String grabData) {
        this.grabData = grabData;
    }

    public List<T> getGrabList() {
        return grabList;
    }

    public void setGrabList(List<T> grabList) {
        this.grabList = grabList;
    }

    public Boolean getUseCahce() {
        return isUseCahce;
    }

    public void setUseCahce(Boolean useCahce) {
        isUseCahce = useCahce;
    }

    public Boolean getGrabSuccess() {
        return isGrabSuccess;
    }

    public void setGrabSuccess(Boolean grabSuccess) {
        isGrabSuccess = grabSuccess;
    }

    public Boolean getParseSuccess() {
        return isParseSuccess;
    }

    public void setParseSuccess(Boolean parseSuccess) {
        isParseSuccess = parseSuccess;
    }

    @Override
    public String toString() {
        return "GrabResult{" +
                "desc='" + desc + '\'' +
                ", result=" + result +
                ", grabTime=" + grabTime +
                ", parseTime=" + parseTime +
                ", grabData='" + grabData + '\'' +
                ", grabList=" + grabList +
                ", isUseCahce=" + isUseCahce +
                ", isGrabSuccess=" + isGrabSuccess +
                ", isParseSuccess=" + isParseSuccess +
                '}';
    }
}
