package com.example.polemistesfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class ChestExercisesActivity extends AppCompatActivity {

    RecyclerView recycleview;


    String chestNames[];
    int chestImages[];
    String armNames[];
    int armImages[];
    String backNames[];
    int backImages[];
    String legNames[];
    int legImages[];
    String shoulderNames[];
    int shoulderImages[];

    String nm;
    int img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_exercises);

        //the recycelview to be filled with exercises
        recycleview = findViewById(R.id.workoutRecycle);

        getData();   //this will get our name so we know which exercises to display
       // chestName = getResources().getStringArray();
        //the images get found up there and the names get found down here


        if (nm == "Chest") {
            //RAdapter myadapter = new RAdapter(this,chestName,chestImages);
           // recycleview.setAdapter(myadapter);
            //recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        else if (nm == "Arms"){

        }



    }

    //getting the data from intent
    private void getData() {
        if (getIntent().hasExtra("Name") && getIntent().hasExtra("Description") && getIntent().hasExtra("myImg") ){
            nm = getIntent().getStringExtra("Name");

        } else{
            Toast.makeText(this,"No data was found",Toast.LENGTH_SHORT).show();
        }




    }


}