package com.example.demo.Result;

public class loginResult {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public loginResult(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "loginResult{" +
                "code=" + code +
                '}';
    }
}
