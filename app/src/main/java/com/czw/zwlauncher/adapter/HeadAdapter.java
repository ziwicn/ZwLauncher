package com.czw.zwlauncher.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.czw.zwlauncher.R;
import com.czw.zwlauncher.model.AppInfo;

import java.util.List;

public class HeadAdapter extends RecyclerView.Adapter {
    List<String> mHeadList;
    Context mContext;

    public HeadAdapter(Context context, List<String> list) {
        mHeadList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_head, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setFocusable(true);
        ((TextView) holder.itemView.findViewById(R.id.head_name)).setText(mHeadList.get(position));
        holder.itemView.setOnFocusChangeListener((view, focusable) -> {
//            if (focusable) {
//                (view.findViewById(R.id.focus_head_view)).setVisibility(View.VISIBLE);
//            } else {
//                (view.findViewById(R.id.focus_head_view)).setVisibility(View.INVISIBLE);
//            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeadList.size();
    }

    private class AppViewHolder extends RecyclerView.ViewHolder {
        private TextView mHeadName;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeadName = itemView.findViewById(R.id.head_name);
        }
    }
}
