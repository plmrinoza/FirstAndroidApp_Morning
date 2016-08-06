
package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.app.Activity;
        import android.view.Menu;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText l1;
    private EditText l2;
    private Button b1;
    private TextView stm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1 = (EditText) findViewById(R.id.l1);
        l2 = (EditText) findViewById(R.id.l2);
        b1 = (Button) findViewById(R.id.b1);
        stm = (TextView) findViewById(R.id.stm);

        b1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = String.valueOf(l1.getText());
                String password = String.valueOf(l2.getText());

                if (username.equals("jpeg_26@yahoo.com") && password.equals("122692")) {
                    stm.setText("Access Granted!");
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);

                        } else {
                            stm.setText("Access Denied!");
                                           }
                                  }
                });
    }

}