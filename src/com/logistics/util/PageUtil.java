package com.logistics.util;

public class PageUtil {
	private int start = 0;
	private int count = 5;
	private int pageNun = 0; //页数
	private int nun = 1;//条目总数
	private int nowPage = 1;//当前页数
	
	public PageUtil() {
		
	}
	/**
	 * num是条目总数，count是每页条目数，nowPage是当前页
	 * @param num
	 * @param count
	 * @param nowPage
	 */
	public PageUtil(int nun,int count,int nowPage) {
		this.nun = nun;
		this.count = count;
		this.nowPage = nowPage;
		this.pageNun = (this.nun%count==0)?(this.nun%count):(this.nun%count+1);
		this.start = (this.nowPage-1)*count;
		this.start = (this.start<0)?0:this.start;
	}
	public int getNun() {
		return nun;
	}
	public void setNun(int nun) {
		this.nun = nun;
		
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		setNun(this.nun);
	}
	public int getPageNun() {
		return pageNun;
	}
	public void setPageNun(int pageNun) {
		this.pageNun = pageNun;
	}

	
}
