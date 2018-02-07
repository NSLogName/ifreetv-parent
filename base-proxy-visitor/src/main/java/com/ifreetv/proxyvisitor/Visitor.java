package com.ifreetv.proxyvisitor;

import com.ifreetv.baseutils.utils.LoggerUtils;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*******************************
 * @Title: Visitor
 * @package com.ifreetv.proxyvisitor
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/12 10:56
 * @version
 ********************************/
public class Visitor {
    private static final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>(10);

    public static String getHtmlSource(String url) {
        return getHtmlSource(url, null, null);
    }

    /**
     * 不使用代理获取网页内容
     *
     * @param url 待抓取网页地址
     * @return 抓取的网页内容
     */
    public static String getHtmlSource(String url, PlatformUtil platform) {
        return getHtmlSource(url, null, platform);
    }

    /**
     * 获取网页内容
     *
     * @param url       待抓取网页地址
     * @param proxyInfo 代理
     * @return 抓取的网页内容
     */
    public static String getHtmlSource(String url, ProxyInfo proxyInfo, PlatformUtil platform) {
        String htmlSource = "";
        try {
            // 判断url是否为空
            if (StringUtils.isBlank(url)) {
                LoggerUtils.getLogger().info("待抓取的网页地址为空！");
                return htmlSource;
            }

            // 默认为Windows平台
            if (platform == null) {
                platform = PlatformUtil.WINDOWS;
            }

            Call call = createOkHttpClient(proxyInfo).newCall(createRequest(url, platform));
            Response response = call.execute();
            if (response == null) {
                return htmlSource;
            } else {
                ResponseBody responseBody = response.body();
                return responseBody != null ? responseBody.string() : htmlSource;
            }
        } catch (Exception e) {
            LoggerUtils.getLogger().error("抓取网页内容出错！" + e.getMessage(), e);
        }
        return htmlSource;
    }

    /**
     * 创建OkHttpClient
     *
     * @param proxyInfo 自定义代理
     * @return OkHttpClient对象
     */
    private static OkHttpClient createOkHttpClient(ProxyInfo proxyInfo) {
        return new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .connectTimeout(Config.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Config.READ_TIMEOUT, TimeUnit.SECONDS)
                .proxy(createProxy(proxyInfo))
                .build();
    }

    /**
     * 创建Proxy
     *
     * @param proxyInfo 自定义代理
     * @return Proxy对象
     */
    private static Proxy createProxy(ProxyInfo proxyInfo) {
        Proxy proxy = Proxy.NO_PROXY;
        if (proxyInfo != null) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyInfo.getAddress(), proxyInfo.getPort()));
        }
        return proxy;
    }

    /**
     * 创建Request
     *
     * @param url      抓取网页地址
     * @param platform 模拟平台类型
     * @return Request对象
     */
    private static Request createRequest(String url, PlatformUtil platform) {
        return new Request.Builder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("User-Agent", platform.getValue())
                .url(url)
                .build();
    }
}
