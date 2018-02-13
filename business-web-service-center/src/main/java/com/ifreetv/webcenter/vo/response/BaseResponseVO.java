package com.ifreetv.webcenter.vo.response;

/*******************************
 * @Title BaseResponseVO
 * @package com.ifreetv.webcenter.vo.response
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 9:42
 * @version
 ********************************/
public class BaseResponseVO {
    /**
     * 是否成功的标识,success-成功;failed-失败
     */
    private String result;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 错误代码 如：1001-登录失效
     */
    private String code;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
