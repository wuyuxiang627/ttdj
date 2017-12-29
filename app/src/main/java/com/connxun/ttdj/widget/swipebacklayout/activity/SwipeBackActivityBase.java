package com.connxun.ttdj.widget.swipebacklayout.activity;


import com.connxun.ttdj.widget.swipebacklayout.SwipeBackLayout;

public interface SwipeBackActivityBase {
    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);
    public abstract void scrollToFinishActivity();

}
