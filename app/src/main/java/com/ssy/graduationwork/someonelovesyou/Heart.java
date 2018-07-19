package com.ssy.graduationwork.someonelovesyou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by YJH on 2018-07-17.
 */

public class Heart extends Fragment {

    ListView listView;
    ListViewAdapterForHeart adapter;

    ArrayList<ListViewItemForHeart> itemList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_heart, null);

        // 어레이리스트 새로 생성.
        itemList = new ArrayList<ListViewItemForHeart>();

        //Adapter 생성
        adapter = new ListViewAdapterForHeart(itemList);

        // 리스트뷰 참조 및 adpater 달기
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        try {
            InputStream is  = getResources().openRawResource(R.raw.heart);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String[] temp = new String[5];
            // temp 0: 폰번호(이미지이름), 1: 이름, 2: 날짜, 3: 오전/후, 4: 시간
            while ((br.readLine()) != null) { // 책 사이의 개행(빈 줄 하나) 읽기

                for(int i = 0; i < 5; i++) {
                    temp[i] = br.readLine();
                }

                //이미지이름==폰번호
                String personImgName = "ph" + temp[0];

                int personImgResId = getResources().getIdentifier(personImgName, "drawable", "com.ssy.graduationwork.someonelovesyou");

                String name = temp[1] + "님이 당신을 생각하고 있어요";
                String date = temp[2];
                String ampm = temp[3];
                String time = temp[4];

                adapter.addItem(personImgResId, name, date, ampm, time);

            }

            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }


        return rootView;
    }

}
