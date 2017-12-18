package com.ifreetv.datacenter.vo;


public class ChildThreadVo {
	/**
	 * 子线程执行结果
	 */
	private boolean exeResult;

	/**
	 * 子线程类名
	 */
	private String childThreadName;

	/**
	 * 子线程执行时间，单位:ms
	 */
	private long exeTime;

	/**
	 * 子线程设置的间隔执行时间 ，单位:ms
	 */
	private long exeIntervalTime;
	
	/**
	 * 线程执行最长时间
	 * 如果超过这个时间，认为线程出现问题
	 */
	private Long maxProcessTime = null;

    public boolean isExeResult() {
        return exeResult;
    }

    public void setExeResult(boolean exeResult) {
        this.exeResult = exeResult;
    }

    public String getChildThreadName() {
        return childThreadName;
    }

    public void setChildThreadName(String childThreadName) {
        this.childThreadName = childThreadName;
    }

    public long getExeTime() {
        return exeTime;
    }

    public void setExeTime(long exeTime) {
        this.exeTime = exeTime;
    }

    public long getExeIntervalTime() {
        return exeIntervalTime;
    }

    public void setExeIntervalTime(long exeIntervalTime) {
        this.exeIntervalTime = exeIntervalTime;
    }

    public Long getMaxProcessTime() {
        if(maxProcessTime == null){
            return exeIntervalTime * 10;
        }else{
            return maxProcessTime;
        }
    }

    public void setMaxProcessTime(Long maxProcessTime) {
        this.maxProcessTime = maxProcessTime;
    }

    @Override
    public String toString() {
        return "ChildThreadVo{" +
                "exeResult=" + exeResult +
                ", childThreadName='" + childThreadName + '\'' +
                ", exeTime=" + exeTime +
                ", exeIntervalTime=" + exeIntervalTime +
                ", maxProcessTime=" + maxProcessTime +
                '}';
    }
}
