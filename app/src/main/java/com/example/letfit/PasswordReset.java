package com.example.letfit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PasswordReset extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.sendBtn).setOnClickListener(onClickListener); //로그인 버튼 클릭 함수
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {  //클릭 했을 때
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.log_in_btn: //로그인 버튼을 클릭 했을 때
                    send();
                    break;
                case R.id.gotosignup:
                    Intent intent = new Intent(PasswordReset.this, signUpActivity.class);
                    startActivity(intent);
            }
        }
    };

    private void send() {     //회원가입 함수
        String email = ((EditText)findViewById(R.id.user_id)).getText().toString();   //이메일

        if(email.length() > 0 ) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startToast("이메일을 보냈습니다.");
                            }
                        }
                    });
            } else {
            startToast("이메일을 입력해주세요.");
        }
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}