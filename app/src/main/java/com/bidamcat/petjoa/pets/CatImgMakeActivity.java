package com.bidamcat.petjoa.pets;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bidamcat.petjoa.R;
import com.bumptech.glide.Glide;

import retrofit2.Retrofit;

public class CatImgMakeActivity extends AppCompatActivity {

    EditText et;
    ImageView iv;

    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_img_make);

        et=findViewById(R.id.et_cat_img_make);
        iv=findViewById(R.id.iv_cat);
    }

    public void click_ImgUpLoad(View view) {
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10 && resultCode==RESULT_OK){
            Uri uri= data.getData();
            if(uri != null){
                Glide.with(this).load(uri).into(iv);

                imgPath= getRealPathFromUri(uri);

                //new AlertDialog.Builder(this).setMessage(imgPath).show();
            }
        }
    }

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }

    public void click_Ok(View view) {
        String msg= et.getText().toString();

        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceScalars();



    }

    public void click_cancel(View view) {
    }
}