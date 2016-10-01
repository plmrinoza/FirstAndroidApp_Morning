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
import android.text.InputFilter;
import android.text.Spanned;


/**
 * Created by homer on 9/27/2016.
 */
public class register extends Activity {
    DatabaseHelper myDb;
    EditText editEmail, editpassword, editcpassword, editfname, editlname, edituname;
    Button btnAddData;
    String email, password, cpassword;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reg);
        myDb = new DatabaseHelper(this);

        editfname = (EditText)findViewById(R.id.FnameReg);
        editlname = (EditText)findViewById(R.id.LnameReg);
        edituname = (EditText)findViewById(R.id.UnameReg);
        editEmail = (EditText)findViewById(R.id.EmailReg);
        editpassword = (EditText)findViewById(R.id.PassReg);
        editcpassword = (EditText)findViewById(R.id.ConfirmPass);
        btnAddData = (Button)findViewById(R.id.regibtn);

        //To restrict Fname and Lname edittext from accepting numbers as well as special characters
        editfname.setFilters(new InputFilter[]{
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
        editlname.setFilters(new InputFilter[]{
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
                                          public void onClick(View v) {
                                              email = editEmail.getText().toString();
                                              password = editpassword.getText().toString();
                                              cpassword = editcpassword.getText().toString();
                                              if (!isValidEmail(email)) {
                                                  editEmail.setError("Invalid Email");
                                              }
                                              if (!isValidPassword(password)) {
                                                  editpassword.setError("Invalid Password");
                                              }

                                              //Checks if passwords match after confirming the email and passwords meet the required format
                                              if (isValidEmail(email) && isValidPassword(password))
                                              {
                                                  if(!(password.equals(cpassword))){
                                                      Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                                                      editcpassword.requestFocus();
                                                  }
                                                  else
                                                  {
                                                      Date curDate = new Date();
                                                      SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
                                                      String DateToStr = format.format(curDate);
                                                      boolean isInserted = myDb.insetData(editfname.getText().toString(), editlname.getText().toString(), edituname.getText().toString(),editEmail.getText().toString(), editpassword.getText().toString(), DateToStr);
                                                      if(isInserted == true)
                                                      {
                                                          Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                                          Intent intent = new Intent(register.this, Login.class);
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
