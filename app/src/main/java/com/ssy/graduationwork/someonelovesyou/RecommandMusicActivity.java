package com.ssy.graduationwork.someonelovesyou;

import android.app.ActionBar;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by YJH on 2018-08-02.
 */

public class RecommandMusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand_music);

        // 타이틀바 가운데 정렬
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        // TabLayout + ViewPager
        // TabLayout을 쓰기 위해서 build.gradle(Module: app)의 dependencies에 디자인 라이브러리 추가
        TabLayout tabLayout = findViewById(R.id.music_tab);
        ViewPager viewPager = findViewById(R.id.music_viewPager);


        /*Fragment[] arrFragments = new Fragment[6];
        arrFragments[0] = new Fragment();

        *//*arrFragments[1] = new MusicEmotion2();
        arrFragments[2] = new MusicEmotion3();
        arrFragments[3] = new MusicEmotion4();
        arrFragments[4] = new MusicEmotion5();
        arrFragments[5] = new MusicEmotion6();*//*

        // 프래그먼트 안의 프래그먼트라서 getChidFragmentManager를 써야한다.
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), arrFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);*/


    }

    /*private class MyPagerAdapter extends FragmentPagerAdapter {
        private Fragment[] arrFragments;

        public MyPagerAdapter(FragmentManager fm, Fragment[] arrFragments) {
            super(fm);
            this.arrFragments = arrFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return arrFragments[position];
        }

        @Override
        public int getCount() {
            return arrFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "낙천";
                case 1:
                    return "사랑";
                case 2:
                    return "공포";
                case 3:
                    return "반감";
                case 4:
                    return "회한";
                case 5:
                    return "분노";
                default:
                    return "";
            }
        }
    }*/


}
