package com.bidamcat.petjoa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.items.CatImgItem;
import com.bidamcat.petjoa.items.DogImgItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DogImgAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<DogImgItem> items;

    public DogImgAdapter(Context context, ArrayList<DogImgItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_dog_img_item, parent, false);
        VH vh= new VH(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;

        DogImgItem item= items.get(position);

        vh.tvMsg.setText(item.msg);
        vh.tvName.setText(item.name);
        vh.tvDate.setText(item.date);

        String imgUrl="http://kimbidam2.dothome.co.kr/DogImg/" + item.file;
        Glide.with(context).load(imgUrl).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvMsg;
        TextView tvName;
        TextView tvDate;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv_dog_img);
            tvMsg= itemView.findViewById(R.id.tv_dog_img_msg);
            tvName= itemView.findViewById(R.id.tv_dog_name);
            tvDate= itemView.findViewById(R.id.tv_dog_date);
        }
    }
}
