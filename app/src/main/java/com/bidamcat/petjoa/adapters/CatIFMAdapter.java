package com.bidamcat.petjoa.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.itemactivitys.CatIFMItemActivity;
import com.bidamcat.petjoa.itemactivitys.CatImgItemActivity;
import com.bidamcat.petjoa.items.CatIFMItem;
import com.bidamcat.petjoa.items.CatImgItem;
import com.bidamcat.petjoa.pets.CatIFMActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CatIFMAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<CatIFMItem> items;

    public CatIFMAdapter(Context context, ArrayList<CatIFMItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_cat_ifm_item, parent, false);
        VH vh= new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;

        CatIFMItem item= items.get(position);

        vh.tvTitle.setText(item.title);
        vh.tvMsg.setText(item.msg);
        vh.tvName.setText(item.name);
        vh.tvDate.setText(item.date);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvMsg;
        TextView tvName;
        TextView tvDate;

        public VH(@NonNull @NotNull View itemView) {
            super(itemView);

            tvTitle= itemView.findViewById(R.id.tv_cat_ifm_title);
            tvMsg= itemView.findViewById(R.id.tv_cat_ifm_msg);
            tvName= itemView.findViewById(R.id.tv_cat_ifm_name);
            tvDate= itemView.findViewById(R.id.tv_cat_ifm_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getLayoutPosition();
                    CatIFMItem catIFMItem= items.get(position);

                    Intent intent= new Intent(context, CatIFMItemActivity.class);

                    intent.putExtra("title", catIFMItem.title);
                    intent.putExtra("name", catIFMItem.name);
                    intent.putExtra("msg", catIFMItem.msg);
                    intent.putExtra("date", catIFMItem.date);

                    context.startActivity(intent);

                }
            });
        }
    }
}
