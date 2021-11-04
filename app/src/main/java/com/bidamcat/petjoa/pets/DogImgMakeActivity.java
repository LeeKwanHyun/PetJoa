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
import android.widget.Toast;

import com.bidamcat.petjoa.G;
import com.bidamcat.petjoa.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DogImgMakeActivity extends AppCompatActivity {

    EditText et;
    ImageView iv;

    String imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_img_make);

        et=findViewById(R.id.et_dog_img_make);
        iv=findViewById(R.id.iv_dog);
    }

    public void click_ImgUpLoad(View view) {
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 9);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==9 && resultCode==RESULT_OK){
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
        RetrofitService_Dog retrofitService_dog= retrofit.create(RetrofitService_Dog.class);

        MultipartBody.Part filepart= null;
        if(imgPath!=null){
            File file= new File(imgPath);
            RequestBody requestBody= RequestBody.create(MediaType.parse("image/*"),file);
            filepart= MultipartBody.Part.createFormData("img", file.getName(), requestBody);
        }

        Map<String, String> dataPart= new HashMap<>();
        dataPart.put("msg", msg);
        dataPart.put("name", G.nickname);

        Call<String> call= retrofitService_dog.postDataToServer(dataPart, filepart);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(DogImgMakeActivity.this, ""+s, Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DogImgMakeActivity.this, "error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void click_cancel(View view) {
        finish();
    }
}