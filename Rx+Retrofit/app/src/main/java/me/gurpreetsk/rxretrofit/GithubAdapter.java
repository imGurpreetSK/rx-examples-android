package me.gurpreetsk.rxretrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.gurpreetsk.rxretrofit.model.Project;

/**
 * Created by Gurpreet on 18/08/17.
 */

public class GithubAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Project> projects;

    private static final String TAG = GithubAdapter.class.getSimpleName();


    public GithubAdapter(Context context, List<Project> projects) {
        this.context = context;
        this.projects = projects;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewName.setText(projects.get(holder.getAdapterPosition()).getName());
        holder.textViewUrl.setText(projects.get(holder.getAdapterPosition()).getUrl());
        holder.textViewDesc.setText(projects.get(holder.getAdapterPosition()).getDescription());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName;
    TextView textViewUrl;
    TextView textViewDesc;

    MyViewHolder(View view) {
        super(view);
        textViewName = (TextView) view.findViewById(R.id.textview_name);
        textViewUrl = (TextView) view.findViewById(R.id.textview_url);
        textViewDesc = (TextView) view.findViewById(R.id.textview_description);
    }
}