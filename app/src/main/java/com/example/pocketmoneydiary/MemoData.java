package com.example.pocketmoneydiary;

public class MemoData {
    private String tv_today;
    private String tv_patch;
    private String tv_delete;
    private String tv_content;

    public MemoData(String tv_today, String tv_patch, String tv_delete, String tv_content){
        this.tv_today = tv_today;
        this.tv_patch = tv_patch;
        this.tv_delete = tv_delete;
        this.tv_content = tv_content;
    }
    public String getTv_today() {
        return tv_today;
    }



    public String getTv_patch() {
        return tv_patch;
    }



    public String getTv_delete() {
        return tv_delete;

    }

    public String getTv_content() {
        return tv_content;
    }
}
