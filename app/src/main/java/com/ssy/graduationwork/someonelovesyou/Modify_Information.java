package com.ssy.graduationwork.someonelovesyou;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Modify_Information extends AppCompatActivity {

    final int REQ_CODE_SELECT_IMAGE=100;
    Button modifyBtn, EmailCheckBtn;
    ImageButton btn;
    ImageView profileImgageview;
    EditText pw, checkPw, EmailText;
    String Spw, ScheckPw,status;
    int emailBit=0;
    @Override

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        //권한부여
        setContentView(R.layout.activity_modify__information);

        // 타이틀바 가운데 정렬
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);}


            EmailText=findViewById(R.id.editText_email);
            EmailCheckBtn=findViewById(R.id.emailCheckBtn);
            pw=findViewById(R.id.editText_newPW);
            checkPw=findViewById(R.id.editText_checkPW);
            modifyBtn=findViewById(R.id.button_modifyAccount);
            btn=findViewById(R.id.Btn_Find);
           //profileImgageview=findViewById(R.id.SelectedImage);

       BitmapDrawable d = (BitmapDrawable)((ImageView) findViewById(R.id.SelectedImage)).getDrawable();

        Bitmap b = d.getBitmap();


            //이메일 중복 확인...emailBit값을 0으로 초기화 해두었는데 중복이 아니라면 1로 바꿔준다.
            EmailCheckBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //중복인경우
                    //아닌경우  ->bit=1;
                }
            });

            modifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Spw=pw.getText().toString();
                    ScheckPw=checkPw.getText().toString();
                    //statusText.getText().toString();
                    if(emailBit==0){
                        Toast.makeText(getApplicationContext(),"이메일 중복 확인해주세요",Toast.LENGTH_SHORT).show();
                    }
                    else if(Spw.compareTo(ScheckPw)!=0){
                        Toast.makeText(getApplicationContext(),"비밀번호가 다릅니다",Toast.LENGTH_SHORT).show();
                        pw.setText("");
                        checkPw.setText("");
                    }
                    else if((Spw.compareTo(ScheckPw)==0) && (emailBit==1)){
                        //update
                        //비트맵 b, Spw, email, satus 업데이트
                    }


                }
            });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());

                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageView image = (ImageView)findViewById(R.id.SelectedImage);

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);


                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


}
