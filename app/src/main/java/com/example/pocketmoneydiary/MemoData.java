package com.example.pocketmoneydiary;

public class MemoData {
    private String tv_today;
    private String tv_content;

    public MemoData(String tv_today, String tv_content){
        this.tv_today = tv_today;
        this.tv_content = tv_content;
    }
    public String getTv_today() {
        return tv_today;
    }
    public String getTv_content() {
        return tv_content;
    }
    public String setTv_today() {
        return tv_today;
    }
    public String setTv_content() {
        return tv_content;
    }
}
