package com.mlabs.bbm.firstandroidapp_morningclass;

import android.widget.TextView;

/**
 * Created by ChristianJohn on 8/4/2016.
 */
public class Validation {

    private TextView emailTxtView;
    private TextView passwordTxtView;

    public Validation(TextView emailTxtView, TextView passwordTxtView){
        this.emailTxtView = emailTxtView;
        this.passwordTxtView = passwordTxtView;
    }

    public boolean ifCorrectEmailFormat(CharSequence email){
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailTxtView.setText("Invalid email");
            return false;
        }else{
            return true;
        }
    }

    public boolean ifEmailMatched(String email){
        String eemail = "john_aparejado@yahoo.com";

        if(email.equals(eemail))
            return true;
        else {
            emailTxtView.setText("Email is not existing");
            return false;
        }
    }

    public boolean ifPLengthIsSufficient(String password, boolean isEmailValid){
        if(password.length() >= 8)
            return true;
        else if(password.length() == 0) {
            passwordTxtView.setText("Please enter password");
            return false;
        }else{
            if(isEmailValid) {
                passwordTxtView.setText("Password is incorrect");
            }
            else if(!isEmailValid) {
                passwordTxtView.setText("Password length must be more than 7");
            }
            return false;
        }

    }

    public boolean ifPasswordMatched(String password){
        String epassword = "aaaaaaaa";

        if(password.equals(epassword))
            return true;
        else{
            passwordTxtView.setText("Password is incorrect");
            return false;
        }
    }
}
