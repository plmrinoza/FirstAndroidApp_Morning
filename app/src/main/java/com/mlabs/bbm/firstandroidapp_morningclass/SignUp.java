package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Context context = this;
        final DataBaseAdapter DataBaseAdapter;
        DataBaseAdapter = new DataBaseAdapter(this);

        final EditText EmailSignUp = (EditText) findViewById(R.id.email_signup);
        final EditText PwSignUp = (EditText) findViewById(R.id.pw_signup);
        final EditText ConPwSignUp = (EditText) findViewById(R.id.conpw_signup);
        final Button CreateAccountBtn = (Button) findViewById(R.id.createaccnt_btn);

        CreateAccountBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String Email = EmailSignUp.getText().toString();
                String Password = PwSignUp.getText().toString();
                String confirmPassword = ConPwSignUp.getText().toString();

                if (Email.equals("") || Password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Fill Up required fields",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!Password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match", Toast.LENGTH_LONG)
                            .show();
                    return;
                } else {

                    DataBaseAdapter.registerUser(Email,Password,GetCurrentDateAndTime());
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(SignUp.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }

    /*protected boolean validateEmail(String Email) {
        String Email_Pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(Email_Pattern);
        Matcher matcher = pattern.matcher(Email);
        return matcher.matches();
    }

    protected boolean validatePassword(String Password) {
        if(Password!=null && Password.length()>9) {
            return true;
        } else {
            return false;
        }
    }*/

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String Pw) {
        return Pw.length() >=8;
    }

    public void doLogin() {
        Toast.makeText(getApplicationContext(), "Successfully Logged-in", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SignUp.this, NextScreen.class);

        startActivity(i);
    }

    public String GetCurrentDateAndTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date).toString();


}

}
