package com.mlabs.bbm.firstandroidapp_morningclass;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MotionEvent;
import android.content.Intent;
import android.util.Log;
import android.text.method.PasswordTransformationMethod;
public class NextScreen extends AppCompatActivity {
    Button btt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nextscreen);
        btt = (Button) findViewById(R.id.button2);


        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub

                Intent intent2 = new Intent(getApplicationContext(), OnTouchActivity.class);
                startActivity(intent2);


            }

        });
    }
}