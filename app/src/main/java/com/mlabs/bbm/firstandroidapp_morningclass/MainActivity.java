package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn;
        final EditText emailAdd,passWord,Show;



        Show = (EditText)findViewById(R.id.show);
        emailAdd=(EditText)findViewById(R.id.editText);
        passWord=(EditText)findViewById(R.id.editText3);
        btn = (Button)findViewById(R.id.button);
        if (btn!=null){
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){

                    if(passWord.equals("") && emailAdd.equals("")) {
                        Toast.makeText(getBaseContext(),"Email or Password field must not be empty",Toast.LENGTH_SHORT).show();

                    }
                    if (Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$").matcher(emailAdd.getText()).matches()) {

                        if (passWord.getText().length() >= 6) {
                            Intent intent = new Intent(MainActivity.this, Clrclass.class);
                            startActivity(intent);
                            Toast.makeText(getBaseContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getBaseContext(), "invalid login details", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(getBaseContext(), "Invalid Email address!", Toast.LENGTH_SHORT).show();


                }
            });


            Show.setOnTouchListener(new View.OnTouchListener(){
              @Override
                public boolean onTouch(View view, MotionEvent motionEvent){
//                  if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
//                    {
//                        passWord.setTransformationMethod(null);
//                        Show.setText("Hide");
//                        return true;
//                    }
//                  else
//                  {
//                   passWord.setTransformationMethod(new PasswordTransformationMethod());
//                      return false;
//                  }
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        passWord.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        passWord.setTransformationMethod(new PasswordTransformationMethod());
                         return false;
                    default:
                        return false;


                }

             }



            });


        }


    }
}
