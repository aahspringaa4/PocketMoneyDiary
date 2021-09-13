package com.example.pocketmoneydiary;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.net.CookieStore;
import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.CustomViewHolder> {
    public ArrayList<String> mcontext = null;
    private Activity Mc;

    ArrayList<String> items = new ArrayList<String>(); // MainData를 리스트배열 arraylist에 넣음
    ArrayList<String> item = new ArrayList<String>(); // MainData를 리스트배열 arraylist에 넣음

    public MemoAdapter(ArrayList<String> context) { // 생성자
        mcontext = context;
    }

    public void addItem(String item) {
        items.add(item);
    }

    @NonNull // null을 허용하지 않는다.
    @Override
    public MemoAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        // 미리 만들어 놓은 item_rv_memo.xml 기입
        View view = inflater.inflate(R.layout.item_view, parent, false) ;
        MemoAdapter.CustomViewHolder vh = new MemoAdapter.CustomViewHolder(view) ;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MemoAdapter.CustomViewHolder holder, final int position) {

        holder.tv_today.setText(items.get(position));
        holder.tv_content.setText(item.get(position));

        holder.patch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mc, MemoScanActivity.class);
                intent.putExtra("key",holder.tv_content.getText().toString());
                Mc.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != items ? items.size() : 0);
    } // arraylist가 null이 아닐 경우 arraylist.size() 반환

    public void remove(int position) {
        items.remove(position);
        item.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
        notifyItemRangeChanged(position, item.size());
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_content;
        protected TextView tv_today;
        protected TextView patch;
        protected TextView delete;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_today = (TextView) itemView.findViewById(R.id.today);
            this.tv_content = (TextView) itemView.findViewById(R.id.content);
            this.patch = (TextView) itemView.findViewById(R.id.patch);
            this.delete = (TextView) itemView.findViewById(R.id.delete);
        }
    }
}