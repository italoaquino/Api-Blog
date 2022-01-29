package com.blog.blog.exception;

public class StandardError {

    private Integer status;
    private Long timestamp;
    private String message;

    public StandardError(){
    }

    public StandardError(Integer status, Long  timestamp, String message ){
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
