package com.ssy.graduationwork.someonelovesyou;

/**
 * Created by 윤지현 on 2018-07-18.
 */

public class ListViewItemForHeart {

    // temp 0: 폰번호(이미지이름), 1: 이름, 2: 날짜, 3: 오전/후, 4: 시간
    private int personImgResId;
    private String name;
    private String date;
    private String ampm;
    private String time;


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

    // temp 0: 폰번호(이미지이름), 1: 이름, 2: 날짜, 3: 오전/후, 4: 시간
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
