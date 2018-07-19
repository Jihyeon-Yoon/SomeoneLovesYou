package com.ssy.graduationwork.someonelovesyou;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by YJH on 2018-07-19.
 */
public class MusicEmotion3 extends Fragment {

    Button titleSortBtn, singerSortBtn;
    ListView listView;
    ListViewAdapterForMusic adapter;

    ArrayList<ListViewItemForMusic> itemList;


    public MusicEmotion3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_music_emotion3, null);

        // 어레이리스트 새로 생성.
        itemList = new ArrayList<ListViewItemForMusic>();

        //Adapter 생성
        adapter = new ListViewAdapterForMusic(itemList);

        // 리스트뷰 참조 및 adpater 달기
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        try {
            InputStream is  = getResources().openRawResource(R.raw.music_e3);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String[] temp = new String[2];
            // temp 0: 제목, 1: 가수
            while ((br.readLine()) != null) { // 책 사이의 개행(빈 줄 하나) 읽기

                for(int i = 0; i < 2; i++) {
                    temp[i] = br.readLine();
                }

                String title = temp[0];
                String singer = temp[1];

                adapter.addItem(title, singer);

            }

            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        // 제목순으로 정렬하는 버튼, 버튼을 누르면 제목순(가나다순))으로 정렬한다.
        titleSortBtn = rootView.findViewById(R.id.btn_sort_title);
        titleSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<ListViewItemForMusic> nameAsc = new Comparator<ListViewItemForMusic>() {
                    @Override
                    public int compare(ListViewItemForMusic item1, ListViewItemForMusic item2) {
                        return item1.getTitle().compareTo(item2.getTitle());
                    }
                };

                Collections.sort(itemList, nameAsc);
                adapter.notifyDataSetChanged();
            }
        });

        // 가수순으로 정렬하는 버튼, 버튼을 누르면 가수순(가나다순))으로 정렬한다.
        singerSortBtn = rootView.findViewById(R.id.btn_sort_singer);
        singerSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<ListViewItemForMusic> nameAsc = new Comparator<ListViewItemForMusic>() {
                    @Override
                    public int compare(ListViewItemForMusic item1, ListViewItemForMusic item2) {
                        return item1.getSinger().compareTo(item2.getSinger());
                    }
                };

                Collections.sort(itemList, nameAsc);
                adapter.notifyDataSetChanged();
            }
        });



        return rootView;

    }

}
