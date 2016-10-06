package com.mlabs.bbm.firstandroidapp_morningclass;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    Button btnConfirm, btnView;
    EditText fldUser, fldEmail, fldPass, fldPass2, fldFname, fldLname;
    DbHandler db = new DbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnConfirm = (Button) findViewById(R.id.buttConf);
        btnView = (Button) findViewById(R.id.buttViewA);
        fldUser = (EditText) findViewById(R.id.fldUnam);
        fldEmail = (EditText) findViewById(R.id.fldMail);
        fldPass = (EditText) findViewById(R.id.fldPass);
        fldPass2 = (EditText) findViewById(R.id.fldPass2);
        fldFname = (EditText) findViewById(R.id.fldFnam);
        fldLname = (EditText) findViewById(R.id.fldLnam);

        btnConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String u = fldUser.getText().toString();
                String e = fldEmail.getText().toString();
                String p = fldPass.getText().toString();
                String p2 = fldPass2.getText().toString();
                String f = fldFname.getText().toString();
                String l = fldLname.getText().toString();

                if (isValidUser(u) && isValidEmail(e) && isValidPass(p, p2) && isValidName(f, l)) {
                    Accounts a = new Accounts();
                    a.setAccounts(u, e, p, f, l);
                    db.addAcc(a);
                    fldUser.setText("");
                    fldEmail.setText("");
                    fldPass.setText("");
                    fldPass2.setText("");
                    fldFname.setText("");
                    fldLname.setText("");
                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("id :"+ res.getString(0)+"\n");
                    buffer.append("un :"+ res.getString(1)+"\n");
                    buffer.append("email :"+ res.getString(2)+"\n");
                    buffer.append("pw :"+ res.getString(3)+"\n");
                    buffer.append("first :"+ res.getString(4)+"\n");
                    buffer.append("last :"+ res.getString(5)+"\n");
                    buffer.append("dc :"+ res.getString(6)+"\n\n");
                }

                // Show all data
                showMessage("Data",buffer.toString());
            }
        });

    }

    boolean isValidEmail(String mail) {
        Log.d("Email", mail);
        if  ((android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) && db.checkEmail(mail))
            return true;
        else {
            Toast.makeText(getApplicationContext(), "Invalid Email!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    boolean isValidPass(String pass, String pass2) {
        if (pass.length() >= 8 && pass.equals(pass2))
            return true;
        else {
            Toast.makeText(getApplicationContext(), "Passwords Don't Match!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    boolean isValidName(String first, String last) {
        if (first.matches("^[\\p{L} .'-]+$") && last.matches("^[\\p{L} .'-]+$"))
            return true;
        else {
            Toast.makeText(getApplicationContext(), "Invalid Name!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    boolean isValidUser(String user) {
        if (db.checkUser(user))
            return true;
        else {
            Toast.makeText(getApplicationContext(), "Invalid Username!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
