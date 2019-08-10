package com.example.email_validation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class MainActivity extends AppCompatActivity {
Button button3;
EditText email,pass;
AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText)findViewById(R.id.emailtext);
        pass=(EditText)findViewById(R.id.passtext);
        button3=(Button)findViewById(R.id.button3);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        String Password = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(MainActivity.this,R.id.emailtext, Patterns.EMAIL_ADDRESS,R.string.error_email);
        awesomeValidation.addValidation(MainActivity.this,R.id.passtext,Password,R.string.pass_word);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    Intent i=new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this,"Invalid email or password",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}

