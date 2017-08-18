package me.gurpreetsk.rxretrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.gurpreetsk.rxretrofit.model.Project;

/**
 * Created by Gurpreet on 18/08/17.
 */

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Project> projects;

    private static final String TAG = GithubAdapter.class.getSimpleName();


    public GithubAdapter(Context context, ArrayList<Project> projects) {
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

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
