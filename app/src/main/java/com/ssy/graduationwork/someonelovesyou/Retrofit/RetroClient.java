package com.ssy.graduationwork.someonelovesyou.Retrofit;

import android.content.Context;

import com.ssy.graduationwork.someonelovesyou.Object.HeartDTO;
import com.ssy.graduationwork.someonelovesyou.Request.GetUserDTO;
import com.ssy.graduationwork.someonelovesyou.Service.RetroBaseApiService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroClient {
    private RetroBaseApiService apiService;
    public static String baseUrl = RetroBaseApiService.Base_URL;
    private static Context mContext;
    private static Retrofit retrofit;


    private static class SingletonHolder {
        private static RetroClient INSTANCE = new RetroClient(mContext);
    }

    public static RetroClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }

    private RetroClient(Context context) {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public RetroClient createBaseApi() {
        apiService = create(RetroBaseApiService.class);
        return this;
    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public  <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    public void getUSer(GetUserDTO dto, final RetroCallback callback) {
        apiService.getUser(dto).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                } else {
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void sendHeart(HeartDTO dto, final RetroCallback callback) {
        apiService.sendHeart(dto).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                } else {
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
    public void getHeart(String id, final RetroCallback callback) {
        apiService.getHeart(id).enqueue(new Callback<List<HeartDTO>>() {

            @Override
            public void onResponse(Call<List<HeartDTO>> call, Response<List<HeartDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                } else {
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<List<HeartDTO>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void updateProfile(String id, String profile, final RetroCallback callback) {
        apiService.updateProfile(id,profile).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                } else {
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError(t);
            }
        });
    }



}