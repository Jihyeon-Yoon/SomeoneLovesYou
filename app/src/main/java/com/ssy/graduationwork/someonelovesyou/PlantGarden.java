package com.ssy.graduationwork.someonelovesyou;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlantGarden extends Fragment {

    int heart_num=0;
    ImageView imageView;
    Button heartBtn;
    TextView heartNumView, heartPercentView;
    ProgressBar heart_progressbar;


    public PlantGarden() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_plant_garden, null);


        imageView=rootView.findViewById(R.id.imageView);
        heartBtn=rootView.findViewById(R.id.heart_btn);
        imageView.setImageResource(R.drawable.plant1);
        heart_progressbar=rootView.findViewById(R.id.heartBar);
        heartNumView=rootView.findViewById(R.id.textView_volunNumProgress);
        heartPercentView=rootView.findViewById(R.id.textView_volunNumPercent);

        heartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart_num++;
                if((0<=heart_num)&&(heart_num<=5)){
                    imageView.setImageResource(R.drawable.plant1);
                    showData();
                }else if((6<=heart_num)&&(heart_num<=10)){
                    imageView.setImageResource(R.drawable.plant2);
                    showData();
                }else if((11<=heart_num)&&(heart_num<=15)){
                    imageView.setImageResource(R.drawable.plant3);
                    showData();
                }else if((16<=heart_num)&&(heart_num<=20)){
                    imageView.setImageResource(R.drawable.plant4);
                    showData();
                }else if((21<=heart_num)&&(heart_num<=25)){
                    imageView.setImageResource(R.drawable.plant5_2);
                    showData();
                }
            }
        });





        return rootView;
    }
    public void showData() {
        String temp;
        temp=String.format("%d", heart_num*4);
        heartPercentView.setText(temp);
        temp=String.format("%d",heart_num);
        heartNumView.setText(temp);
        heart_progressbar.setProgress(heart_num);
    }


}
