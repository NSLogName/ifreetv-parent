package com.ifreetv.datacenter.grab.result;

import java.util.List;

/*******************************
 * @Title: GroupGrabResult
 * @package com.ifreetv.datacenter.grab.result
 * @Description:
 * 复杂抓取结果描述，针对每一个单页有独立的描述信息
 *
 * @author XCTY
 * @date 2017/12/18 14:59
 * @version
 ********************************/
public class GroupGrabResult<T> {
    /**
     * 抓取过程中所有的信息
     */
    private List<GrabResult<T>> middleGrabResult;

    /**
     * 最终结果
     */
    private GrabResult<T> grabResult;

    /**
     * 结果是否有效
     */
    private Boolean result;

    public List<GrabResult<T>> getMiddleGrabResult() {
        return middleGrabResult;
    }

    public void setMiddleGrabResult(List<GrabResult<T>> middleGrabResult) {
        this.middleGrabResult = middleGrabResult;
    }

    public GrabResult<T> getGrabResult() {
        return grabResult;
    }

    public void setGrabResult(GrabResult<T> grabResult) {
        this.grabResult = grabResult;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "GroupGrabResult{" +
                "middleGrabResult=" + middleGrabResult +
                ", grabResult=" + grabResult +
                ", result=" + result +
                '}';
    }
}
