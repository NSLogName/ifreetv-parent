package com.ifreetv.datacenter.entity;

/*******************************
 * @Title IPTVClassify
 * @package com.ifreetv.datacenter.entity
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/8 13:16
 * @version
 ********************************/
public class IPTVClassify {
    /**
     * 频道类别名
     */
    private String classifyName;

    /**
     * 频道类别url
     */
    private String classifyUrl;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifyUrl() {
        return classifyUrl;
    }

    public void setClassifyUrl(String classifyUrl) {
        this.classifyUrl = classifyUrl;
    }

    @Override
    public String toString() {
        return "IPTVClassify{" +
                "classifyName='" + classifyName + '\'' +
                ", classifyUrl='" + classifyUrl + '\'' +
                '}';
    }
}
