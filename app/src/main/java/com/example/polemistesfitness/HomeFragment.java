package com.example.polemistesfitness;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.reflect.GenericArrayType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HomeFragment extends Fragment {

    DatabaseHelper mydb;
    RecyclerView recyclerView;
    ArrayList<String> exercise_name, set_weight, set_reps, set_date;
    //making instance of db adapter class
    DBAdapter dbadapter;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String todaydate;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

       recyclerView = (RecyclerView) view.findViewById(R.id.homerecycle);

        mydb = new DatabaseHelper(getContext());
        exercise_name = new ArrayList<>();
        set_weight = new ArrayList<>();
        set_reps = new ArrayList<>();
        set_date= new ArrayList<>();

        todaydate = sdf.format(new Date()).toString();
        storeDatainArrays();
        dbadapter = new DBAdapter(getContext(),exercise_name ,set_weight,set_reps,set_date);
        recyclerView.setAdapter(dbadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        return view;

    }


    void storeDatainArrays(){
        //calling the method in db java class and storing it in cursor
        Cursor cursor = mydb.readTodayData(todaydate);    //Im going to change this to read only todays Data
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
        //Here we read the columns by their number
        else{
            while (cursor.moveToNext()){
                    //these columindexes might need changing
                exercise_name.add(cursor.getString(1)); //storing column data in arraylist
                set_weight.add(cursor.getString(2));
                set_reps.add(cursor.getString(3));
                set_date.add(cursor.getString(4));

            }
        }

    }
}