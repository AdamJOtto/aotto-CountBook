package com.example.aotto.aotto_countbook;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditCounter extends AppCompatActivity {

    int index;
    EditText countName;
    TextView countValue;
    TextView countDate;
    EditText countComment;
    Button addButton;
    Button subButton;
    Button resetButton;
    Button saveAndExitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        //This code grabs the index of ParentList so we edit the proper counter.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            index = extras.getInt("index");
        }

        //Get all the component ID's
        countName = (EditText) findViewById(R.id.nameLabel_edit);
        countValue = (TextView) findViewById(R.id.countLable_edit);
        countDate = (TextView) findViewById(R.id.dateLabel_edit);
        countComment = (EditText) findViewById(R.id.commentLabel_edit);
        addButton = (Button) findViewById(R.id.addOne_button);
        subButton = (Button) findViewById(R.id.subOne_button);
        resetButton = (Button) findViewById(R.id.resetCounter_Button);
        saveAndExitButton = (Button) findViewById(R.id.saveExit_Button);
        //Set the text for all the
        countName.setText(MainActivity.Parentlist.get(index).getName());
        countValue.setText(Integer.toString(MainActivity.Parentlist.get(index).getCount()));
        countDate.setText(MainActivity.Parentlist.get(index).getDate().toString());
        countComment.setText(MainActivity.Parentlist.get(index).getComment());

        //Set listeners for the buttons
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addButton_Click();
            }
        });
        subButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                subButton_Click();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetButton_Click();
            }
        });
        saveAndExitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveExit_Click();
            }
        });
    }

    //this method handles the add button.  It calls the increment method of the counter.
    private void addButton_Click() {
        MainActivity.Parentlist.get(index).incrementCounter();
        resetDisplay();
    }

    //this method handles the sub button.  It calls the decrement method of the counter.
    private void subButton_Click() {
        MainActivity.Parentlist.get(index).decrementCounter();
        resetDisplay();
    }

    //This method handles the reset button click.  This resets the counter back to its initial value.
    private void resetButton_Click() {
        MainActivity.Parentlist.get(index).resetCounter();
        resetDisplay();
    }

    //This method that handles the save and exit button.  Closes the activity and returns to MainActivity
    private void saveExit_Click() {
        updateCounter();
        finish();
        return;
    }

    //Resets the display of the counter so that it shows the most up-to-date value.
    private void resetDisplay()
    {
        countValue.setText(Integer.toString(MainActivity.Parentlist.get(index).getCount()));
        countDate.setText(MainActivity.Parentlist.get(index).getDate().toString());
    }

    //Overrides the back press so that it also saves changes made.
    @Override
    public void onBackPressed() {
        updateCounter();
        finish();
        return;
    }

    //Whenever a change happens, the parentlist in MainActivity is also updated.
    public void updateCounter()
    {
        MainActivity.Parentlist.get(index).setComment(countComment.getText().toString());
        MainActivity.Parentlist.get(index).setName(countName.getText().toString());
    }
}