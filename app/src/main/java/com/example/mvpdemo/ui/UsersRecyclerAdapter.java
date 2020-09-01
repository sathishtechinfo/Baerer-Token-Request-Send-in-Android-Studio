package com.example.mvpdemo.ui;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpdemo.R;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<Userlist> listUsers;
    Context mContext;

    public UsersRecyclerAdapter(Context mContext, List<Userlist> listUsers) {
        this.listUsers = listUsers;
        this.mContext=mContext;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.txtfirstname.setText(listUsers.get(position).getFirstname());
        holder.txtlastname.setText(listUsers.get(position).getLastname());
        holder.txtemail.setText(listUsers.get(position).getEmail());
        holder.txtmobile.setText(listUsers.get(position).getMobile());


        holder.llLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,Profile_Activity.class);
                i.putExtra("ID",listUsers.get(position).getID());
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView txtfirstname;
        public TextView txtlastname;
        public TextView txtemail;
        public TextView txtmobile;

        LinearLayout llLayout;

        public UserViewHolder(View view) {
            super(view);
            txtfirstname = (TextView) view.findViewById(R.id.txtfirstname);
            txtlastname = (TextView) view.findViewById(R.id.txtlastname);
            txtemail = (TextView) view.findViewById(R.id.txtemail);
            txtmobile = (TextView) view.findViewById(R.id.txtmob);
            llLayout=view.findViewById(R.id.ll_layout);

        }
    }


}
