package com.ssy.graduationwork.someonelovesyou;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by YJH on 2018-07-17.
 */

public class Music extends Fragment {

    Context context;
    SharedPreferences sh_Pref;
    String userEmotionString;

    ImageView emoticon;
    TextView userEmotion;
    TextView recommendTitle;
    TextView recommendSinger;
    ImageButton youtubeBtn;
    String emotionResult;
    String title;
    String singer;
    String linkAddress;
    int count=0;

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

        emoticon = rootView.findViewById(R.id.iv_emoticon);
        userEmotion = rootView.findViewById(R.id.tv_emotion);
        recommendTitle = rootView.findViewById(R.id.tv_recommendTitle );
        recommendSinger = rootView.findViewById(R.id.tv_recommendSinger);
        youtubeBtn = rootView.findViewById(R.id.ib_youtube);
        getSharedPreferenceUserInfo();
        recommendMusic();

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

    public void getSharedPreferenceUserInfo() {
        context = getActivity();
        sh_Pref = context.getSharedPreferences("userInfo", MODE_PRIVATE);
        if(sh_Pref != null && sh_Pref.contains("userEmotion")) {
            userEmotionString = sh_Pref.getString("userEmotion", "noEmotion");
            userEmotion.setText(userEmotionString);
        }
    }

    private void recommendMusic() {
        InputStream is=null;
        try {

            if(userEmotionString.equals("행복")){
                is  = getResources().openRawResource(R.raw.music_e1);
            }
            else if(userEmotionString.equals("불안")){
                is  = getResources().openRawResource(R.raw.music_e2);
            }
            else if(userEmotionString.equals("슬픔")){
                is  = getResources().openRawResource(R.raw.music_e3);
            }
            else if(userEmotionString.equals("평안")){
                is  = getResources().openRawResource(R.raw.music_e4);
            }

            //서버에 감정 업데이트되면 올릴것
            is  = getResources().openRawResource(R.raw.music_e3);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String[] temp = new String[3];
            int randomNum=(int)(Math.random()*100);//랜덤 추천

            // temp 0: 제목, 1: 가수, 2: 링크
            while ((br.readLine()) != null) { // 음악 사이의 개행(빈 줄 하나) 읽기
                count++;
                for(int i = 0; i < 3; i++) {
                    temp[i] = br.readLine();
                }
                if(count==randomNum) {
                    title = temp[0];
                    singer = temp[1];
                    linkAddress = temp[2];
                }

            }
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        recommendTitle.setText(title);
        recommendSinger.setText(singer);


        if(userEmotionString.equals("행복")) {
            emoticon.setImageResource(R.drawable.emoticon_happy);
            userEmotion.setText("행복");

        } else if(userEmotionString.equals("불안")) {
            emoticon.setImageResource(R.drawable.emoticon_confused);
            userEmotion.setText("불안");


        } else if(userEmotionString.equals("슬픔")) {
            emoticon.setImageResource(R.drawable.emoticon_remorse);
            userEmotion.setText("슬픔");

        } else if(userEmotionString.equals("평온")) {
            emoticon.setImageResource(R.drawable.emoticon_smiling);
            userEmotion.setText("평온");

        } else {
            emoticon.setImageResource(R.drawable.emoticon_silent);

        }


        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(linkAddress));

                v.getContext().startActivity(intent);

            }
        });
    }


}
