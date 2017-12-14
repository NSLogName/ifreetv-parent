package com.ifreetv.proxyvisitor;

import com.ifreetv.baseutils.utils.LoggerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*******************************
 * @Title: ProxyInfoManager
 * @package com.ifreetv.proxyvisitor
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/12 10:55
 * @version
 ********************************/
public class ProxyInfoManager {
    /**
     * 所有可用的代理--主
     */
    private static List<ProxyInfo> allValidateProxyInfoList;

    /**
     * 所有可用的代理--备
     */
    private static List<ProxyInfo> allValidateProxyInfoListBak;

    static {
        allValidateProxyInfoList = new ArrayList<>();
        allValidateProxyInfoListBak = new ArrayList<>();
    }


    public static List<ProxyInfo> getAllValidateProxyInfoList() {
        return allValidateProxyInfoList.size() < 1 ? allValidateProxyInfoListBak : allValidateProxyInfoList;
    }

    private static List<ProxyInfo> getAllValidateProxyInfoListBak() {
        return allValidateProxyInfoListBak;
    }

    /**
     * 有效代理ip备份列表
     */
    public static void bak() {
        allValidateProxyInfoListBak = null;
        allValidateProxyInfoListBak = new ArrayList<>();
        allValidateProxyInfoListBak.addAll(allValidateProxyInfoList);
    }

    /**
     * 获取一个可用的代理
     */
    public static ProxyInfo getProxyInfo() {
        int i;
        Random random = new Random();
        try {
            if (getAllValidateProxyInfoList().size() > 0) {
                i = random.nextInt(getAllValidateProxyInfoList().size());
                return getAllValidateProxyInfoList().get(i);
            } else if (getAllValidateProxyInfoListBak().size() > 0) {
                i = random.nextInt(getAllValidateProxyInfoListBak().size());
                return getAllValidateProxyInfoListBak().get(i);
            }
        } catch (final Exception e) {
            LoggerUtils.getLogger().error("获取有效代理地址出错：" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * 移除失效的
     *
     * @param proxyInfo 代理对象
     */
    public static void remove(ProxyInfo proxyInfo) {
        allValidateProxyInfoList.remove(proxyInfo);
        if (allValidateProxyInfoListBak != null) {
            allValidateProxyInfoListBak.remove(proxyInfo);
        }
    }

    /**
     * 清理所有
     */
    public static void clear() {
        allValidateProxyInfoList.clear();
    }

    /**
     * 替换现有的代理
     *
     * @param proxyInfoList 代理列表
     */
    public static void refresh(final List<ProxyInfo> proxyInfoList) {
        allValidateProxyInfoList = proxyInfoList;
    }
}
