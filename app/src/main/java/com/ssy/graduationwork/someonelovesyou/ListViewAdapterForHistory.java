package com.ssy.graduationwork.someonelovesyou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 윤지현 on 2018-07-18.
 */

public class ListViewAdapterForHistory extends BaseAdapter {

    // Adapter에 추가한 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItemForHistory> listViewItemList;

    // ListViewAdatper의 생성자
    public ListViewAdapterForHistory(ArrayList<ListViewItemForHistory> itemList) {
        if(itemList == null) {
            listViewItemList = new ArrayList<ListViewItemForHistory>();
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
        final Context context = parent.getContext();

        // listview_item layout을 inflate하여 convertView 참조 획득
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_history_item, parent, false);
        }

        // 화면에 표시될 View(layout이 inflate됨)로 부터 위젯에 대한 참조 획득
        ImageView flowerImageView = convertView.findViewById(R.id.iv_flower);
        TextView startDate = convertView.findViewById(R.id.tv_startDate);
        TextView endDate = convertView.findViewById(R.id.tv_endDate);


        // Data set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItemForHistory listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        flowerImageView.setImageResource(listViewItem.getFlowerImgResId());
        startDate.setText(listViewItem.getStartDate());
        endDate.setText(listViewItem.getEndDate());

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
    // temp (0: 순서), 1:꽃종류, 2:시작날짜 3:끝날짜
    public void addItem(int flowerImgResId, String startDate, String endDate) {
        ListViewItemForHistory item = new ListViewItemForHistory();

        item.setFlowerImgResId(flowerImgResId);
        item.setStartDate(startDate);
        item.setEndDate(endDate);

        listViewItemList.add(item);
    }

}
