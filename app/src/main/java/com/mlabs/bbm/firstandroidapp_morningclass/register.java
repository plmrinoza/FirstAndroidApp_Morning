package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }
    public void sai(View v) {
        final EditText eText = (EditText) findViewById(R.id.editText);
        final EditText pText = (EditText) findViewById(R.id.editText2);
        final EditText cpText = (EditText) findViewById(R.id.editText3);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passPattern = "([a-zA-Z0-9]+_?)+";
        switch (v.getId()) {
            case R.id.button:

                    if (pText.getText().length() >= 1 && eText.getText().length() >= 1 && cpText.getText().length() >= 1) {
                        if (eText.getText().toString().trim().matches(emailPattern)) {
                            if (pText.getText().toString().trim().matches(passPattern)) {
                                if (pText.getText().length() >= 8) {
                                    if(pText.getText().toString().equals(cpText.getText().toString())){
                                    Toast.makeText(getBaseContext(), "Register successful!", Toast.LENGTH_SHORT).show();
                                    setContentView(R.layout.blank);}
                                    else Toast.makeText(getBaseContext(), "Password should be same!", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(getBaseContext(), "Password is too short!", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getBaseContext(), "Invalid Password!", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getBaseContext(), "Invalid Email!", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getBaseContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();




                break;

        }
    }
}
