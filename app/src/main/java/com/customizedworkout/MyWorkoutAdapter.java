package com.customizedworkout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

class MyWorkoutAdapter extends RecyclerView.Adapter<MyWorkoutAdapter.ViewHolder> {

    private ArrayList<WorkoutList> workoutLists;
    final private OnListItemClickListener onListItemClickListener;
    private Context context;

    MyWorkoutAdapter(ArrayList<WorkoutList> workoutLists, OnListItemClickListener onListItemClickListener, Context context) {
        this.workoutLists = workoutLists;
        this.onListItemClickListener = onListItemClickListener;
        this.context = context;


    }

    public ArrayList<WorkoutList> getWorkoutLists() {
        return this.workoutLists;
    }

    @NonNull
    @Override
    public MyWorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.myworkout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyWorkoutAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.name.setText(workoutLists.get(position).getName());
        viewHolder.icon.setImageResource(workoutLists.get(position).getIconId());
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                for (int i = 0; i < prefs.getAll().size() / 3; i++) {

                    if (prefs.getString("name" + i, "").contains(workoutLists.get(position).getName())) {
                        prefs.edit().remove("name" + i).apply();
                        prefs.edit().remove("id" + i).apply();
                        prefs.edit().remove("iconId" + i).apply();
                        Toast.makeText(context, workoutLists.get(position).getName() + " have been removed of your workout", Toast.LENGTH_SHORT).show();

                        //context.startActivity(context.getIntent());
                        /*this.getClass().finish();
                        startActivity(getIntent());µ*/
                    }
                }

                //context.finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return workoutLists.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageView icon;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            icon = itemView.findViewById(R.id.iv_icon);
            imageView = itemView.findViewById(R.id.imageview);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {


            onListItemClickListener.onListItemClick(getAdapterPosition(), view);

        }
    }

    public interface OnListItemClickListener {

        void onListItemClick(int clickedItemIndex, View view);



    }


}
