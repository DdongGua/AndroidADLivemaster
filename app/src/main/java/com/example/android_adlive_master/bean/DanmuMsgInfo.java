package com.example.android_adlive_master.bean;

/**
 * Created by 亮亮 on 2018/1/17.
 */  //弹幕bean

public class DanmuMsgInfo {
    String avatar;
    int grade;
    String nickname;
    String text;
    String adouID;

    public DanmuMsgInfo() {
    }

    public DanmuMsgInfo(String avatar, int grade, String nickname, String text, String adouID) {
        this.avatar = avatar;
        this.grade = grade;
        this.nickname = nickname;
        this.text = text;
        this.adouID = adouID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAdouID() {
        return adouID;
    }

    public void setAdouID(String adouID) {
        this.adouID = adouID;
    }
}
