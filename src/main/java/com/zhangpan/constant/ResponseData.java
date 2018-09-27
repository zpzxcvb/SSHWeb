package com.zhangpan.constant;

public class ResponseData {
	
	private String code;//状态码
	
	private String msg;//返回信息
	
	private Object data;//数据集合json对象

	public ResponseData() {
    }

    public ResponseData(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }
	
}
