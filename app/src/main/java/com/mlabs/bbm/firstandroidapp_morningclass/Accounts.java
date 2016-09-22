package com.mlabs.bbm.firstandroidapp_morningclass;

import android.accounts.Account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by markherson on 9/23/2016.
 */
public class Accounts {
    private int id;
    private String email, pass, date;

    public Accounts(){}

    public void setEmail(String email){this.email = email; }

    public void setPass(String pass){this.pass = pass; }

    public void setId (int id){this.id = id; }

    public void setDate(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        this.date = format.format(date);
    }

    public String getEmail (){
        return this.email;
    }

    public String getPass(){
        return this.pass;
    }

    public String getDate(){return this.date; }

    public int getId() {return this.id; }
}
