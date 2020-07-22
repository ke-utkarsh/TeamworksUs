package com.example.teamworksus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    Context context;
    List<UserListResponse> userListResponseData;

    public UsersAdapter(Context context, List<UserListResponse> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }
    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_list_items, null);
        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
        return usersViewHolder;
    }
    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        // set the data
        holder.id.setText("Id: "+userListResponseData.get(position).getId());
        holder.title.setText("Name: " + userListResponseData.get(position).getTitle());
        holder.urli.setText("Url: " + userListResponseData.get(position).getUrl());
        holder.thumbnail.setText("Thumbnail: "+userListResponseData.get(position).getThumbnailUrl());
        // implement setONCLickListtener on itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with user name
                Toast.makeText(context, userListResponseData.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView title, id,urli,thumbnail;

        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            id=(TextView) itemView.findViewById(R.id.id);
            title = (TextView) itemView.findViewById(R.id.title);
            urli = (TextView) itemView.findViewById(R.id.urli);
            thumbnail=(TextView) itemView.findViewById(R.id.thumbnail);
        }
    }
}
