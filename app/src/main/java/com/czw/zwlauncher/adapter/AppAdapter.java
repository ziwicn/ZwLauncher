package com.czw.zwlauncher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.czw.zwlauncher.R;
import com.czw.zwlauncher.model.AppInfo;
import com.czw.zwlauncher.interfaces.GridViewScrollListener;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter {
    List<AppInfo> mAppList;
    Context mContext;
    GridViewScrollListener mScrollInterface;

    public AppAdapter(Context context, List<AppInfo> list) {
        mAppList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_app, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setFocusable(true);
        ((ImageView) holder.itemView.findViewById(R.id.app_icon)).setImageDrawable(mAppList.get(position).getIcon());
        ((TextView) holder.itemView.findViewById(R.id.app_name)).setText(mAppList.get(position).getName());
        holder.itemView.setOnClickListener((view) -> {
            mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(mAppList.get(position).getPackageName()));
        });
        holder.itemView.setOnFocusChangeListener((view, focusable) -> {
            if (focusable) {
                (view.findViewById(R.id.focus_view)).setVisibility(View.VISIBLE);
                mScrollInterface.scrollToPosition(position);
            } else {
                (view.findViewById(R.id.focus_view)).setVisibility(View.INVISIBLE);
            }
        });
//        if(position == 0) {
//            holder.itemView.requestFocus();
//        }
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }

    private class AppViewHolder extends RecyclerView.ViewHolder {
        private ImageView mAppIcon;
        private TextView mAppName;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppIcon = itemView.findViewById(R.id.app_icon);
            mAppName = itemView.findViewById(R.id.app_name);
        }
    }

    public void setScrollPositionListener(GridViewScrollListener scrollInterface){
        mScrollInterface = scrollInterface;
    }
}
