package com.example.aotto.aotto_countbook;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * Created by aotto on 9/15/17.
 */

public class counter implements Serializable {
    private String name;
    private int count;
    private Date date;
    private int initValue;
    private String comment;

    public counter(String name, int count, Date date, int initialValue, String comment) {
        this.name = name;
        this.count = count;
        this.date = date;
        this.initValue = initialValue;
        this.comment = comment;
        if (this.count < 0)
        { this.count = 0; }
    }

    //Getters and setters
    public String getName()
    {
        return name;
    }
    public void setName(String value){name = value;}
    public int getCount()
    {
        return count;
    }
    public void setCount(int newCount)
    {
        count = newCount;
    }
    public String getDate()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    public void setDate()
    {
        date = new Date();
    }
    public String getComment()
    {
        return comment;
    }
    public void setComment(String value)
    {
        comment = value;
    }
    public void setInitialValue(int value) { initValue = value; }

    public void incrementCounter()
    {
        count += 1;
        date = new Date();
    }

    public void decrementCounter()
    {
        count -= 1;
        if(count < 0)
        {
            count = 0;
        }
        date = new Date();
    }

    public void resetCounter()
    {
        count = initValue;
        date = new Date();
    }
}
