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

    public String GetCurrentDateAndTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date).toString();


}

}
