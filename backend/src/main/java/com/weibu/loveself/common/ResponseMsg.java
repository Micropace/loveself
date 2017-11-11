package com.weibu.loveself.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * HTTP回复Json信息格式
 * {
 *     "status": "success",
 *     "message": "",
 *     "data": {}
 * }
 */
public class ResponseMsg {
    /** 返回结果 */
    @JsonProperty("status")
    private String status;
    /** 结果信息 */
    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message = null;
    /** 返回数据 */
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data = null;

    ResponseMsg(String status) {
        this.status = status;
    }

    ResponseMsg(String status, String message) {
        this.status = status;
        this.message = message;
    }

    ResponseMsg(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
