package com.ssy.graduationwork.someonelovesyou;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.usermgmt.response.model.User;
import com.ssy.graduationwork.someonelovesyou.Object.HeartDTO;
import com.ssy.graduationwork.someonelovesyou.Object.UserVO;
import com.ssy.graduationwork.someonelovesyou.Request.GetUserDTO;
import com.ssy.graduationwork.someonelovesyou.Retrofit.RetroCallback;
import com.ssy.graduationwork.someonelovesyou.Retrofit.RetroClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.SharedPreferences;
import android.widget.EditText;

import org.json.JSONObject;

import okhttp3.ResponseBody;

import static android.content.Context.MODE_PRIVATE;
import static com.ssy.graduationwork.someonelovesyou.MainActivity.LOG;

/**
 * Created by YJH on 2018-07-17.
 */

public class Friend extends Fragment {

    Context context;
    SharedPreferences sh_Pref;
    private RetroClient retroClient;
    UserVO gUser;
    ListView listView;
    ListViewAdapterForFriend adapter;
    static public int emotionNum;


    ArrayList<ListViewItemForFriend> itemList;
    //itemList를 복사할 List 선언
    ArrayList<ListViewItemForFriend>  arraylist;
    //검색창
    EditText search;


   // TextView
    TextView userName;
    public static ImageView iv_emoticon;
    public static TextView StateTv;
    String stringTemp;
    String state;
    String result;
    String result2, result3;

    String userState,userEmotion;


    ImageView profile;

    //감정설정
    ImageButton ib_emoticon,statusBtn;


   @Override
/*    public void onResume() {
       context = getActivity();
       sh_Pref = context.getSharedPreferences("userInfo", MODE_PRIVATE);//이름
       if(sh_Pref != null && sh_Pref.contains("userName")) {
           stringTemp = sh_Pref.getString("userName", "noname");
           userName.setText(stringTemp);
       }

       sh_Pref = context.getSharedPreferences("userInfo", MODE_PRIVATE);//상태메세지 & 감정결과
       if(sh_Pref != null && sh_Pref.contains("userState")&& sh_Pref.contains("userEmotion")) {
           Log.d("profile4", "성공" + stringTemp + state + result);
           state = sh_Pref.getString("userState", "nonstate");
           result = sh_Pref.getString("userEmotion", "noemotion");
           result2 = state;
           result3 = result;


           if (stringTemp.equals("이보영")) {
               StateTv.setText(state);
               if (result.equals("행복")) {
                   iv_emoticon.setImageResource(R.drawable.emoticon_happy);
               } else if (result.equals("불안")) {
                   iv_emoticon.setImageResource(R.drawable.emoticon_fear);
               } else if (result.equals("슬픔")) {
                   iv_emoticon.setImageResource(R.drawable.emoticon_disapproval);
               } else if (result.equals("평온")) {
                   iv_emoticon.setImageResource(R.drawable.emoticon_silent);
               }


           }
       }
        super.onResume();
    }*/


    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_friend, null);
        statusBtn=rootView.findViewById(R.id.ib_edit);

        iv_emoticon=rootView.findViewById(R.id.iv_emoticon);
        userName=rootView.findViewById(R.id.tv_name);
      //  userName.setText(gUser.getUsername());

        StateTv=rootView.findViewById(R.id.tv_state);

        profile=rootView.findViewById(R.id.iv_me);

        //서버에서 받은 유저 네임 보여주기
        getSharedPreferenceUserInfo();



       //서버에서 받은 유저 네임 보여주기
        getSharedPreferenceUserInfo();



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

        getSharedPreferenceUserInfo();

        try {
            InputStream is  = getResources().openRawResource(R.raw.friend);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String[] temp = new String[4];
            // temp 0:폰번호 1: 이미지이름, 2: 이름, 3: 상태  4:결과
            while ((br.readLine()) != null) { // 책 사이의 개행(빈 줄 하나) 읽기

                for(int i = 0; i < 4; i++) {
                    temp[i] = br.readLine();
                }

                String phone = temp[0];

                //이미지이름==폰번호
                String personImgName = "ph" + temp[0];

                int personImgResId = getResources().getIdentifier(personImgName, "drawable", "com.ssy.graduationwork.someonelovesyou");

                String name = temp[1];
                String state = temp[2];
                final String result=temp[3];
                if((stringTemp.equals("박보검"))&&(name.equals("박보검")))  {
                     continue;
                }else if((stringTemp.equals("이보영"))&&(name.equals("이보영"))){
                    continue;
                }
                else if((stringTemp.equals("이보영"))&&(name.equals("박보검"))){//이보영 화면에서 박보검 상태 메세지

                    adapter.addItem(phone, personImgResId, name, result2,result3);
            }
            else if((stringTemp.equals("박보검"))&&(name.equals("이보영"))){


                    adapter.addItem(phone, personImgResId, name, result2,result3);
                }

                else{
                    adapter.addItem(phone, personImgResId, name, state,result);
                }

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

 /*       final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //지연시키길 원하는 밀리초 뒤에 동작         Intent intent=getIntent();
                String getMessage=getActivity().getIntent().getExtras().getString("stateMessage","기본값");
                StateTv.setText(getMessage);

            }
        }, 50000 );*/


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


    public void getSharedPreferenceUserInfo() {
        context = getActivity();
        sh_Pref = context.getSharedPreferences("userInfo", MODE_PRIVATE);//이름
        if(sh_Pref != null && sh_Pref.contains("userName")) {
            stringTemp = sh_Pref.getString("userName", "noname");
            userName.setText(stringTemp);

        }

        sh_Pref = context.getSharedPreferences("userInfo", MODE_PRIVATE);//상태메세지 & 감정결과
        if(sh_Pref != null && sh_Pref.contains("userState")&& sh_Pref.contains("userEmotion")) {
            Log.d("profile3", "성공"+stringTemp+state+result);
            state = sh_Pref.getString("userState", "nonstate");
            result = sh_Pref.getString("userEmotion", "noemotion");
            result2=sh_Pref.getString("userState2", "nonstate");
            result3=sh_Pref.getString("userEmotion2", "noemotion");


            if(stringTemp.equals("이보영")){
                StateTv.setText(state);
                if(result.equals("행복")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_happy);
                }else if(result.equals("불안")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_fear);
                }else if(result.equals("슬픔")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_disapproval);
                }else if(result.equals("평온")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_silent);
                }


            }
            else if(stringTemp.equals("박보검")){
                StateTv.setText(state);
                if(result.equals("행복")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_happy);
                }else if(result.equals("불안")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_fear);
                }else if(result.equals("슬픔")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_disapproval);
                }else if(result.equals("평온")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_silent);
                }
                profile.setImageResource(R.drawable.ph00000003);
            }

        }




    }

    @Override
    public void onPause() {
        super.onPause();
        search.clearFocus();

    }

 /*   public void setUser(UserVO user){
        gUser=user;
        Log.d("test",user.getUsername());

        //userName.setText(user.getUsername());
    }*/


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



