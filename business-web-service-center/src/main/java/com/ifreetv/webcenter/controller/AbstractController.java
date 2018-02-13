package com.ifreetv.webcenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ifreetv.baseutils.utils.GZiPUtils;
import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.webcenter.constant.Constants;
import com.ifreetv.webcenter.exception.BusinessException;
import com.ifreetv.webcenter.exception.RequestParamException;
import com.ifreetv.webcenter.service.IBaseService;
import com.ifreetv.webcenter.vo.request.AppStandardReq;
import com.ifreetv.webcenter.vo.response.BaseResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*******************************
 * @Title AbstractController
 * @package com.ifreetv.webcenter.controller
 * @Description:TODO
 *
 * @author XCTY
 * @date 2018/2/12 10:43
 * @version
 ********************************/
public abstract class AbstractController<T> {
    /** The features. */
    private SerializerFeature[] features = new SerializerFeature[] {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullNumberAsZero };

    /**
     * 是否压缩0-不压缩;1-压缩
     */
    private boolean isNeedGzip;

    /**
     *
     * @Title: requestHandle
     * @Description: 请求参数处理成对象
     * @param
     *
     * @return    返回类型
     */
    public abstract AppStandardReq<T> requestHandle(String reqJson);

    /**
     *
     * @Title: getService
     * @Description: 获取对应的service
     * @param
     *
     * @return    返回类型
     */
    public abstract IBaseService<T> getService();

    /**
     *
     * @Title: exceptionHandle
     * @Description: 基于@ExceptionHandler异常处理
     * @param request    请求
     * @param response   响应
     * @param ex  监听到的异常
     *
     */
    @ExceptionHandler
    public void exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        request.setAttribute("ex", ex);
        response.setHeader("content-type", "application:json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        BaseResponseVO respVo = new BaseResponseVO();
        respVo.setCode(Constants.RESULT_CODE_FAILED);
        respVo.setResult(Constants.RESULT_FAILED);

        // 根据不同错误转向不同页面
        if(ex instanceof BusinessException) {
            respVo.setMsg(ex.getMessage());

        }else if(ex instanceof RequestParamException) {
            respVo.setMsg(ex.getMessage());

        }else {
            respVo.setMsg(Constants.RESULT_MSG_SYSTEM_FAILED);
            LoggerUtils.getLogger().error(ex.getMessage(), ex);
        }

        //消息返回
        try {
            String result = JSON.toJSONString(respVo, features);
            response.getWriter().write(result);
            response.getWriter().close();

        } catch (IOException e) {
            LoggerUtils.getLogger().error("com.ifreetv.webcenter.controller.AbstractController.exceptionHandle 异常统一处理时I/O异常", e);
        }

    }

    /**
     *
     * @Title: writeJson
     * @Description: 发送返回结果给客户端
     * @param
     *
     * @return    返回类型
     * @throws IOException
     */
    protected void writeJson(HttpServletResponse response, Object obj, AppStandardReq<T> reqvo) throws IOException{
        try {
            String result = JSON.toJSONString(obj, features);
            LoggerUtils.getLogger().info("AbstractController类writeJson方法返回json : "+result);
            /** 处理js跨越问题  **/
            response.setHeader("content-type", "application:json;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("AAccess-Control-Allow-Headers",
                    "x-requested-with,content-type");
            // 根据需求判断是否进行gzip压缩
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);

            response.getWriter().close();
        } catch (IOException e) {
            throw new IOException("Gzip压缩时出现异常：", e);
        }
    }

    /**
     *
     * @Title: execute
     * @Description: 通用流程：
     * 1、解析request请求
     * 2、封装为对象
     * 3、调用业务处理
     * 4、返回response
     * @param
     *
     * @return    返回类型
     * @throws Exception
     */
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
        /* TODO */
    }

    /**
     * 获取请求参数 get/post
     *
     * @param request
     *            the request
     * @return 请求报文
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws RequestParamException
     *             the request param exception
     */
    @SuppressWarnings({"rawtypes"})
    protected String getRequestStr(HttpServletRequest request) throws IOException, RequestParamException {
        request.setCharacterEncoding("UTF-8");
        String reqStr;
        StringBuilder reqJson = new StringBuilder(Constants.BLANK_STR);
        Map<String, String> headerMap = getHeadersInfo(request);
        String contentType = headerMap.get("content-type");

        /**
         * wap端默认通过application/x-www-form-urlencoded发送数据
         * 数据存放在body中，可通过request.getParameter("xx")获取
         */
        if (StringUtils.contains(contentType, "application/x-www-form-urlencoded")) {
            String dataReq = request.getParameter("data");
            Map map = new HashMap<String, String>();
            map = request.getParameterMap();
            reqJson.append("{");
            if (map != null && map.size() > 0) {
                for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
                    Map.Entry element = (Map.Entry) iter.next();
                    String strKey = element.getKey().toString();
                    String[] value = (String[]) element.getValue();

                    reqJson.append(object2json(strKey));
                    reqJson.append(":");
                    if (strKey.equals("data")) {
                        reqJson.append(dataReq);
                        reqJson.append(",");
                    } else {
                        for (int i = 0; i < value.length; i++) {
                            reqJson.append(object2json(value[i]));
                            reqJson.append(",");
                        }
                    }
                }
                reqJson.setCharAt(reqJson.length() - 1, '}');
            } else {
                reqJson.append("}");
            }
            reqStr = reqJson.toString();
        }
        /**
         * app端默认通过text/plain发送数据
         * 数据放在inputstream中，可通过request.getReader()读取数据
         */
        else if (StringUtils.contains(contentType, "text/plain") ||
                StringUtils.contains(contentType, "text/xml") ||
                StringUtils.contains(contentType, "application/json")) {
            try {
                BufferedReader br = request.getReader();
                String tmp;
                while ((tmp = br.readLine()) != null) {
                    reqJson.append(tmp);
                }
            } catch (IOException e) {
                throw new RequestParamException("BufferedReader读取接口请求数据时错误");
            }
            reqStr = reqJson.toString();

            if (StringUtils.isBlank(reqStr)) {
                throw new RequestParamException("app客户端发送的请求报文为空");
            }
            LoggerUtils.getLogger().info("接收到app端一个请求,请求报文:{}", reqStr);
        }
        //其他的contentType请求不处理
        else {
            throw new RequestParamException("contentType = " + contentType + " 暂不提供解析方案，请使用application/x-www-form-urlencoded或text/plain");
        }
        return reqStr;
    }

    private static String object2json(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof String || obj instanceof Integer
                || obj instanceof Float || obj instanceof Boolean
                || obj instanceof Short || obj instanceof Double
                || obj instanceof Long || obj instanceof BigDecimal
                || obj instanceof BigInteger || obj instanceof Byte) {
            json.append("\"").append(string2json(obj.toString())).append("\"");
        }
        return json.toString();
    }

    private static String string2json(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                default:
                    if (ch >= '\u0000' && ch <= '\u001F') {
                        String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k++) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                    } else {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }


    /**
     *
     * @Title: getHeadersInfo
     * @Description: 获取请求的header
     * @param
     *
     * @return 返回类型
     */
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<?> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key.toLowerCase(), value.toLowerCase());
        }
        return map;
    }
}
