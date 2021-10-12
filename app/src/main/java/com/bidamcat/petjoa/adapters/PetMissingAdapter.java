package com.bidamcat.petjoa.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.itemactivitys.CatImgItemActivity;
import com.bidamcat.petjoa.itemactivitys.PetMissingItemActivity;
import com.bidamcat.petjoa.items.CatImgItem;
import com.bidamcat.petjoa.items.PetMissingItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PetMissingAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<PetMissingItem> items;

    public PetMissingAdapter(Context context, ArrayList<PetMissingItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_pet_missing_item, parent, false);
        VH vh= new VH(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;

        PetMissingItem item= items.get(position);

        vh.tvMsg.setText(item.msg);
        vh.tvName.setText(item.name);
        vh.tvDate.setText(item.date);

        String imgUrl="http://kimbidam2.dothome.co.kr/PetMissing/" + item.file;
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

            iv= itemView.findViewById(R.id.iv_pet_img);
            tvMsg= itemView.findViewById(R.id.tv_pet_img_msg);
            tvName= itemView.findViewById(R.id.tv_pet_name);
            tvDate= itemView.findViewById(R.id.tv_pet_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getLayoutPosition();
                    PetMissingItem petMissingItem= items.get(position);

                    Intent intent= new Intent(context, PetMissingItemActivity.class);

                    intent.putExtra("name", petMissingItem.name);
                    intent.putExtra("msg", petMissingItem.msg);
                    intent.putExtra("file", petMissingItem.file);
                    intent.putExtra("date", petMissingItem.date);

                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity) context,new Pair<View, String>(iv, "file"));
                    context.startActivity(intent, options.toBundle());

                }
            });
        }
    }
}
