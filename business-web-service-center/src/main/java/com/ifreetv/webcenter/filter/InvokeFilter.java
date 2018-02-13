/**
 * InvokeFilter.java
 *
 * @author huangwei
 * @since 2016-11-2
 * 描述：
 */
package com.ifreetv.webcenter.filter;

import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*******************************
 * @Title InvokeFilter
 * @package com.ifreetv.webcenter.controller
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 10:43
 * @version
 ********************************/
public class InvokeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String queryStr = req.getRequestURI();
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long endTime = System.currentTimeMillis();
        long execTime = endTime - startTime;
        if (execTime > 100) {
            LoggerFactory.getLogger(InvokeFilter.class).info("[" + queryStr + "]" + "*执行时长:*[" + execTime + "]毫秒*参数*|||"
                    + InvokeParamAndResponse.getParams() + "|||结果|||"
                    + InvokeParamAndResponse.getResponse());
        } else {
            LoggerFactory.getLogger(InvokeFilter.class).info("[" + queryStr + "]" + "*执行时长:*[" + execTime + "]毫秒");
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}
