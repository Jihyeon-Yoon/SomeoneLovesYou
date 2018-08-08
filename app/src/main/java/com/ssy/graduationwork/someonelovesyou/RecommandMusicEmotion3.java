package com.ssy.graduationwork.someonelovesyou;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecommandMusicEmotion3 extends Fragment {

    Button titleSortBtn, singerSortBtn;
    ListView listView;
    ListViewAdapterForMusic adapter;

    ArrayList<ListViewItemForMusic> itemList;
    ArrayList<ListViewItemForMusic>  arraylist;
    //검색창
    EditText search;


    public RecommandMusicEmotion3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_recommand_music_emotion3, null);

        // 어레이리스트 새로 생성.
        itemList = new ArrayList<ListViewItemForMusic>();

        //Adapter 생성
        adapter = new ListViewAdapterForMusic(itemList);
        search=rootView.findViewById(R.id.editSearch);

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

        arraylist = new  ArrayList<ListViewItemForMusic>();
        arraylist.addAll(itemList);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String searchText=search.getText().toString();
                search(searchText);


            }
        });

        return rootView;
    }

    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        itemList.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            itemList.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {

                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).getTitle().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    itemList.add(arraylist.get(i));

                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }



}
