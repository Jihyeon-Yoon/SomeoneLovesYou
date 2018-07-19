package com.ssy.graduationwork.someonelovesyou;

/**
 * Created by 윤지현 on 2018-07-18.
 */

public class ListViewItemForHistory {

    // temp (0: 순서), 1:꽃종류, 2:시작날짜 3:끝날짜
    private int flowerImgResId;
    private String startDate;
    private String endDate;

    public void setFlowerImgResId(int flowerImgResId) { this.flowerImgResId = flowerImgResId; }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // temp (0: 순서), 1:꽃종류, 2:시작날짜 3:끝날짜
    public int getFlowerImgResId() {
        return flowerImgResId;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }

}
