package com.example.customlistview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<Hero> {

    //The list values in the list of type hero;
    List<Hero> heroList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructur initializing the value
    public  MyListAdapter(Context context, int resource, List<Hero> heroList){
        super(context, resource, heroList);
        this.context    = context;
        this.resource   = resource;
        this.heroList   = heroList;
    }

    //this will return the ListView item as a view
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){

        //we need to get the view of the xml for our list item
        //and for this we need a layoutinflater
        LayoutInflater layoutInflater   = LayoutInflater.from(context);

        //getting the view
        View view   = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        ImageView imageView     = view.findViewById(R.id.imageView);
        TextView textViewName   = view.findViewById(R.id.textViewName);
        TextView textViewTeam   = view.findViewById(R.id.textViewTeam);
        Button buttonDelete     = view.findViewById(R.id.buttonDelete);

        //getting the hero of the specified position
        Hero hero   = heroList.get(position);

        //adding values to the list item
        imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        textViewName.setText(hero.getName());
        textViewTeam.setText(hero.getTeam());

        //adding a click listener to the button to remove item from the list
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method
                removeHero(position);
            }
        });
        //finally returning the view
        return view;
    }

    //this method will remove the item from the list
    private void removeHero(final int position){
        //Creating an alert dialog to confirm the deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Anda yakin ingin menghapus ini?");

        //if the response is positive in the alert
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //removing the item
                heroList.remove(position);
                //reloading the list
                notifyDataSetChanged();
            }
        });
        //if response is negative nothing is being done
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //creating and displaying the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
