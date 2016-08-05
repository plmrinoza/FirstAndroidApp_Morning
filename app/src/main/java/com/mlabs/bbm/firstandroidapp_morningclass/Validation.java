package com.mlabs.bbm.firstandroidapp_morningclass;

import android.util.Log;
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

    public boolean validateLogin(String email, String password){
        boolean isEmailValid = false, isPasswordCorrect = false;
        String myemail = "john_aparejado@yahoo.com";
        String mypassword = "aaaaaaaa";
        email = email.replace(" ","");

        if(!email.equals(myemail)){
            emailTxtView.setText(String.format("%s","Email is not existing"));

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailTxtView.setText(String.format("%s","Invalid email format"));
            }
            isEmailValid = false;
        }
        else{
            isEmailValid = true;
        }

        if(password.length() >= 8 && password.equals(mypassword))
            isPasswordCorrect = true;
        else if(password.length() == 0) {
            passwordTxtView.setText(String.format("%s","Please enter password"));
            isPasswordCorrect = false;
        }else{
            if(isEmailValid) {
                passwordTxtView.setText(String.format("%s","Password is incorrect"));
            }
            else if(!isEmailValid) {
                passwordTxtView.setText(String.format("%s","Password length must be more than 7"));
            }
            isPasswordCorrect = false;
        }

        if(isEmailValid && isPasswordCorrect)
            return true;
        else
            return false;
    }
}
