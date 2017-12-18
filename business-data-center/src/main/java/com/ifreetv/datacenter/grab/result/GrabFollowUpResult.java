package com.ifreetv.datacenter.grab.result;

/*******************************   
 * @Title: GrabFollowUpResult
 * @package com.ifreetv.datacenter.grab.result
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 15:04
 * @version
 ********************************/
public class GrabFollowUpResult {
    /**
     * 后续处理流程用时
     */
    private Long followUpTime = 0L;

    /**
     * 后续处理流程是否成功
     */
    private Boolean isFollowUpSuccess;

    public Long getFollowUpTime() {
        return followUpTime;
    }

    public void setFollowUpTime(Long followUpTime) {
        this.followUpTime = followUpTime;
    }

    public Boolean getFollowUpSuccess() {
        return isFollowUpSuccess;
    }

    public void setFollowUpSuccess(Boolean followUpSuccess) {
        isFollowUpSuccess = followUpSuccess;
    }

    @Override
    public String toString() {
        return "GrabFollowUpResult{" +
                "followUpTime=" + followUpTime +
                ", isFollowUpSuccess=" + isFollowUpSuccess +
                '}';
    }

    /**
     * 带参初始化方法
     * @param followUpTime 后续处理流程用时
     * @param isFollowUpSuccess 后续处理流程是否成功
     */
    public GrabFollowUpResult(Long followUpTime, Boolean isFollowUpSuccess) {
        super();
        this.followUpTime = followUpTime;
        this.isFollowUpSuccess = isFollowUpSuccess;
    }

    /**
     * 无参初始化方法
     */
    public GrabFollowUpResult() {
        super();
    }
}
