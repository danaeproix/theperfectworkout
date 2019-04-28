package com.customizedworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.ViewHolder> {

    private ArrayList<WorkoutList> workoutLists;
    final private OnListItemClickListener onListItemClickListener;

    WorkoutListAdapter(ArrayList<WorkoutList> workoutLists, OnListItemClickListener onListItemClickListener) {
        this.workoutLists = workoutLists;
        this.onListItemClickListener = onListItemClickListener;

    }

    public ArrayList<WorkoutList> getWorkoutLists() {
        return this.workoutLists;
    }

    @NonNull
    @Override
    public WorkoutListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.workout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(workoutLists.get(position).getName());
        viewHolder.icon.setImageResource(workoutLists.get(position).getIconId());
    }

    @Override
    public int getItemCount() {
        return workoutLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            icon = itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener {

        void onListItemClick(int clickedItemIndex);
    }


}
