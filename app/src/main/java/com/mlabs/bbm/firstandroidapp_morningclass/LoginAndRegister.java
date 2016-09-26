package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ChristianJohn on 8/4/2016.
 */
public class LoginAndRegister {

    private TextView emailTxtView;
    private TextView passwordTxtView;
    private TextView alertPassword;
    private Context context;
    private UsersDataSource usersDataSource;
    private EditText fname, lname, username, email, password, cpassword;

    public LoginAndRegister(Context context, EditText fname, EditText lname, EditText username, EditText email, EditText password, EditText cpassword, TextView alertPassword){
        this.context = context;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpassword = password;
        this.alertPassword = alertPassword;
    }

    public LoginAndRegister(Context context, TextView emailTxtView, TextView passwordTxtView){
        this.context = context;
        this.emailTxtView = emailTxtView;
        this.passwordTxtView = passwordTxtView;
    }

    public boolean validateLogin(EditText emailEditTxt, EditText passwordEditTxt){
        String email = emailEditTxt.getText().toString();
        String password = passwordEditTxt.getText().toString();
        boolean isEmailValid = false, isPasswordCorrect = false;
        User user = null;
        email = email.replace(" ","");
        usersDataSource = new UsersDataSource(context);

        usersDataSource.open();

        user = usersDataSource.getUser(email);
        try {
            if (!user.equals(null)) {
                if (!email.equals(user.getEmail())) {
                    emailTxtView.setText(String.format("%s", "Email is not existing"));

                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailTxtView.setText(String.format("%s", "Invalid email format"));
                    }
                    isEmailValid = false;
                } else {
                    isEmailValid = true;
                }
            }
        }catch(NullPointerException npe){
            Toast.makeText(context, ""+String.format("%s","Not a member? Signup! :)"), Toast.LENGTH_SHORT).show();

        }

        usersDataSource.close();
        Log.v("PASSWORD:",""+user.getPassword());

        if(isEmailValid && password.equals(user.getPassword()))
            isPasswordCorrect = true;
        else if(password.length() == 0) {
            passwordTxtView.setText(String.format("%s","Please enter password"));
        }else if(!isEmailValid && password.length() < 8){
            passwordTxtView.setText(String.format("%s","Password length must be more than 7"));
        }else if(isEmailValid && !password.equals(user.getPassword())){
            passwordTxtView.setText(String.format("%s", "Password is incorrect"));
        }

        if(isEmailValid && isPasswordCorrect)
            return true;
        else
            return false;
    }

    public boolean isRegistrationSuccessful(){
        User user = new User();
        this.usersDataSource = new UsersDataSource(context);
        usersDataSource.open();

        if(fname.getText().toString().equals("")){
            fname.setText(String.format("%s","Please enter first name"));
        }else{
            user.setFname(fname.getText().toString());
        }

        if(lname.getText().toString().equals("")){
            lname.setText(String.format("%s","Please enter last name"));
        }else{
            user.setLname(lname.getText().toString());
        }

        if(username.getText().toString().equals("")){
            username.setText(String.format("%s","Please enter username"));
        }else{
            if(usersDataSource.ifUsernameIsAvailable(username.getText().toString()))
                user.setUname(username.getText().toString());
            else
                username.setText(String.format("%s","Username not available"));
        }

        if(email.getText().toString().equals("")){
            email.setText(String.format("%s","Please enter email address"));
        }else{
            if(usersDataSource.ifEmailIsAvailable(email.getText().toString()))
                user.setEmail(email.getText().toString());
            else
                email.setText(String.format("%s","Try another email"));
        }

        if(password.getText().toString().equals("") && password.getText().toString().length() < 8){
            alertPassword.setText(String.format("%s","Password must be more than 7 characters"));
        }else{
            if(cpassword.getText().toString().equals("") || !password.getText().toString().equals(cpassword.getText().toString())){
                alertPassword.setText(String.format("%s","Passwords do not match"));
            }else{
                user.setPassword(password.getText().toString());
            }
        }

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        user.setDate(formattedDate);
        boolean success = false;
        try{
            if(!user.getFname().equals("") && !user.getLname().equals("") && !user.getUname().equals("") && !user.getEmail().equals("") && !user.getPassword().equals("")) {
                usersDataSource.createUser(user.getFname(), user.getLname(), user.getUname(), user.getEmail(), user.getPassword(), user.getDate());
                usersDataSource.close();
                success = true;
            }
        }catch (NullPointerException npe){
            Toast.makeText(context, ""+String.format("%s","Please complete the registration form"), Toast.LENGTH_SHORT).show();
        }

        return success;
    }
}
