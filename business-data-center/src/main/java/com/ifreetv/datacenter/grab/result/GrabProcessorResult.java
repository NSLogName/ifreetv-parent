package com.ifreetv.datacenter.grab.result;

/*******************************   
 * @Title: GrabProcessorResult
 * @package com.ifreetv.datacenter.grab.result
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 15:02
 * @version
 ********************************/
public class GrabProcessorResult<T> {
    /**
     * 抓取结果
     */
    private GroupGrabResult<T> groupGrabResult;

    /**
     * 抓取后续处理结果
     */
    private GrabFollowUpResult grabFollowUpResult;

    public GroupGrabResult<T> getGroupGrabResult() {
        return groupGrabResult;
    }

    public void setGroupGrabResult(GroupGrabResult<T> groupGrabResult) {
        this.groupGrabResult = groupGrabResult;
    }

    public GrabFollowUpResult getGrabFollowUpResult() {
        return grabFollowUpResult;
    }

    public void setGrabFollowUpResult(GrabFollowUpResult grabFollowUpResult) {
        this.grabFollowUpResult = grabFollowUpResult;
    }

    @Override
    public String toString() {
        return "GrabProcessorResult{" +
                "groupGrabResult=" + groupGrabResult +
                ", grabFollowUpResult=" + grabFollowUpResult +
                '}';
    }

    /**
     * 是否最终成功
     * 1.如果使用缓存，直接成功；
     * 2.抓取结果与后续处理都成功，认为成功；
     * @return
     */
    public Boolean isProcessSuccesss(){
        Boolean result = grabFollowUpResult.getFollowUpSuccess();
        return result;
    }
}
