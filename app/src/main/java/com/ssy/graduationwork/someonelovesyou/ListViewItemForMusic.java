package com.ssy.graduationwork.someonelovesyou;

/**
 * Created by 윤지현 on 2018-07-19.
 */

public class ListViewItemForMusic {

    // temp 0: 제목, 1: 가수, 2: 링크
    private String title;
    private String singer;
    private String linkAddress;

    public void setTitle(String title) {
        this.title = title;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    // temp 0: 제목, 1: 가수, 2: 링크

    public String getTitle() {
        return title;
    }
    public String getSinger() {
        return singer;
    }
    public String getLinkAddress() {
        return linkAddress;
    }

}
