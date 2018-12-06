package com.ssy.graduationwork.someonelovesyou;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.ssy.graduationwork.someonelovesyou.Object.UserVO;
import com.ssy.graduationwork.someonelovesyou.Retrofit.RetroCallback;
import com.ssy.graduationwork.someonelovesyou.Retrofit.RetroClient;
import static com.ssy.graduationwork.someonelovesyou.Friend.iv_emoticon;
import static com.ssy.graduationwork.someonelovesyou.Friend.StateTv;
import static com.ssy.graduationwork.someonelovesyou.Friend.emotionNum;

public class PopupActivity extends FragmentActivity {

    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;
    Context context;
    ImageButton ib_emoticon;
    EditText statusText;
    Button saveBtn;
    private RetroClient retroClient;
    UserVO gUser;
    String state;
    String result;
    String result2,result3;
    String emotion, userName, userState, userEmotion, userPWD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // setTheme(android.R.style.Theme_NoTitleBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        //setTitle("회원 상태 변경");
        setContentView(R.layout.activity_popup);
        saveBtn=findViewById(R.id.btn_save);
        statusText=findViewById(R.id.et_stateMessage);
        iv_emoticon=findViewById(R.id.iv_emoticon);
        statusText.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.editTextHighlight), PorterDuff.Mode.SRC_ATOP);


        retroClient = RetroClient.getInstance(this).createBaseApi();

       // getSharedPreferenceUserInfo();





        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           emotion=statusText.getText().toString();
            StateTv.setText(emotion);

                String stringTemp="";

                context = getApplicationContext();
                sh_Pref = context.getSharedPreferences("userInfo", MODE_PRIVATE);
                if(sh_Pref != null && sh_Pref.contains("userName")) {
                    stringTemp = sh_Pref.getString("userName", "noname");
                    Log.d("profile1", "성공"+stringTemp);
                    //userName.setText(stringTemp);
                }


                if(stringTemp.equals("이보영")){

                    retroClient.updateProfile("01022345690",emotion, new RetroCallback() {

                        /*
                        응답 오류
                         */
                        @Override
                        public void onError(Throwable t) {
                            Log.d("profile", "에러 : " + t.toString());
                        }

                        @Override
                        public void onSuccess(int code, Object receivedData) {
                            try {
                                Log.d("profile5", "성공"+emotion);
                                Log.d("profile", "성공");




                                Toast.makeText(getApplicationContext(),"성공",Toast.LENGTH_SHORT).show();

                            }catch(Exception e){}
                        }

                        @Override
                        public void onFailure(int code) {
                            Log.d("profile","실패");
                        }
                    });
                }

                else if(stringTemp.equals("박보검")){

                    retroClient.updateProfile("01050345566",emotion, new RetroCallback() {

                        /*
                        응답 오류
                         */
                        @Override
                        public void onError(Throwable t) {
                            Log.d("profile", "에러 : " + t.toString());
                        }

                        @Override
                        public void onSuccess(int code, Object receivedData) {
                            try {
                                Log.d("profile", "성공");




                                Toast.makeText(getApplicationContext(),"성공",Toast.LENGTH_SHORT).show();

                            }catch(Exception e){}
                        }

                        @Override
                        public void onFailure(int code) {
                            Log.d("profile","실패");
                        }
                    });
                }

                setSharedPreferncesUserInfo();
                Log.d("profile7", "성공"+emotion);


                sh_Pref = getSharedPreferences("userInfo", MODE_PRIVATE);
                toEdit = sh_Pref.edit();

                toEdit.putString("userState", emotion);
                toEdit.putString("userEmotion", userEmotion);


                toEdit.commit();



                sh_Pref = context.getSharedPreferences("userInfo", MODE_PRIVATE);
                if(sh_Pref != null && sh_Pref.contains("userName")&&sh_Pref.contains("userState")&&sh_Pref.contains("userEmotion")) {
                    state = sh_Pref.getString("userState", "nonstate");

                    stringTemp = sh_Pref.getString("userName", "noname");
                    result = sh_Pref.getString("userEmotion", "noemotion");
                    Log.d("profile4", "성공"+stringTemp+state+result);
                    //userName.setText(stringTemp);
                }

 /*               sh_Pref = context.getSharedPreferences("userInfo", MODE_PRIVATE);
                if(sh_Pref != null && sh_Pref.contains("userState")){
                    state = sh_Pref.getString("userState", "nonstate");
                    result = sh_Pref.getString("userEmotion", "noemotion");
                    Log.d("profile2", "성공"+userState);
                    Log.d("profile3", "성공"+result);
                }*/
             /*   if(result.equals("행복")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_happy);
                }else if(result.equals("불안")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_fear);
                }else if(result.equals("슬픔")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_disapproval);
                }else if(result.equals("평온")){
                    iv_emoticon.setImageResource(R.drawable.emoticon_silent);
                }*/

                finish();

            }
        });



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }



    public void setSharedPreferncesUserInfo() {
        sh_Pref = getSharedPreferences("userInfo", MODE_PRIVATE);
        toEdit = sh_Pref.edit();

        toEdit.putString("userState", statusText.getText().toString());
        toEdit.putString("userEmotion", userEmotion);


        toEdit.commit();
    }




        }



