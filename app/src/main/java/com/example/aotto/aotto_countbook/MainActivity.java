package com.example.aotto.aotto_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //Parentlist is an arraylist that holds the counters.  It is set to public so it can be easily accessed from activities.
    static public ArrayList<counter> Parentlist;
    private ListView lv;
    File f;

    Button removeButton;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the file location for persistence.
        f = new File(getCacheDir(), "CountBook.txt");

        //Initialize the two buttons
        addButton = (Button) findViewById(R.id.addCounterButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent editCounterIntent = new Intent(v.getContext(), add_counter.class);
                editCounterIntent.putExtra("size", Parentlist.size());
                startActivityForResult(editCounterIntent, 0);
            }
        });

        removeButton = (Button) findViewById(R.id.removeCounterButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent removeCounterIntent = new Intent(v.getContext(), removeCounter.class);
                startActivityForResult(removeCounterIntent, 0);
            }
        });

        //Initialize a listView and ArrayAdapter to populate the listView
        lv = (ListView) findViewById(R.id.counterList);

        //Loads a previous instance of the app
        Parentlist = openCounterList( f );

        if (Parentlist == null)
        {
            Parentlist = new ArrayList<counter>();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Sets a listener for the listView so it will start a new activity when an element is pressed.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editCounterIntent = new Intent(view.getContext(), EditCounter.class);
                editCounterIntent.putExtra("index", position);
                startActivityForResult(editCounterIntent, 0);
            }
        });
    }

    //When the main activity resumes, it will refresh the ListView and save any changes made.
    @Override
    public void onResume()
    {
        super.onResume();
        RefreshViewList();
        saveCounterList(Parentlist, f);
    }

    //This Method refreshes the main ListView
    public void RefreshViewList() {
        //a temporary string array is created to store the title for each counter
        String[] temp = new String[Parentlist.size()];

        //The array is populated with the name and current count of each counter
        for(int i = 0; i < Parentlist.size(); i++)
        {
            temp[i] = (String) (Parentlist.get(i).getName() + " : " + Parentlist.get(i).getDate().toString() + " : " + Parentlist.get(i).getCount());
        }

        //An Array adapter takes in the string array and is used to update the ListView.
        ArrayAdapter<String> childList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp);
        lv.setAdapter(childList);
    }

    //Loads a previous instance of the counter app.
    //Code received through class/lab notes
    public static ArrayList<counter> openCounterList(File f)
    {
        ArrayList<counter> returnList = null;
        try {
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fin);
            returnList = (ArrayList<counter>)ois.readObject();
            ois.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    //saves the current instance of the counter app.
    //Code received through class/lab notes
    public static void saveCounterList(ArrayList<counter> list, File f) {
        try {
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(list);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}