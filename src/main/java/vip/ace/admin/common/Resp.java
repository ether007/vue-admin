package vip.ace.admin.common;

public class Resp {

    public final static int SUCCESS = 1;

    public final static int ERROR = 0;


    private int code;
    private String message;

    private Object data;

    public Resp() {
        this.code = SUCCESS;
        this.message = "success";
    }

    public Resp(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Resp(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
