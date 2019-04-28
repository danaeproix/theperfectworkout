package com.customizedworkout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class WorkoutList {

    private String name;
    private String id;
    private int iconId;

    public WorkoutList() {
    }

    ;

    public WorkoutList(String name, String id, int iconId) {
        this.name = name;
        this.id = id;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }


    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public static void addChest() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Chest");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("Push Up", "push_up", R.drawable.push_up));
        workouts.put("2", new WorkoutList("Chest Dips", "chest_dips", R.drawable.chest_dips));

        workoutRef.setValue(workouts);


    }

    public static void addAbs() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Abs");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("Bridge", "bridge", R.drawable.bridge));
        workouts.put("2", new WorkoutList("Sit Up", "sit_up", R.drawable.sit_up));
        workoutRef.setValue(workouts);

    }

    public static void addBack() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Back");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("One-arm Dumbbell Row", "one_arm_dumbbell", R.drawable.one_arm_dumbbell));
        workouts.put("2", new WorkoutList("Pull Up", "pull_up", R.drawable.pull_up));
        workoutRef.setValue(workouts);

    }

    public static void addBiceps() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Biceps");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("Curls", "curls", R.drawable.curls));
        workoutRef.setValue(workouts);

    }

    public static void addCalf() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Calf");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("Toe Raises", "toe_raises", R.drawable.toe_raises));
        workoutRef.setValue(workouts);

    }

    public static void addForearms() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Forearms");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("Dumbbell Wrist Twist", "wrist_twist", R.drawable.wrist_twist));
        workoutRef.setValue(workouts);

    }

    public static void addLegs() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Legs");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("Squats", "squats", R.drawable.squats));
        workouts.put("2", new WorkoutList("Lunges", "lunges", R.drawable.lunges));
        workoutRef.setValue(workouts);

    }

    public static void addShoulders() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Shoulders");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("Dumbbell Shoulder Press", "shoulder_press", R.drawable.shoulder_press));
        workoutRef.setValue(workouts);

    }

    public static void addTriceps() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Triceps");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("Close Triceps Push Up", "ct_push_up", R.drawable.ct_push_up));
        workouts.put("2", new WorkoutList("Dumbbell KickBack", "kickback", R.drawable.kickback));
        workoutRef.setValue(workouts);

    }

    public static void addButtocks() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference workoutRef = database.getReference("Buttocks");

        Map<String, WorkoutList> workouts = new HashMap<>();
        workouts.put("1", new WorkoutList("Hips Extension On Knees", "hips_extension", R.drawable.hips_extension));
        workouts.put("2", new WorkoutList("Straight Leg Extension", "straight_leg_extension", R.drawable.straight_leg_extension));
        workoutRef.setValue(workouts);

    }
}
