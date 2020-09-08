package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //a list of type hero for holding list items
    List<Hero> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing objects
        heroList    = new ArrayList<>();
        listView    = (ListView) findViewById(R.id.listView);

        //adding some values to our list
        heroList.add(new Hero(R.drawable.spiderman, "Spiderman","Avengers"));
        heroList.add(new Hero(R.drawable.joker, "Joker", "Injustice Gang"));
        heroList.add(new Hero(R.drawable.ironman, "Iron Man", "Avengers"));
        heroList.add(new Hero(R.drawable.doctorstrange, "Doctor Strange", "Avengers"));
        heroList.add(new Hero(R.drawable.captainamerica, "Captain America", "Avengers"));
        heroList.add(new Hero(R.drawable.batman, "Batman", "Justice League"));

        //creating the adapter
        MyListAdapter adapter   = new MyListAdapter(this,R.layout.my_custom_list,heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }
}