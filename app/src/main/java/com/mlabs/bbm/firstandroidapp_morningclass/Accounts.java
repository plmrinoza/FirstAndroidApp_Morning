package com.mlabs.bbm.firstandroidapp_morningclass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pedro on 06/10/2016.
 */

public class Accounts {

    private int id;
    private String uNam, eMail, pass, fNam, lNam, date;

    public Accounts() {}

    public void setAccounts(String uNam, String eMail, String pass, String fNam, String lNam) {
        this.uNam = uNam;
        this.eMail = eMail;
        this.pass = pass;
        this.fNam = fNam;
        this.lNam = lNam;

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        this.date = format.format(date);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getuNam() {
        return this.uNam;
    }

    public String geteMail() {
        return this.eMail;
    }

    public String getPass() {
        return this.pass;
    }

    public String getfNam() {
        return this.fNam;
    }

    public String getlNam() {
        return this.lNam;
    }

    public String getDate() {
        return this.date;
    }
}
