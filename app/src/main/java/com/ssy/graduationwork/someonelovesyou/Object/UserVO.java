package com.ssy.graduationwork.someonelovesyou.Object;

public class UserVO {
    private String userid;
    private String userpwd;
    private String state;
    private String emotion;
    private String username;

    public UserVO(String userid, String userpwd, String state, String emotion, String username){
        this.userid = userid;
        this.userpwd = userpwd;
        this.state = state;
        this.emotion = emotion;
        this.username = username;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUserpwd() {
        return userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getEmotion() {
        return emotion;
    }
    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}