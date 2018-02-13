package com.ifreetv.datacenter.grab.framework;

import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.datacenter.grab.result.GrabResult;
import com.ifreetv.proxyvisitor.PlatformUtil;
import com.ifreetv.proxyvisitor.ProxyVisitor;
import com.ifreetv.proxyvisitor.Visitor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/*******************************
 * @Title: AbstractGraber
 * @package com.ifreetv.datacenter.grab.framework
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/18 14:56
 * @version
 ********************************/
public abstract class AbstractGraber<T> implements IGraber<T>{
    /**
     * 获取解析后的结果
     * 1.判断是否有缓存，如果有的话不需要抓取；
     * 2.如果没有缓存完成抓取指定html内容；
     *   调用相应的解析器，完成抓取数据的解析；
     * @return 解析后的结果
     */
    @Override
    public GrabResult<T> execute(){
        List<T> cacheList = getCacheList();
        GrabResult<T> grabResult = new GrabResult<T>();
        grabResult.setDesc(getDesc());
        if(cacheList == null){
            grabResult = grabData();
            if(grabResult.getGrabSuccess()){
                parseData(grabResult);
                grabResult.setResult(grabResult.getGrabSuccess() && grabResult.getParseSuccess());
            }else{
                grabResult.setResult(false);
            }
        }else{
            grabResult.setGrabList(cacheList);
            grabResult.setUseCahce(true);
            grabResult.setResult(true);
        }
        return grabResult;
    }


    /**
     * 解析数据
     * @param result 解析后数据
     */
    public void parseData(GrabResult<T> result){
        List<T> grabList = null;
        try{
            Long startTime = System.currentTimeMillis();
            grabList = getParse().parseData(result.getGrabData());
            Long endTime = System.currentTimeMillis();
            //为减少内存占用，解析后就删除记录，不保存中间抓取到的内容
            result.setGrabData(null);
            result.setGrabList(grabList);
            result.setParseTime(endTime - startTime);
            result.setParseSuccess(true);
        }catch(Exception e){
            LoggerUtils.getLogger().error("解析数据发生错误" + e.getMessage(), e);
            result.setParseSuccess(false);
        }
    }


    /**
     * 抓取网页内容的结果
     * @return 网页内容的结果
     */
    public GrabResult<T> grabData(){
        GrabResult<T> result = new GrabResult<T>();
        try{

            Long startTime = System.currentTimeMillis();
            String responseBody = grabDataStr();
            Long endTime = System.currentTimeMillis();
            //如果未找到数据
            if(responseBody == null || responseBody.trim().equals("")){
                LoggerUtils.getLogger().warn(getDesc() + ":未抓取到数据");
                result.setGrabSuccess(false);
            }else{
                result.setGrabSuccess(true);
                result.setGrabData(responseBody);
            }
            Long grabTime = endTime - startTime;
            result.setGrabTime(grabTime);
            return result;
        }catch(Exception e){
            LoggerUtils.getLogger().error("抓取网页内容" + getDesc() + "出现异常" + e.getMessage(), e);
            result.setGrabSuccess(false);
            return result;
        }
    }

    /**
     * 实际抓取到的html
     * 如果是强制使用代理，就直接使用代理方式抓取
     * 如果不是强制使用代理，则使用直连方式抓取，如果没有抓到，则再使用代理方式抓取
     * @return 抓取到的html
     */
    public String grabDataStr(){
        if(isProxy()){
            return ProxyVisitor.getHtmlSource(getUrlAddress(),chosePlatform());
        }else{
            String responseBody;
            try{
                responseBody = Visitor.getHtmlSource(getUrlAddress(), null, chosePlatform());
            }catch(Exception e){
//                responseBody = ProxyVisitor.getHtmlSource(getUrlAddress(),chosePlatform());
                responseBody = Visitor.getHtmlSource(getUrlAddress(), null, chosePlatform());
            }

            if(StringUtils.isBlank(responseBody)){
//                responseBody = ProxyVisitor.getHtmlSource(getUrlAddress(),chosePlatform());
                responseBody = Visitor.getHtmlSource(getUrlAddress(), null, chosePlatform());
            }
            return responseBody;
        }
    }

    /**
     * 是否使用代理
     * @return 是否使用代理
     */
    public Boolean isProxy(){
        return false;
    }

    /**
     * 使用哪种平台抓取
     * @return 平台
     */
    public PlatformUtil chosePlatform() {
        return PlatformUtil.WINDOWS;
    }
}
