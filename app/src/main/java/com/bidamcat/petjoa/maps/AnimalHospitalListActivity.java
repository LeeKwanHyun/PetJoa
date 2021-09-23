package com.bidamcat.petjoa.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bidamcat.petjoa.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AnimalHospitalListActivity extends AppCompatActivity {

    ArrayList<String> items= new ArrayList<>();

    ListView listView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_hospital_list);

        listView= findViewById(R.id.list);
        adapter= new ArrayAdapter(this, R.layout.mymap_list_item, items);
        listView.setAdapter(adapter);
    }


    public void clickList(View view) {

        new Thread(){
            @Override
            public void run() {

                String address="http://openapi.seoul.go.kr:8088/6c716e7877646c7235316655794665/xml/LOCALDATA_020301/1/50/";

                try {
                    URL url= new URL(address);
                    InputStream is= url.openStream();
                    InputStreamReader isr= new InputStreamReader(is);

                    XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                    XmlPullParser xpp= factory.newPullParser();
                    xpp.setInput(isr);

                    int eventType= xpp.getEventType();

                    StringBuffer buffer= null;

                    while(eventType != XmlPullParser.END_DOCUMENT){

                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AnimalHospitalListActivity.this, "동물병원 리스트 불러오는중", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case  XmlPullParser.START_TAG:
                                String name= xpp.getName();
                                if(name.equals("row")){
                                    buffer= new StringBuffer();

                                }else if(name.equals("DTLSTATENM")){
                                    buffer.append("영업상태: ");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");

                                }else if(name.equals("SITETEL")){
                                    buffer.append("전화번호: ");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");

                                }else if(name.equals("SITEWHLADDR")){
                                    buffer.append("번지수 주소: ");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");

                                }else if(name.equals("RDNWHLADDR")){
                                    buffer.append("도로명 주소: ");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");

                                }else if(name.equals("BPLCNM")) {
                                    buffer.append("병원이름: ");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");

                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                String name2= xpp.getName();
                                if(name2.equals("row")){

                                    items.add(buffer.toString());

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });

                                }
                                break;


                        }

                        eventType= xpp.next();

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();


    }
}