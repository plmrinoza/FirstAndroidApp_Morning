package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
EditText t1,t2;
Button bt1;
    TextView ta,tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText)findViewById(R.id.editText);
        t2=(EditText)findViewById(R.id.editText2);




        bt1=(Button)findViewById(R.id.button);


        bt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                //  Intent i = new Intent(getApplicationContext(),NextScreen.class);
                //     startActivity(i);

                Intent intent = new Intent(getApplicationContext(), NextScreen.class);
                startActivity(intent);

            }
        });

    }


}
