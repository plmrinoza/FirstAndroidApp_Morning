package com.mlabs.bbm.firstandroidapp_morningclass;

/*import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}*/

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextUtils;
        import android.text.TextWatcher;
        import android.text.method.PasswordTransformationMethod;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText email, password;
    Boolean showPassword=true;
    TextView show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.Blogin);
        email=(EditText)findViewById(R.id.TFusername);
        password=(EditText)findViewById(R.id.TFpassword);
        show = (TextView)findViewById(R.id.Tshow);


        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("iampatrick.turalba@yahoo.com") &&

                        password.getText().toString().equals("captainpatrick")){
                    Toast.makeText(getApplicationContext(), "verifying...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Activity3.class);
                    startActivity(intent);

                }else if(isValidEmail(email.getText().toString())==false || password.getText().toString().length()<8){
                    if(isValidEmail(email.getText().toString())==false){
                        Toast.makeText(getApplicationContext(), "Invalid E-mail!", Toast.LENGTH_SHORT).show();
                        email.getText().clear();
                        email.requestFocus();
                    }

                    if(password.getText().toString().length()<8){
                        Toast.makeText(getApplicationContext(), "Provide at least 8 characters long Password!", Toast.LENGTH_SHORT).show();
                        password.getText().clear();
                        password.requestFocus();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "E-mail and Password does not match!", Toast.LENGTH_SHORT).show();
                    email.getText().clear();
                    password.getText().clear();
                    email.requestFocus();
                }
            }
        });
        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        password.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        return false;
                }
                return false;
            }

        });

}




    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
        protected void onPause(){
            super.onPause();
            finish();
        }
}
