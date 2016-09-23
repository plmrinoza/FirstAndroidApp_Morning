package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Earl on 22/09/2016.
 */
public class Register extends Activity {
    DatabaseHelper myDb;
    EditText editEmail, editPassword, editCPassword;
    Button btnAddData;
    String email, pass, cpass;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        myDb = new DatabaseHelper(this);

        editEmail = (EditText)findViewById(R.id.EmailReg);
        editPassword = (EditText)findViewById(R.id.PassReg);
        editCPassword = (EditText)findViewById(R.id.ConfirmPass);
        btnAddData = (Button)findViewById(R.id.regibtn);
        AddData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                        if (isValidEmail(email) && isValidPassword(pass))
                        {
                            if(!(pass.equals(cpass))){
                                Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                                editEmail.setText("");
                                editPassword.setText("");
                                editCPassword.setText("");
                            }
                            else
                            {
                                Date curDate = new Date();
                                SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
                                String DateToStr = format.format(curDate);
                                boolean isInserted = myDb.insetData(editEmail.getText().toString(), editPassword.getText().toString(), DateToStr);
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
