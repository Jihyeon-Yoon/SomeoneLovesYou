package com.ssy.graduationwork.someonelovesyou;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ssy.graduationwork.someonelovesyou.Object.HeartDTO;
import com.ssy.graduationwork.someonelovesyou.Retrofit.RetroCallback;
import com.ssy.graduationwork.someonelovesyou.Retrofit.RetroClient;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static java.security.AccessController.getContext;

/**
 * Created by 윤지현 on 2018-07-18.
 */

public class ListViewAdapterForFriend extends BaseAdapter {

    // Adapter에 추가한 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItemForFriend> listViewItemList;



    // ListViewAdatper의 생성자
    public ListViewAdapterForFriend(ArrayList<ListViewItemForFriend> itemList) {
        if(itemList == null) {
            listViewItemList = new ArrayList<ListViewItemForFriend>();

        }
        else {
            listViewItemList = itemList;
        }
    }



    // Adapter에 사용되는 데이터의 개수를 리턴 : 필수 구현
    public int getCount() {
        return listViewItemList.size();
    }



    // position에 위치한 데이터를 화면에 출력하는 데 사용될 view를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final  RetroClient retroClient;
        final String UserName;
        int received_heartNum;
        final Context context = parent.getContext();
        final String LOG = "ConnectionTEST";

        retroClient = RetroClient.getInstance(context).createBaseApi();
        //retroClient = RetroClient.getInstance(this).createBaseApi();

        // listview_item layout을 inflate하여 convertView 참조 획득
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_friend_item, parent, false);
        }

        // 화면에 표시될 View(layout이 inflate됨)로 부터 위젯에 대한 참조 획득
        ImageView personImageView = convertView.findViewById(R.id.iv_person);
        ImageView emoticon=convertView.findViewById(R.id.iv_emoticon);
        TextView nameTextView = convertView.findViewById(R.id.tv_name);
        TextView stateTextView = convertView.findViewById(R.id.tv_state);
        //listItem=convertView.findViewById(R.id.listView);
        ImageButton  send_name, no_nameBtn, send_music;


        send_name=convertView.findViewById(R.id.iv_send_name);
        no_nameBtn=convertView.findViewById(R.id.iv_send_noname);
        send_music=convertView.findViewById(R.id.iv_music);

       /* emoticon.setFocusable(false);
        send_name.setFocusable(false);
        no_nameBtn.setFocusable(false);
        send_music.setFocusable(false);*/


        // Data set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItemForFriend listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        personImageView.setImageResource(listViewItem.getPersonImgResId());
        nameTextView.setText(listViewItem.getName());
        stateTextView.setText(listViewItem.getState());
        String result=listViewItem.getResult();

        if(result.equals("행복")){
            emoticon.setImageResource(R.drawable.emoticon_happy);
        }
        else if(result.equals("불안")){
            emoticon.setImageResource(R.drawable.emoticon_fear);
        }
        else if(result.equals("슬픔")){
            emoticon.setImageResource(R.drawable.emoticon_disapproval);
        }

       /* emoticon.setFocusable(false);
        send_name.setFocusable(false);
        no_nameBtn.setFocusable(false);
        send_music.setFocusable(false);*/
        UserName=nameTextView.getText().toString();
        final String Phone=listViewItem.getPhone();






        send_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserName.equals("박보검")){
                    String receiver_id = "01050345566";
                    HeartDTO dto = new HeartDTO();
                    dto.setSender("01022345690"); //이보영
                    dto.setReceiver("01050345566"); //박보검
                    retroClient.sendHeart(dto, new RetroCallback() {

                        /*
                        응답 오류
                         */
                        @Override
                        public void onError(Throwable t) {
                            Log.d(LOG, "에러 : " + t.toString());
                        }

                        @Override
                        public void onSuccess(int code, Object receivedData) {
                            try {
                                Log.d(LOG, "성공");
                                Toast.makeText(context.getApplicationContext(),"성공!",Toast.LENGTH_SHORT).show();
                            }catch(Exception e){}
                        }

                        @Override
                        public void onFailure(int code) {
                            Log.d(LOG,"실패");
                        }
                    });
                    retroClient.getHeart(receiver_id, new RetroCallback() {

                        /*
                        응답 오류
                         */
                        @Override
                        public void onError(Throwable t) {
                            Log.d(LOG, "에러 : " + t.toString());
                        }

                        @Override
                        public void onSuccess(int code, Object receivedData) {
                            try {
                                Log.d(LOG, "성공");
                                ArrayList<HeartDTO> res = (ArrayList<HeartDTO>)receivedData;
                                Toast.makeText(context.getApplicationContext(),"성공! 받은 리스트 수 : " + res.size(),Toast.LENGTH_SHORT).show();
                            }catch(Exception e){}
                        }

                        @Override
                        public void onFailure(int code) {
                            Log.d(LOG,"실패");
                        }
                    });

                    Toast.makeText(context.getApplicationContext(),"01050345566",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(context.getApplicationContext(),UserName+"님께 하트가 전송되었습니다",Toast.LENGTH_SHORT).show();
            }
        });
        no_nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(),UserName+"님께 하트가 익명으로 전송되었습니다",Toast.LENGTH_SHORT).show();
            }
        });
        send_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecommandMusicActivity.class);
                context.startActivity(intent);
                SharedPreferences sp = context.getSharedPreferences("a", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("key", UserName);
                editor.putString("key2", Phone);

                editor.commit();

            }
        });



        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된  아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴. : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    // 아이템 데이터 추가를 위한 함수, 개발자가 원하는대로 작성 가능
    // temp 0:폰번호 1: 이미지이름, 2: 이름, 3: 상태
    public void addItem(String phone, int personImgResId, String name, String state, String result) {
        ListViewItemForFriend item = new ListViewItemForFriend();

        item.setPhone(phone);
        item.setPersonImgResId(personImgResId);
        item.setName(name);
        item.setState(state);
        item.setResult(result);


        listViewItemList.add(item);
    }









}
