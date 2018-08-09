package com.ssy.graduationwork.someonelovesyou;

/**
 * Created by 윤지현 on 2018-07-18.
 */

public class ListViewItemForHeart {

    // temp 0:폰번호 1: 폰번호(이미지이름), 2: 이름, 3: 날짜, 4: 오전/후, 5: 시간
    private String phone;
    private int personImgResId;
    private String name;
    private String date;
    private String ampm;
    private String time;

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPersonImgResId(int personImgResId) { this.personImgResId = personImgResId; }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setAMPM(String ampm) {
        this.ampm = ampm;
    }
    public void setTime(String time) {
        this.time = time;
    }

    // temp 0:폰번호 1: 폰번호(이미지이름), 2: 이름, 3: 날짜, 4: 오전/후, 5: 시간
    public String getPhone() {
        return phone;
    }
    public int getPersonImgResId() {
        return personImgResId;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getAMPM() {
        return ampm;
    }
    public String getTime() {
        return time;
    }
}
