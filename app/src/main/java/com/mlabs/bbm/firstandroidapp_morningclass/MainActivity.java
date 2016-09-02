package com.mlabs.bbm.firstandroidapp_morningclass;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MotionEvent;
import android.content.Intent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.view.View.OnClickListener;
import android.util.Log;
import android.text.method.PasswordTransformationMethod;
public class MainActivity extends AppCompatActivity {
    EditText t1,t2;
    Button bt1;
    TextView ta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText)findViewById(R.id.editText);
        t2=(EditText)findViewById(R.id.editText2);
      ta = (TextView)findViewById(R.id.textView3);
        bt1=(Button)findViewById(R.id.button);


        bt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub

                final  String email = t1.getText().toString();
                final  String password = t2.getText().toString();
                if (!validate(email)) {

                    t1.setError("Invalid Email") ;


                }

                if(!validatepass(password )) {
                    t2.setError("Invalid Password") ;
                }

                if (validate(email)  && validatepass(password) )
                {

                    Intent intent = new Intent(getApplicationContext(), NextScreen.class);
                    startActivity(intent);

                }

            }



        });

        ta.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("event", "down");
                        t2.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("event", "up");
                        t2.setTransformationMethod(new PasswordTransformationMethod());
                        return true;
                }
                return false;
            }
        });
    





}
    private boolean validate(String email)
    {
        String em_pat="pr.luniza@gmail.com";
        Pattern pattern =Pattern.compile(em_pat ) ;
        Matcher matcher =pattern.matcher(email) ;
        return matcher.matches();
    }






    private boolean validatepass(String password) {
        if (password != null && password.length() > 7)
        {

            return true;
        }
        return false;
    }



}
