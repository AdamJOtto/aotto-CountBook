package com.example.aotto.aotto_countbook;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class removeCounter extends AppCompatActivity {

    ListView lv;
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_counter);

        //Initialize the widgets
        returnButton = (Button) findViewById(R.id.return_Button);
        lv = (ListView) findViewById(R.id.removeList_ListView);

        RefreshViewList();

        //Sets a listener for the listView so it will delete a counter when one is pressed.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.Parentlist.remove(position);
                RefreshViewList();
            }
        });

        //Sets a listener for the return button.
        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


    //Refreshes the listview to show the contents of ParentList
    public void RefreshViewList() {
        String[] temp = new String[MainActivity.Parentlist.size()];
        for(int i = 0; i < MainActivity.Parentlist.size(); i++)
        {
            temp[i] = (String) (MainActivity.Parentlist.get(i).getName() + " : " + MainActivity.Parentlist.get(i).getDate().toString() + " : " + MainActivity.Parentlist.get(i).getCount());
        }
        ArrayAdapter<String> childList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp);
        lv.setAdapter(childList);
    }
}