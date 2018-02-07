package com.ifreetv.proxyvisitor;

/*******************************
 * @Title PlatformUtil
 * @package com.ifreetv.proxyvisitor
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/7 14:04
 * @version
 ********************************/
public enum PlatformUtil {
    /**
     * iPhone平台
     */
    IPHONE("Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1"),

    /**
     * Android平台
     */
    ANDROID("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Mobile Safari/537.36"),

    /**
     * Windows平台
     */
    WINDOWS("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36"),

    /**
     * Mac平台
     */
    MAC("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/537.36");

    /**
     * 自定义变量
     */
    private String value;

    /**
     * 自定义普通成员方法
     * @return 普通成员变量
     */
    public String getValue() {
        return value;
    }

    /**
     * 构造方法
     * @param value 初始值
     */
    PlatformUtil(String value) {
        this.value = value;
    }
}
