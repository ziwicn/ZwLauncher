package com.czw.zwlauncher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;

import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.VerticalGridView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.czw.zwlauncher.interfaces.GridViewScrollListener;

public class GridView extends VerticalGridView implements GridViewScrollListener {
    HorizontalGridView mHorizontalGridView;
    public GridView(Context context) {
        super(context);
    }

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            int keyCode = event.getKeyCode();
            // 这里只考虑水平移动的情况（垂直移动相同的解决方案）
            if (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                View focusedView = getFocusedChild();  // 获取当前获得焦点的view
                View nextFocusView;
                try {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                        // 通过findNextFocus获取下一个需要得到焦点的view
                        nextFocusView = FocusFinder.getInstance().findNextFocus(this, focusedView, View.FOCUS_UP);
                    } else {
                        // 通过findNextFocus获取下一个需要得到焦点的view
                        nextFocusView = FocusFinder.getInstance().findNextFocus(this, focusedView, View.FOCUS_DOWN);
                    }
                } catch (Exception e) {
                    nextFocusView = null;
                }
                // 如果获取失败（也就是说需要交给系统来处理焦点， 消耗掉事件，不让系统处理， 并让先前获取焦点的view获取焦点）
                if (nextFocusView == null) {
                    focusedView.requestFocus();
                    int position = getChildLayoutPosition(focusedView);
                    if (position < ((GridLayoutManager)getLayoutManager()).getSpanCount()) {
                        Log.i("GridView", " " + position);
                        mHorizontalGridView.requestFocus();
                    }
                    return true;
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void scrollToPosition(int position) {
        smoothScrollToPosition(position);
    }

    public void setHorizontalGridView(HorizontalGridView horizontalGridView) {
        mHorizontalGridView = horizontalGridView;
    }
}
