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
import com.bidamcat.petjoa.items.CatImgItem;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CatImgAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<CatImgItem> items;

    public CatImgAdapter(Context context, ArrayList<CatImgItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_cat_img_item, parent, false);
        VH vh= new VH(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;

        CatImgItem item= items.get(position);

        vh.tvMsg.setText(item.msg);
        vh.tvName.setText(item.name);
        vh.tvDate.setText(item.date);

        String imgUrl="http://kimbidam2.dothome.co.kr/CatImg/" + item.file;
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

            iv= itemView.findViewById(R.id.iv_cat_img);
            tvMsg= itemView.findViewById(R.id.tv_cat_img_msg);
            tvName= itemView.findViewById(R.id.tv_cat_name);
            tvDate= itemView.findViewById(R.id.tv_cat_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getLayoutPosition();
                    CatImgItem catImgItem= items.get(position);

                    Intent intent= new Intent(context, CatImgItemActivity.class);

                    intent.putExtra("no", catImgItem.no+"");
                    intent.putExtra("name", catImgItem.name);
                    intent.putExtra("msg", catImgItem.msg);
                    intent.putExtra("file","http://kimbidam2.dothome.co.kr/CatImg/"+ catImgItem.file);
                    intent.putExtra("date", catImgItem.date);

                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity) context,new Pair<View, String>(iv, "file"));
                    context.startActivity(intent, options.toBundle());

                }
            });
        }
    }
}
