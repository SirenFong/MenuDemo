package com.example.listview_nhanvien;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<NhanVien> {
    //Màn hình sử dụng layout này
    Activity layout;
    //Layout cho từng dòng muốn hiển thị
    int resource;
    //Danh sách dữ liệu muốn hiển thị lên màn hình
    @NonNull List<NhanVien> objects;

    public MyArrayAdapter(@NonNull Activity layout, int resource, @NonNull List<NhanVien> objects) {
        super(layout, resource, objects);
        this.layout= layout;
        this.resource= resource;
        this.objects= objects;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Đây là 1 lớp để build layout bình thường
        LayoutInflater inflater= this.layout.getLayoutInflater();
        //this.resource chính là item.xml, truyền vào khi gọi MyArrayAdapter
        convertView = inflater.inflate(this.resource, null);

        ImageView imageItem=convertView.findViewById(R.id.img_View);
        TextView tv_view= convertView.findViewById(R.id.tv_view);
        CheckBox checkBox= convertView.findViewById(R.id.checkbox_view);


        NhanVien nhanVien= this.objects.get(position);

        byte[] byteArray = nhanVien.getImg();
        Bitmap bitmaps = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageItem.setImageBitmap(bitmaps);


        tv_view.setText("  " +nhanVien.getMaso() + "\n  " + nhanVien.getHoten() + "\n  "+ nhanVien.getGioitinh() + "\n  "+
                nhanVien.getDonvi());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox.isChecked()){
                    MainActivity.vitri.add(position);
                }else {
                    for(int x: MainActivity.vitri){
                        if(x == position){
                            MainActivity.vitri.remove(x);
                        }
                    }
                }
            }
        });




        return convertView;
    }



}

