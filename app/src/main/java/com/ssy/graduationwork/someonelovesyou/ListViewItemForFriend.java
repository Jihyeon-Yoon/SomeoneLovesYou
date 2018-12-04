package com.ssy.graduationwork.someonelovesyou;

/**
 * Created by 윤지현 on 2018-07-18.
 */

public class ListViewItemForFriend {

    // temp 0:폰번호 1: 이미지이름, 2: 이름, 3: 상태
    private String phone;
    private int personImgResId;
    private String name;
    private String state;
    private String result;

    public void setPhone(String phone) { this.phone = phone; }
    public void setPersonImgResId(int personImgResId) { this.personImgResId = personImgResId; }
    public void setName(String name) {
        this.name = name;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setResult(String result) {
        this.result = result;
    }


    // temp 0:폰번호 1: 이미지이름, 2: 이름, 3: 상태
    public String getPhone() {
        return phone;
    }
    public int getPersonImgResId() {
        return personImgResId;
    }
    public String getName() {
        return name;
    }
    public String getState() {
        return state;
    }
    public String getResult() {
        return result;
    }


}
