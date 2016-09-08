package com.mlabs.bbm.firstandroidapp_morningclass;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MotionEvent;
import android.text.InputType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText email, password;
    Button SHOW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.button);
        email=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        SHOW=(Button)findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                    setContentView(R.layout.activity_main);
                }

            }
        });
        SHOW.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });


    }

    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }
    protected boolean validatePassword(String password) {
        if(password!=null && password.length()>8) {
            return true;
        } else {
            return false;
        }
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
