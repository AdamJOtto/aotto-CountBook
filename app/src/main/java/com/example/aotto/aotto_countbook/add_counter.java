package com.example.aotto.aotto_countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class add_counter extends AppCompatActivity {

    int parentListSize;
    TextView countName;
    EditText countComment;
    EditText countValue;
    Button saveAndExitButton;
    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);

        //Get the size of the counter ArrayList
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            parentListSize = extras.getInt("size");
        }

        //Initialize the editTexts and the buttons.
        countName = (EditText) findViewById(R.id.nameLabel_edit);
        countValue = (EditText) findViewById(R.id.countInitialValue_edit);
        countComment = (EditText) findViewById(R.id.commentLabel_edit);
        saveAndExitButton = (Button) findViewById(R.id.saveExit_Button);
        cancelButton = (Button) findViewById(R.id.cancelButton_button);

        //Create listeners for the two buttons
        saveAndExitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveExit_Click();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    //When the user exits out of this activity, a new counter is created.
    public void updateCounter()
    {
        //If the counter name is left blank, a name is automatically created.
        if(countName.getText().toString().equals(""))
        {
            countName.setText(("Counter" + Integer.toString(parentListSize + 1)));
        }
        //If the initial value is left blank, a default 0 is filled in.
        if(countValue.getText().toString().equals(""))
        {
            countValue.setText("0");
        }
        //Create a new counter and add it to Parentlist.
        counter newCount = new counter(countName.getText().toString(),
                Integer.parseInt(countValue.getText().toString()),
                new Date(),
                Integer.parseInt(countValue.getText().toString()),
                countComment.getText().toString());
        MainActivity.Parentlist.add(parentListSize, newCount);
    }

    //This method handles the save and exit button.
    private void saveExit_Click() {
        updateCounter();
        finish();
        return;
    }

    //Overrides the back press so that it also saves changes made.
    @Override
    public void onBackPressed() {
        updateCounter();
        finish();
        return;
    }
}