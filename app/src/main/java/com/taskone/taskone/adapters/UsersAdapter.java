package com.taskone.taskone.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taskone.taskone.R;
import com.taskone.taskone.model.GitHubUser;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<GitHubUser> list;
    public UsersAdapter(List<GitHubUser> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final GitHubUser obj = list.get(position);
        holder.name.setText(obj.getLogin());
        holder.url.setText(obj.getHtmlUrl());
        Picasso.get()
                .load(obj.getAvatarUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.avatarImg);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView url;
        ImageView avatarImg;
        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.text_name);
            url = view.findViewById(R.id.text_url);
            url.setMovementMethod(LinkMovementMethod.getInstance());
            avatarImg = view.findViewById(R.id.img_avatar);
        }
    }
}