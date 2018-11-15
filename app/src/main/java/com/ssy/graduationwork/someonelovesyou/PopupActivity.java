package com.ssy.graduationwork.someonelovesyou;

import android.app.ActionBar;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupActivity extends FragmentActivity {

    ImageButton ib_emoticon;
    EditText statusText;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // setTheme(android.R.style.Theme_NoTitleBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        //setTitle("회원 상태 변경");
        setContentView(R.layout.activity_popup);
        saveBtn=findViewById(R.id.btn_save);
        statusText=findViewById(R.id.et_stateMessage);
        statusText.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.editTextHighlight), PorterDuff.Mode.SRC_ATOP);

        final String stateMessage=statusText.getText().toString();


        /*
        //감정 설정 메뉴
        ib_emoticon = findViewById(R.id.ib_emoticon);
        ib_emoticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미

                getMenuInflater().inflate(R.menu.emotion, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.e1:
                                ib_emoticon.setImageResource(R.drawable.emoticon_happy);
                                break;
                            case R.id.e2:
                                ib_emoticon.setImageResource(R.drawable.emoticon_confused);
                                break;
                            case R.id.e3:
                                ib_emoticon.setImageResource(R.drawable.emoticon_remorse);
                                break;
                            case R.id.e4:
                                ib_emoticon.setImageResource(R.drawable.emoticon_smiling);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });

                popup.show();//Popup Menu 보이기
            }
        });
        */

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  stateMessage
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


}
