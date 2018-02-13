package com.ifreetv.webcenter.vo.request.iptv;

/*******************************
 * @Title ChannelRequest
 * @package com.ifreetv.webcenter.vo.request.iptv
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 9:48
 * @version
 ********************************/
public class ChannelRequestVO {
    /**
     * 节目列别名
     */
    private String classifyName;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    @Override
    public String toString() {
        return "ChannelRequest{" +
                "classifyName='" + classifyName + '\'' +
                '}';
    }
}
