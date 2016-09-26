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

   @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Context context = this;
        final DataBaseAdapter DataBaseAdapter;
        DataBaseAdapter = new DataBaseAdapter(this);

        DataBaseAdapter.open();

        final EditText EmailSignUp = (EditText) findViewById(R.id.email_signup);
        final EditText PwSignUp = (EditText) findViewById(R.id.pw_signup);
        final EditText ConPwSignUp = (EditText) findViewById(R.id.conpw_signup);

        final EditText Firstname = (EditText) findViewById(R.id.FirstName);
        final EditText Lastname = (EditText) findViewById(R.id.LastName);
        final EditText Username = (EditText) findViewById(R.id.UserName);

        final Button CreateAccountBtn = (Button) findViewById(R.id.createaccnt_btn);

        CreateAccountBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String Email = EmailSignUp.getText().toString();
                String Password = PwSignUp.getText().toString();
                String confirmPassword = ConPwSignUp.getText().toString();

                String Fname = Firstname.getText().toString();
                String Lname = Lastname.getText().toString();
                String Uname = Username.getText().toString();

                if (Email.equals("") || Password.equals("")
                        || confirmPassword.equals("")  || Fname.equals("")  ||
                        Lname.equals("")  || Uname.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill Up required fields", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Password.equals(ConPwSignUp.getText().toString())) {
                    ConPwSignUp.setError("Password does not match!");
                }
                if (!validateName(Firstname.getText().toString())) {
                    Firstname.setError("Not a valid First Name");
                }
                if (!validateName(Lastname.getText().toString())) {
                    Lastname.setError("Not a valid Last Name");
                }
                 if (!validateUsername(Username.getText().toString())) {
                    Username.setError("Not a valid User Name");

                  /*   //validate the username or email
                     if (Username == null || Username.isEmpty()){
                         Username.setError(getString(R.string.error_username));
                         Username.requestFocus();
                         return;
                     }
                     //validate the password
                     if (Password == null || Password.isEmpty()){
                         Password.setError(getString(R.string.error_password));
                         return;
                     }
                     return; */
                }
                 if (!validateEmail(EmailSignUp.getText().toString())) {
                    EmailSignUp.setError("Not a valid Email");
                } if (!validatePassword(PwSignUp.getText().toString())) {
                    PwSignUp.setError("Not a valid password!");
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

    //    DataBaseAdapter.close();
    }



}
