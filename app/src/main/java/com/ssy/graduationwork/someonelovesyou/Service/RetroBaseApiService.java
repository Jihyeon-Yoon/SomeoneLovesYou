
package com.ssy.graduationwork.someonelovesyou.Service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssy.graduationwork.someonelovesyou.Object.HeartDTO;
import com.ssy.graduationwork.someonelovesyou.Request.GetUserDTO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetroBaseApiService {

    /**
     * API 정의
     * 여기서 작성한 메서드를 RetroClient 클래스에도 작성해줘야한다.
     */

    final String Base_URL = "http://devsim0.cafe24.com/";

    //회원가입
    @POST("user/get")
    Call<ResponseBody> getUser(@Body GetUserDTO dto);
    Call<ResponseBody> sendHeart(@Body HeartDTO dto);
}