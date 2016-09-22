package com.mlabs.bbm.firstandroidapp_morningclass;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText email, pass, cPass;
    Button reg;
    DbHandler helper = new DbHandler(this);
    Pattern emailP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_up);

        email = (EditText) findViewById(R.id.emailReg);
        pass = (EditText) findViewById(R.id.passReg);
        cPass = (EditText) findViewById(R.id.cPassReg);
        reg = (Button) findViewById(R.id.reg);
        emailP = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        findViewById(R.id.reg).setOnClickListener(regClickListener);
    }

        View.OnClickListener regClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String emailStr = email.getText().toString();
                String pwStr = pass.getText().toString();
                String cPWStr = cPass.getText().toString();

                if(validate(emailStr,pwStr,cPWStr)){
                    Accounts a = new Accounts();
                    a.setEmail(emailStr);
                    a.setPass(pwStr);
                    a.setDate();

                    helper.regAccount(a);

                    Toast.makeText(getApplicationContext(),"Account Registered!",Toast.LENGTH_SHORT);
                }else
                    Toast.makeText(getApplicationContext(),"Invalid Email/Password!",Toast.LENGTH_SHORT);
            }
        };

    Boolean validate(String email, String pw, String cpw) {
        Matcher m;
        m = emailP.matcher(email);
        if (m.matches() && pw.length()>=8){
            if (pw.equals(cpw)) {
            }
            return true;
        }
        else {
            return false;
        }
    }
}
