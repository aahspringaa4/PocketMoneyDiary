package com.example.pocketmoneydiary;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.CustomViewHolder> {
    private ArrayList<MemoData> arraylist; // MainData를 리스트배열 arraylist에 넣음

    public MemoAdapter(ArrayList<MemoData> arraylist) { // 생성자
        this.arraylist = arraylist;
    }

    @NonNull // null을 허용하지 않는다.
    @Override
    public MemoAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewHolder와 레이아웃 파일을 연결해주는 역할

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view); // View를 가져온다.

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_content.setText(arraylist.get(position).getTv_content());
        holder.tv_today.setText(arraylist.get(position).getTv_today());
        Log.d("결과", "성공 1");
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    } // arraylist가 null이 아닐 경우 arraylist.size() 반환

    public void remove(int position) {
        try { // 예외사항이 생겨도 강제실행
            arraylist.remove(position);
            notifyItemRemoved(position); // 새로고침
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_content;
        protected TextView tv_today;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_today = (TextView) itemView.findViewById(R.id.today);
            this.tv_content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
