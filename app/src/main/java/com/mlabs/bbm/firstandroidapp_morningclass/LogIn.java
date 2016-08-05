package com.mlabs.bbm.firstandroidapp_morningclass;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


/**
 * Created by lauren on 05/08/2016.
 */
public class LogIn extends Activity{
    private EditText editU;
    private EditText editP;

    public boolean ValidPassword(String Password){
    if (Password.length()<8) {
        return false;
    }else {return true;}
    }

    public boolean ValidUsername(String Username) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(Username);
        return m.matches();
    }

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editU = (EditText)findViewById(R.id.editPassword);
        editP = (EditText)findViewById(R.id.editUsername);

        findViewById(R.id.btnLogIn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final String Username = editU.getText().toString();
                final String Password = editP.getText().toString();
                if (!ValidUsername(Username)){
                    editU.setError("Invalid Username!");
                }

                if (!ValidPassword(Password)){
                    editP.setError("Invalid Password!");
                }
                if ((ValidUsername(Username)&& ValidPassword(Password)) == true){
                    Intent intent=new Intent(LogIn.this,Next.class);
                    startActivity(intent);

                }

            }
        });
    }
}
