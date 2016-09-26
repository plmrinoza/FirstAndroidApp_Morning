//SIGN-UP//

package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class SignUp extends Activity {
    EditText UsernameReg, PasswordReg, PasswordConfirm, EmailReg;
    Button Register;
    DataBaseAdapter db;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
       super.onCreate(savedInstanceState);
       setContentView(R.layout.sign_up);

       Context context = this;
       //get Instance of DataBase Adapter
       final DataBaseAdapter db = new DataBaseAdapter(this);
       db.open();

       //get reference views
       final EditText EmailSignUp = (EditText) findViewById(R.id.EmailReg);
       final EditText PwSignUp = (EditText) findViewById(R.id.PasswordReg);
       final EditText ConPwSignUp = (EditText) findViewById(R.id.PasswordConfirm);

       final EditText Firstname = (EditText) findViewById(R.id.FirstName);
       final EditText Lastname = (EditText) findViewById(R.id.LastName);
       final EditText Username = (EditText) findViewById(R.id.UsernameReg);

       final Button Register = (Button) findViewById(R.id.Register);

       Register.setOnClickListener(new View.OnClickListener() {

           public void onClick(View v) {
               //TODO Auto-generate method stub
               String Email = EmailReg.getText().toString();
               String Password = PasswordReg.getText().toString();
               String confirmPassword = PasswordConfirm.getText().toString();
               String TakenUsername = db.getData();
               String Fname = Firstname.getText().toString();
               String Lname = Lastname.getText().toString();
               String Uname = UsernameReg.getText().toString();

               //check if any of the field are vacant
               if (Uname.equals("") || Password.equals("")
                       || confirmPassword.equals("")) {
                   Toast.makeText(getApplicationContext(), "Fill-Up required fields", Toast.LENGTH_LONG).show();
                   return;
               }

               // Check if both passwords matches
               if (!Password.equals(PasswordConfirm)) {
                   Toast.makeText(getApplicationContext(), "Password does not match",
                           Toast.LENGTH_LONG).show();
                   return;
               }
               if (Uname.equals(TakenUsername)) {
                   Toast.makeText(getApplicationContext(), "Username Already Taken", Toast.LENGTH_LONG).show();
                   return;
               } else {
                   // Save data in database
                   db.insertPlayer(Uname, Password, Email);
                   Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                   Intent i = new Intent(SignUp.this, NextScreen.class);

                   startActivity(i);
               }

                if (!PasswordReg.equals(ConPwSignUp.getText().toString())) {
                    ConPwSignUp.setError("Password does not match!");
                }
                if (!validateName(Firstname.getText().toString())) {
                    Firstname.setError("Not a valid First Name");
                }
                if (!validateName(Lastname.getText().toString())) {
                    Lastname.setError("Not a valid Last Name");
                }
                 if (!validateUsername(UsernameReg.getText().toString())) {
                    UsernameReg.setError("Not a valid User Name");
                }
                 if (!validateEmail(EmailReg.getText().toString())) {
                    EmailReg.setError("Not a valid Email");
                } if (!validatePassword(PasswordReg.getText().toString())) {
                    PasswordReg.setError("Not a valid password!");
                }
                else {
                    DataBaseAdapter.registerUser(Fname,Lname,Uname,Email,Password,GetCurrentDateAndTime());
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SignUp.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
   }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static final String Name = "^[a-zA-Z ]+$";
    private Pattern pattern1 = Pattern.compile(Name);
    private Matcher matcher1;

    public boolean validateName(String name) {
        matcher1 = pattern1.matcher(name);
        return matcher1.matches();
    }

    private static final String USERNAME = "^[a-z0-9_-]{3,15}$";
    private Pattern pattern2 = Pattern.compile(USERNAME);
    private Matcher matcher2;

    public boolean validateUsername(String username) {
        matcher2 = pattern2.matcher(username);
        return matcher2.matches();
    }

    public boolean validatePassword(String Pw) {
        return Pw.length() >=8;
    }

    public String GetCurrentDateAndTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date).toString();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    //db.close();
    }



}
