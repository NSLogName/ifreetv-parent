package com.ifreetv.webcenter.vo.response.iptv;

import java.util.List;

/*******************************
 * @Title ClassifyResponseVO
 * @package com.ifreetv.webcenter.vo.response.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 9:50
 * @version
 ********************************/
public class ClassifyResponseVO {
    /**
     * 频道列表
     */
    private List<String> classifyList;

    public List<String> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<String> classifyList) {
        this.classifyList = classifyList;
    }

    @Override
    public String toString() {
        return "ClassifyResponseVO{" +
                "classifyList=" + classifyList +
                '}';
    }
}
