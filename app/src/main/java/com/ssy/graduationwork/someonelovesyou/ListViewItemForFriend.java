package com.ssy.graduationwork.someonelovesyou;

/**
 * Created by 윤지현 on 2018-07-18.
 */

public class ListViewItemForFriend {

    // temp 0: 폰번호(이미지이름), 1: 이름, 2: 상태
    private int personImgResId;
    private String name;
    private String state;

    public void setPersonImgResId(int personImgResId) { this.personImgResId = personImgResId; }
    public void setName(String name) {
        this.name = name;
    }
    public void setState(String state) {
        this.state = state;
    }


    // temp 0: 폰번호(이미지이름), 1: 이름, 2: 상태
    public int getPersonImgResId() {
        return personImgResId;
    }
    public String getName() {
        return name;
    }
    public String getState() {
        return state;
    }


}
