package com.example.mvpdemo.base;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder  extends RecyclerView.ViewHolder {

    private int position;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        this.position = position;
        clear();
    }

    public int getCurrentPosition() {
        return position;
    }
}
