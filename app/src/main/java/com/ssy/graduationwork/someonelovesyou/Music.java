package com.ssy.graduationwork.someonelovesyou;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by YJH on 2018-07-17.
 */

public class Music extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_music, null);

        // TabLayout + ViewPager
        // TabLayout을 쓰기 위해서 build.gradle(Module: app)의 dependencies에 디자인 라이브러리 추가
        TabLayout tabLayout = rootView.findViewById(R.id.music_tab);
        ViewPager viewPager = rootView.findViewById(R.id.music_viewPager);

        Fragment[] arrFragments = new Fragment[4];
        arrFragments[0] = new MusicEmotion1();
        arrFragments[1] = new MusicEmotion2();
        arrFragments[2] = new MusicEmotion3();
        arrFragments[3] = new MusicEmotion4();

        // 프래그먼트 안의 프래그먼트라서 getChidFragmentManager를 써야한다.
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager(), arrFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
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
