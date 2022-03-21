package com.example.listview_nhanvien;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnXemds:
                Toast.makeText(this, "Xem danh sách", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnXemSV:
                Toast.makeText(this, "Xem danh sách nhân viên", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnSuaDanhSach:

                return true;
            default:
                return  super.onOptionsItemSelected(item);

        }
    }

    ListView lv_DS;
    ArrayList<NhanVien> nvArrayList= new ArrayList<NhanVien>();
    MyArrayAdapter adapterNV;
    String[] dv_List;
    String donvi;
    ImageView imageActi;

    public  static ArrayList<Integer> vitri= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et_Maso= findViewById(R.id.editText_MaSo);
        EditText et_Hoten= findViewById(R.id.editText_HoTen);
        lv_DS= findViewById(R.id.listViewNV);
        RadioGroup rg_Gioitinh= findViewById(R.id.radiofroup);
        RadioButton rg_Nam= findViewById(R.id.radioButton_Nam);
        RadioButton rg_Nu= findViewById(R.id.radioButton_Nu);
        imageActi= findViewById(R.id.imageActivity);
        Button btnExit= findViewById(R.id.button_thoat);


        Spinner spinner_DonVi= findViewById(R.id.spinner_DonVi);

        dv_List= getResources().getStringArray(R.array.donvi_list);

        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dv_List);
        spinner_DonVi.setAdapter(adapter);
        spinner_DonVi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donvi= dv_List[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        Button bt_ThemNV= findViewById(R.id.button_Them);
        bt_ThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maNV= Integer.parseInt(et_Maso.getText().toString());
                String tenNV= et_Hoten.getText().toString();
                String gioitinhNV= ((RadioButton)findViewById(rg_Gioitinh.getCheckedRadioButtonId())).getText().toString();
                byte[] imagesByte = imagesByte(imageActi);


                NhanVien nV= new NhanVien(maNV, tenNV, gioitinhNV, donvi, imagesByte);
                nvArrayList.add(nV);


                adapterNV= new MyArrayAdapter(MainActivity.this, R.layout.item_viewnhanvien, nvArrayList);
                lv_DS.setAdapter(adapterNV);
                adapter.notifyDataSetChanged();

            }
        });


        Button btn_ChupHinh = findViewById(R.id.button_ChupHinh);
        btn_ChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.CAMERA}, 10);
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 10);

            }
        });

        Button btn_LayAnh = findViewById(R.id.button_LayAnh);
        btn_LayAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK
                        , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 4);
            }
        });


        Button bt_Xoa= findViewById(R.id.button_Xoa);
        bt_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!vitri.isEmpty()){
                    for(int k:vitri){
                        nvArrayList.remove(k);
                    }
                    vitri.clear();
                    adapterNV.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Chưa chọn!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    public byte[] imagesByte(ImageView img) {
        Bitmap bitmaps = ((BitmapDrawable) img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmaps.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytesimg = stream.toByteArray();
        return  bytesimg;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 10 && resultCode == RESULT_OK && data != null) {
            Bitmap captureImages = (Bitmap) data.getExtras().get("data");
            imageActi.setImageBitmap(captureImages);
        }

        if (requestCode == 4 && resultCode == RESULT_OK && data != null) {
            Uri selectedImgs = data.getData();
            imageActi.setImageURI(selectedImgs);

        }
    }


//        lv_Nhanvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                NhanVien nv= dsNhanVien.get(i);
//                //mã số là kiểu int nên +"" đằng sau nó nữa
//
//
//                et_Maso.setText(nv.getMaso() + "");
//                //họ tên kiểu string nên ko cần
//                et_Hoten.setText(nv.getHoten());
//
//                //Xử lý giới tính
//                if(nv.getGioitinh().equals("Nam")){
//                    rg_Nam.setChecked(true);
//                }
//                else{
//                    rg_Nu.setChecked(true);
//                }
//
//                //Xử lý đơn vị
//                for(int j=0; j < dv_List.length; j++){
//                    if(dv_List[j].equals(nv.getGioitinh())){
//                        spinner_DonVi.setSelection(j);
//                    }
//                }
//
//
//            }
//        });


}