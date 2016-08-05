package com.mlabs.bbm.firstandroidapp_morningclass;

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

    // User name
    private EditText TFuname;
    // Password
    private EditText TFpassword;
    // Sign In
    private Button BLogin;
    // Message
    private TextView StatMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        TFuname = (EditText) findViewById(R.id.TFuname);
        TFpassword = (EditText) findViewById(R.id.TFpassword);
        BLogin = (Button) findViewById(R.id.BLogin);
        StatMsg = (TextView) findViewById(R.id.StatMsg);

        BLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                // Stores User name
                String username = String.valueOf(TFuname.getText());
                // Stores Password
                String password = String.valueOf(TFpassword.getText());

                // Validates the User name and Password for girardeugenio@gmail.com, 080295
                if (username.equals("girardeugenio@gmail.com") && password.equals("080295")) {
                    StatMsg.setText("Access Granted!");

                    Intent intent = new Intent(MainActivity.this,Loggedin.class);
                    startActivity(intent);

                } else {
                    StatMsg.setText("Access Denied!");
                }
            }
        });
    }

}
