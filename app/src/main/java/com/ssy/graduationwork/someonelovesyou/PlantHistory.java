package com.ssy.graduationwork.someonelovesyou;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlantHistory extends Fragment {

    ListView listView;
    ListViewAdapterForHistory adapter;

    ArrayList<ListViewItemForHistory> itemList;


    public PlantHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_plant_history, null);

        // 어레이리스트 새로 생성.
        itemList = new ArrayList<ListViewItemForHistory>();

        //Adapter 생성
        adapter = new ListViewAdapterForHistory(itemList);

        // 리스트뷰 참조 및 adpater 달기
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        try {
            InputStream is  = getResources().openRawResource(R.raw.history);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String[] temp = new String[4];
            // temp 0: 순서, 1:꽃종류, 2:시작날짜 3:끝날짜
            while ((br.readLine()) != null) { // 책 사이의 개행(빈 줄 하나) 읽기

                for(int i = 0; i < 4; i++) {
                    temp[i] = br.readLine();
                }

                String order = temp[0];
                String flowerImgName = temp[1];

                int flowerImgResId = getResources().getIdentifier(flowerImgName, "drawable", "com.ssy.graduationwork.someonelovesyou");

                String startDate = temp[2];
                String endDate = temp[3];

                adapter.addItem(flowerImgResId, startDate, endDate);

            }

            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }




        return rootView;
    }

}
