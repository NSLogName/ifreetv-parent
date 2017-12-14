package com.ifreetv.proxyvisitor;

import com.machinepublishers.jbrowserdriver.ProxyConfig;

/*******************************
 * @Title: ProxyInfo
 * @package com.ifreetv.proxyvisitor
 * @Description:TODO
 *
 * @author XCTY
 * @date 2017/12/12 10:54
 * @version
 ********************************/
public class ProxyInfo {
    /**
     * 代理IP地址
     */
    private String address;

    /**
     * 代理端口号
     */
    private Integer port;

    /**
     * 有参的构造方法
     * @param address 代理IP地址
     * @param port 代理端口号
     */
    public ProxyInfo(String address, Integer port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ProxyInfo{" +
                "address='" + address + '\'' +
                ", port=" + port +
                '}';
    }
}
