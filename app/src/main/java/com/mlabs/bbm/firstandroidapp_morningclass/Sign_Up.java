package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Sign_Up extends AppCompatActivity {

    Button Create_Acct;
    EditText Email, Pass_R, CPass_R, FnameR, LnameR, UnameR;
    Context context = this;
    DataBaseAdapter DataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);



        DataBaseAdapter = new DataBaseAdapter(this);
        DataBaseAdapter = DataBaseAdapter.open();
        Email = (EditText) findViewById(R.id.eMail);
        Pass_R = (EditText) findViewById(R.id.rPass);
        CPass_R = (EditText) findViewById(R.id.rCpass);
        FnameR = (EditText) findViewById(R.id.Fname);
        LnameR = (EditText) findViewById(R.id.Lname);
        UnameR = (EditText) findViewById(R.id.UN);

        Create_Acct = (Button) findViewById(R.id.bCreate);
        Create_Acct.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = Email.getText().toString();
                String password = Pass_R.getText().toString();
                String confirmPassword = CPass_R.getText().toString();

                if (validEmailadd(Email.getText().toString()) && validPassword( Pass_R.getText().toString(), CPass_R.getText().toString())) {
                    Intent intent = new Intent(Sign_Up.this, MainActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Invalid Email Format or Password < 8", Toast.LENGTH_LONG).show();




                if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                else
                {

                    DataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
    }

    boolean validEmailadd(String x){
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(x).matches())
        {
            return true;
        }
        else
            return false;
    }

    boolean validPassword(String y, String z){
        if(y.length() >= 8 && y.length() !=0 && z.length() >=8 && z.length() !=0)
        {
         return true;
        }
        else
            return false;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        DataBaseAdapter.close();


    }
}
