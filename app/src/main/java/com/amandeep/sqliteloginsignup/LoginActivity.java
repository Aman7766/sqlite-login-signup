package com.amandeep.sqliteloginsignup;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextView signup_text;
    TextInputLayout emailLayout, passLayout;
    Button login;
    DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        signup_text=findViewById(R.id.ls_bottom);
        emailLayout=findViewById(R.id.log_user);
        passLayout=findViewById(R.id.log_pass);
        login=findViewById(R.id.login);
        dbHelper=new DBHelper(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLayout.getEditText().getText().toString();
                String password = passLayout.getEditText().getText().toString();
                Cursor c=dbHelper.validatelogin(email,password);
                c.moveToFirst();
                if(c==null)
                {
                    Toast.makeText(getApplicationContext(),"Failed Login",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Welcome");
                    builder.setCancelable(true);

                }




            }
        });
        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
