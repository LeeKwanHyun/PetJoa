package com.bidamcat.petjoa.items;

public class CatImgItem {

    public int no;
    public String name;
    public String profileImg;
    public String msg;
    public String fileImg;
    public String date;

    public CatImgItem() {
    }

    public CatImgItem(int no, String name, String profileImg, String msg, String fileImg, String date) {
        this.no = no;
        this.name = name;
        this.profileImg = profileImg;
        this.msg = msg;
        this.fileImg = fileImg;
        this.date = date;
    }
}
