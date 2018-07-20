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
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by YJH on 2018-07-17.
 */

public class Friend extends Fragment {

    ListView listView;
    ListViewAdapterForFriend adapter;

    ArrayList<ListViewItemForFriend> itemList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_friend, null);

        // 어레이리스트 새로 생성.
        itemList = new ArrayList<ListViewItemForFriend>();

        //Adapter 생성
        adapter = new ListViewAdapterForFriend(itemList);

        // 리스트뷰 참조 및 adpater 달기
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        try {
            InputStream is  = getResources().openRawResource(R.raw.friend);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String[] temp = new String[3];
            // temp 0: 폰번호(이미지이름), 1: 이름, 2: 상태
            while ((br.readLine()) != null) { // 책 사이의 개행(빈 줄 하나) 읽기

                for(int i = 0; i < 3; i++) {
                    temp[i] = br.readLine();
                }

                //이미지이름==폰번호
                String personImgName = "ph" + temp[0];

                int personImgResId = getResources().getIdentifier(personImgName, "drawable", "com.ssy.graduationwork.someonelovesyou");

                String name = temp[1];
                String state = temp[2];

                adapter.addItem(personImgResId, name, state);

            }

            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }


        // 이름순으로 정렬 (default)
        Comparator<ListViewItemForFriend> nameAsc = new Comparator<ListViewItemForFriend>() {
            public int compare(ListViewItemForFriend item1, ListViewItemForFriend item2) {
                return item1.getName().compareTo(item2.getName());
            }
        };
        Collections.sort(itemList, nameAsc);
        adapter.notifyDataSetChanged();


        return rootView;
    }

}
