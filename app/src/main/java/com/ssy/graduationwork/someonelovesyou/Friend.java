package com.ssy.graduationwork.someonelovesyou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by YJH on 2018-07-17.
 */

public class Friend extends Fragment {
    ArrayList<ListViewItemForFriend>  list;
    private List<String> listTitle;//새거
    ListView listView;
    ListViewAdapterForFriend adapter;
    private ArrayList<String> arraylistTitle;

    ArrayList<ListViewItemForFriend> itemList;
    ArrayList<ListViewItemForFriend> itemList2;
     EditText editSearch;        // 검색어를 입력할 Input 창


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_friend, null);

        // 어레이리스트 새로 생성.
        itemList = new ArrayList<ListViewItemForFriend>();

        //Adapter 생성
        adapter = new ListViewAdapterForFriend(itemList);

        itemList2 = new ArrayList<ListViewItemForFriend>();
        itemList2.addAll(itemList);

        list=new ArrayList<ListViewItemForFriend>();


        // 리스트뷰 참조 및 adpater 달기
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //새로만듦
        editSearch=rootView.findViewById(R.id.editSearch);

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

        listTitle = new ArrayList<String>();
        settingSearchList();


        arraylistTitle = new ArrayList<String>();
        arraylistTitle.addAll(listTitle);


        // 이름순으로 정렬 (default)
        Comparator<ListViewItemForFriend> nameAsc = new Comparator<ListViewItemForFriend>() {
            public int compare(ListViewItemForFriend item1, ListViewItemForFriend item2) {
                return item1.getName().compareTo(item2.getName());
            }
        };
        Collections.sort(itemList, nameAsc);
        adapter.notifyDataSetChanged();




        //새로만듦
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text=editSearch.getText().toString();
                search(text);

            }
        });



        return rootView;
    }

    private void settingSearchList(){
        for(int i=0; i<itemList.size(); i++){
            //list.add(itemList.get(i).getName()+ " / " + itemList.get(i).getState());
            listTitle.add(itemList.get(i).getName());
        }
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
           list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(itemList2);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < itemList2.size(); i++)
            {
// arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (itemList2.get(i).getName().toLowerCase().contains(charText))
                {
// 검색된 데이터를 리스트에 추가한다.
                    list.add(itemList2.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }



}
