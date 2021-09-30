package com.bidamcat.petjoa.items;

public class PetMissingItem {

    public int no;
    public String name;
    public String msg;
    public String file;
    public String date;

    public PetMissingItem() {
    }

    public PetMissingItem(int no, String name, String msg, String file, String date) {
        this.no = no;
        this.name = name;
        this.msg = msg;
        this.file = file;
        this.date = date;
    }
}
