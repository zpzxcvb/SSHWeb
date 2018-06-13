package com.zhangpan.constant;

public class ResponseResult {
	
	private String code;//状态码
	
	private String message;//返回信息
	
	private String result;//数据集合字符串

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseResult [code=" + code + ", message=" + message + ", result=" + result + "]";
    }
	
}
