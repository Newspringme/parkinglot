package com.cnzk.pojo;
import java.util.List;

/**
 * @author su
 * layui表格的实体类
 */

public class LayuiData
{
	private int code;
	private String msg = "";
	private int count;
	private List<?> data;
	private String files;

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}


}
