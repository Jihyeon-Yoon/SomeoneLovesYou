package com.ssy.graduationwork.someonelovesyou;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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


        Fragment[] arrFragments = new Fragment[4];
        arrFragments[0] = new RecommandMusicEmotion1();
        arrFragments[1] = new RecommandMusicEmotion2();
        arrFragments[2] = new RecommandMusicEmotion3();
        arrFragments[3] = new RecommandMusicEmotion4();

        // 프래그먼트 안의 프래그먼트라서 getChidFragmentManager를 써야한다.
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), arrFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
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
                    return "행복";
                case 1:
                    return "불안";
                case 2:
                    return "슬픔";
                case 3:
                    return "평온";
                default:
                    return "";
            }
        }
    }


}
