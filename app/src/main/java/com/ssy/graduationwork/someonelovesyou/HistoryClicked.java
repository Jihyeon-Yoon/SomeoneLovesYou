package com.ssy.graduationwork.someonelovesyou;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HistoryClicked extends AppCompatActivity {
    private  int img;
    TextView date_text;
    String date,start_date,end_date;

    Button timeSortBtn, nameSortBtn;
    ListView listView;
    ListViewAdapterForHeart adapter;

    ArrayList<ListViewItemForHeart> itemList;
    ArrayList<ListViewItemForHeart> arraylist;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_clicked);

        // 타이틀바 가운데 정렬
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);


        date_text=findViewById(R.id.date_text);



        Intent intent =getIntent();
        start_date=intent.getStringExtra("startDate");
        end_date=intent.getStringExtra("endDate");
        date=start_date+" ~ "+end_date;
        date_text.setText(date);
        /*ImageView profile=(ImageView)findViewById(R.id.img_prof);
        TextView name=findViewById(R.id.friend_clicked_name);
        TextView phone=findViewById(R.id.friend_clicked_phone);

        img=Integer.parseInt(intent.getStringExtra("profile"));
        profile.setImageResource(img);
        name.setText(intent.getStringExtra("name"));
        phone.setText(intent.getIntExtra("phone",1));*/




        // 어레이리스트 새로 생성.
        itemList = new ArrayList<ListViewItemForHeart>();

        //Adapter 생성
        adapter = new ListViewAdapterForHeart(itemList);

        // 리스트뷰 참조 및 adpater 달기
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        search=findViewById(R.id.editSearch);

        try {
            InputStream is  = getResources().openRawResource(R.raw.who);
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


        arraylist = new ArrayList<ListViewItemForHeart> ();
        arraylist.addAll(itemList);

        // 시간순으로 정렬하는 버튼, 버튼을 누르면 시간순으로 정렬한다.
        timeSortBtn = findViewById(R.id.btn_sort_time);
        timeSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<ListViewItemForHeart> timeAsc = new Comparator<ListViewItemForHeart>() {
                    @Override
                    public int compare(ListViewItemForHeart item1, ListViewItemForHeart item2) {
                        String time1 = "";
                        String time2 = "";
                        String temp = "";

                        //time1 얻기
                        temp = item1.getDate();
                        time1 = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);

                        if(item1.getAMPM().equals("오후")) {
                            String strBuffer="";
                            int intBuffer=0;

                            temp = item1.getTime();
                            strBuffer = temp.substring(0, 2) + temp.substring(3);
                            intBuffer = Integer.parseInt(strBuffer);
                            intBuffer += 1200;
                            strBuffer = String.valueOf(intBuffer);
                            time1 += strBuffer;

                        } else {
                            temp = item1.getTime();
                            time1 += temp.substring(0, 2) + temp.substring(3);
                        }

                        temp = "";

                        //time2 얻기
                        temp = item2.getDate();
                        time2 = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);

                        if(item2.getAMPM().equals("오후")) {
                            String strBuffer="";
                            int intBuffer=0;

                            temp = item2.getTime();
                            strBuffer = temp.substring(0, 2) + temp.substring(3);
                            intBuffer = Integer.parseInt(strBuffer);
                            intBuffer += 1200;
                            strBuffer = String.valueOf(intBuffer);
                            time2 += strBuffer;

                        } else {
                            temp = item2.getTime();
                            time2 += temp.substring(0, 2) + temp.substring(3);
                        }

                        return time1.compareTo(time2);
                    }
                };
                Collections.sort(itemList, timeAsc);
                adapter.notifyDataSetChanged();
            }
        });

        // 이름순으로 정렬하는 버튼, 버튼을 누르면 이름순(가나다순))으로 정렬한다.
        nameSortBtn = findViewById(R.id.btn_sort_name);
        nameSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<ListViewItemForHeart> nameAsc = new Comparator<ListViewItemForHeart>() {
                    @Override
                    public int compare(ListViewItemForHeart item1, ListViewItemForHeart item2) {
                        return item1.getName().compareTo(item2.getName());
                    }
                };

                Collections.sort(itemList, nameAsc);
                adapter.notifyDataSetChanged();
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
    }


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

                if (arraylist.get(i).getName().toLowerCase().substring(0,3).contains(charText))
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