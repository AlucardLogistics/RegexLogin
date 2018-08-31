package com.android.alucard.regexlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "^([a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+)$"
    );

    public static final Pattern VALIDATE = Pattern.compile("^((0?[1-9]|[12][0-9]|3[01])-(0?[13578]|1[02])-(18|19|20|21)[0-9]{2})|((0?[1-9]|[12][0-9]|30)-(0?[469]|11)-(18|19|20|21)[0-9]{2})|((0?[1-9]|1[0-9]|2[0-8])-(0?2)-(18|19|20|21)[0-9]{2})|(29-(0?2)-(((18|19|20|21)(04|08|[2468][048]|[13579][26]))|2000))$");
    public static final Pattern USERNAME = Pattern.compile("^[a-z0-9._\\\\s-]{3,15}$");
    public static final Pattern PASSWORD = Pattern.compile("^[a-z0-9._\\\\s-]{3,15}$");

    EditText etUser, etPass, etEmail, etDate;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        etEmail = findViewById(R.id.etEmail);
        etDate = findViewById(R.id.etDate);

        btSubmit = findViewById(R.id.btSubmit);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString();
                String pass = etPass.getText().toString();
                String email = etEmail.getText().toString();
                String date = etDate.getText().toString();

                login(user, pass, email, date);
            }
        });


    }

    private void login(String user, String pass, String email, String date) {

        checkUser(user);
        Log.d(TAG, "login: user " + user);
        Log.d(TAG, "login: checkUser " + checkUser(user));

        checkPass(pass);
        Log.d(TAG, "login: user " + pass);
        Log.d(TAG, "login: checkUser " + checkUser(pass));

        checkEmail(email);
        Log.d(TAG, "email: " + email);
        Log.d(TAG, "email: " + checkEmail(email));

        checkDate(date);
        Log.d(TAG, "date: " + date);
        Log.d(TAG, "date: " + checkDate(date));

        if(checkUser(user) && checkPass(pass) && checkEmail(email) && checkDate(date)) {
            Toast.makeText(this, "REGEX Validated!", Toast.LENGTH_SHORT).show();
        }



    }

    private boolean checkEmail(String email) {
        if(!EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
            Toast.makeText(this, "Email is invalid", Toast.LENGTH_SHORT).show();
            return false;
        } else
        return true;
    }

    private boolean checkDate(String date) {
        if(!VALIDATE.matcher(date).matches()) {
            Toast.makeText(this, "Date is invalid", Toast.LENGTH_SHORT).show();
            return false;
        } else
        return true;
    }

    private boolean checkUser(String user) {
        if(!USERNAME.matcher(user).matches()) {
            Toast.makeText(this, "User is invalid", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    private boolean checkPass(String pass) {
        if(!PASSWORD.matcher(pass).matches()) {
            Toast.makeText(this, "Password is invalid", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }
}
