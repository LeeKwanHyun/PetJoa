package com.bidamcat.petjoa.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.itemactivitys.CatIFMItemActivity;
import com.bidamcat.petjoa.itemactivitys.DogIFMItemActivity;
import com.bidamcat.petjoa.items.CatIFMItem;
import com.bidamcat.petjoa.items.DogIFMItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DogIFMAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<DogIFMItem> items;

    public DogIFMAdapter(Context context, ArrayList<DogIFMItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_dog_ifm_item, parent, false);
        VH vh= new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;

        DogIFMItem item= items.get(position);

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

            tvTitle= itemView.findViewById(R.id.tv_dog_ifm_title);
            tvMsg= itemView.findViewById(R.id.tv_dog_ifm_msg);
            tvName= itemView.findViewById(R.id.tv_dog_ifm_name);
            tvDate= itemView.findViewById(R.id.tv_dog_ifm_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getLayoutPosition();
                    DogIFMItem dogIFMItem= items.get(position);

                    Intent intent= new Intent(context, DogIFMItemActivity.class);

                    intent.putExtra("no", dogIFMItem.no+"");
                    intent.putExtra("title", dogIFMItem.title);
                    intent.putExtra("name", dogIFMItem.name);
                    intent.putExtra("msg", dogIFMItem.msg);
                    intent.putExtra("date", dogIFMItem.date);

                    context.startActivity(intent);

                }
            });
        }
    }
}
