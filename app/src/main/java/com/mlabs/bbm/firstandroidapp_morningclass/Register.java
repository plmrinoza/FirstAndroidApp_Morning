package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by Earl on 22/09/2016.
 */
public class Register extends Activity {
    DatabaseHelper myDb, db;
    EditText editEmail, editPassword, editCPassword, editFname, editLname, editUname;
    Button btnAddData;
    String email, pass, cpass, uname;
    Context CTX = this;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        myDb = new DatabaseHelper(this);


        editFname = (EditText)findViewById(R.id.FnameReg);
        editLname = (EditText)findViewById(R.id.LnameReg);
        editUname = (EditText)findViewById(R.id.UnameReg);
        editEmail = (EditText)findViewById(R.id.EmailReg);
        editPassword = (EditText)findViewById(R.id.PassReg);
        editCPassword = (EditText)findViewById(R.id.ConfirmPass);
        btnAddData = (Button)findViewById(R.id.regibtn);

        editFname.setFocusable(true);

        //To restrict Fname and Lname edittext from accepting numbers as well as special characters
        editFname.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                        if (charSequence.equals("")){
                            return charSequence;
                        }
                        if (charSequence.toString().matches("[a-zA-Z ]+")){
                            return charSequence;
                        }
                        return "";
                    }
                }
        });
        editLname.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                        if (charSequence.equals("")){
                            return charSequence;
                        }
                        if (charSequence.toString().matches("[a-zA-Z ]+")){
                            return charSequence;
                        }
                        return "";
                    }
                }
        });
        AddData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        uname = editUname.getText().toString();
                        email = editEmail.getText().toString();
                        pass = editPassword.getText().toString();
                        cpass = editCPassword.getText().toString();
                        if (!isValidEmail(email)) {
                            editEmail.setError("Invalid Email");
                        }
                        if (!isValidPassword(pass)) {
                            editPassword.setError("Invalid Password");
                        }

                        //Checks if passwords match after confirming the email and passwords meet the required format
                        db = new DatabaseHelper(CTX);
                        Cursor cr = db.getInfo(db);
                        cr.moveToFirst();
                        boolean unamestat = false, emailstat = false;
                        do {
                            if ((uname.equals(cr.getString(3)))) //checks database if entered information is present
                            {
                                unamestat = true;
                            }
                            if ((email.equals(cr.getString(4)))){
                                emailstat = true;
                            }
                        } while (cr.moveToNext());

                        if(unamestat == false && emailstat == false)
                        {
                            if (isValidEmail(email) && isValidPassword(pass))
                            {
                                if(!(pass.equals(cpass))){
                                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                                    editCPassword.requestFocus();
                                }
                                else
                                {
                                    Date curDate = new Date();
                                    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
                                    String DateToStr = format.format(curDate);
                                    boolean isInserted = myDb.insetData(editFname.getText().toString(), editLname.getText().toString(), editUname.getText().toString(),editEmail.getText().toString(), editPassword.getText().toString(), DateToStr);
                                    if(isInserted == true)
                                    {
                                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }
                        }
                        else
                        {
                            if(unamestat == true){
                                editUname.setError("Username Already in Use");
                            }
                            if(emailstat == true){
                                editEmail.setError("Email Already in Use");
                            }
                        }

                    }
                }
        );
    }
    //Regex for email and password pattern
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass){
        if (pass !=null && pass.length() > 7){
            return true;
        }
        return false;
    }
}
