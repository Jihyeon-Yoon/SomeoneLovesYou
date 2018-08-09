package com.ssy.graduationwork.someonelovesyou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
    //itemList를 복사할 List 선언
    ArrayList<ListViewItemForFriend>  arraylist;
    //검색창
    EditText search;

    //감정설정
    ImageButton ib_emoticon,statusBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_friend, null);
        statusBtn=rootView.findViewById(R.id.ib_edit);


        // 어레이리스트 새로 생성.
        itemList = new ArrayList<ListViewItemForFriend>();

        //Adapter 생성
        adapter = new ListViewAdapterForFriend(itemList);
        search=rootView.findViewById(R.id.editSearch);
        search.requestFocus();


        InputMethodManager imm = (InputMethodManager)getContext().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.showSoftInput(search,InputMethodManager.SHOW_FORCED);




        // 리스트뷰 참조 및 adpater 달기
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);



        try {
            InputStream is  = getResources().openRawResource(R.raw.friend);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String[] temp = new String[3];
            // temp 0:폰번호 1: 이미지이름, 2: 이름, 3: 상태
            while ((br.readLine()) != null) { // 책 사이의 개행(빈 줄 하나) 읽기

                for(int i = 0; i < 3; i++) {
                    temp[i] = br.readLine();
                }

                String phone = temp[0];

                //이미지이름==폰번호
                String personImgName = "ph" + temp[0];

                int personImgResId = getResources().getIdentifier(personImgName, "drawable", "com.ssy.graduationwork.someonelovesyou");

                String name = temp[1];
                String state = temp[2];

                adapter.addItem(phone, personImgResId, name, state);

            }

            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        Comparator<ListViewItemForFriend> nameAsc = new Comparator<ListViewItemForFriend>() {
            public int compare(ListViewItemForFriend item1, ListViewItemForFriend item2) {
                return item1.getName().compareTo(item2.getName());
            }
        };
        Collections.sort(itemList, nameAsc);
        adapter.notifyDataSetChanged();

        arraylist = new ArrayList<ListViewItemForFriend>();
        arraylist.addAll(itemList);

        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PopupActivity.class);
                startActivity(intent);

    }
});






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

    @Override
    public void onPause() {
        super.onPause();
        search.clearFocus();

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
                if (arraylist.get(i).getName().toLowerCase().contains(charText))
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



