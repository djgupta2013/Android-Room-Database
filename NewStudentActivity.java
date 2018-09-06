package com.wildnet.mvvmroomexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewStudentActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    private EditText email,name,password;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        email=findViewById(R.id.et_email);
        name=findViewById(R.id.et_name);
        password=findViewById(R.id.et_password);
        submit=findViewById(R.id.btn_submit);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent replyIntent = new Intent();
        String studentEmail=email.getText().toString();
        String studentName=name.getText().toString();
        String studentPassword=password.getText().toString();

        if(TextUtils.isEmpty(studentEmail)&&TextUtils.isEmpty(studentName)&&TextUtils.isEmpty(studentPassword)){
            setResult(RESULT_CANCELED,replyIntent);
        }else{
            replyIntent.putExtra(EMAIL,studentEmail );
            replyIntent.putExtra(NAME,studentName );
            replyIntent.putExtra(PASSWORD,studentPassword );
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }
}
