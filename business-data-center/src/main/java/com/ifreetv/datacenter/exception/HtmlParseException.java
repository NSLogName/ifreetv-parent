package com.ifreetv.datacenter.exception;

/*******************************   
 * @Title: HTMLParseException
 * @package com.ifreetv.datacenter.exception
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 14:44
 * @version
 ********************************/
public class HtmlParseException extends RuntimeException {

    public HtmlParseException(){
        super();
    }

    public HtmlParseException(String msg){
        super(msg);
    }

    public HtmlParseException(Throwable t){
        super(t);
    }

    public HtmlParseException(String msg, Throwable t){
        super(msg, t);
    }
}
