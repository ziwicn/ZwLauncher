/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.czw.zwlauncher;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.VerticalGridView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czw.zwlauncher.adapter.AppAdapter;
import com.czw.zwlauncher.adapter.HeadAdapter;
import com.czw.zwlauncher.model.AppInfo;
import com.czw.zwlauncher.view.GridView;

import java.util.List;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    GridView mVerticalGridView;
    HorizontalGridView mHorizontalGridView;
    AppAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mHorizontalGridView = view.findViewById(R.id.head_grid_view);
        mHorizontalGridView.setAdapter(new HeadAdapter(getContext(),Utils.loadHead()));
        mHorizontalGridView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(20,0,20,0);
            }
        });

        mVerticalGridView = view.findViewById(R.id.app_grid_view);
        List<AppInfo> list = Utils.loadApplications(getContext());
        mAdapter = new AppAdapter(getContext(),list);
        mAdapter.setScrollPositionListener(mVerticalGridView);
        mVerticalGridView.setAdapter(mAdapter);
        mVerticalGridView.setLayoutManager(new GridLayoutManager(getContext(),5));
        mVerticalGridView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(35,20,35,20);
            }
        });
        mVerticalGridView.setHasFixedSize(true);
        mVerticalGridView.setOnChildSelectedListener((parent, v, position, id)->{
            Log.i("MainFragment","  "+position);
        });
        mVerticalGridView.setHorizontalGridView(mHorizontalGridView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void requestFocus(){
//        mVerticalGridView.getChildAt(0).requestFocus();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
