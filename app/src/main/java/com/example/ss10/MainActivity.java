package com.example.ss10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ss10.database.AppDatabase;
import com.example.ss10.database.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edUser,edDes;
    Spinner spinner;
    CheckBox ckAgree;
    Button btRegister;
    String gender = "Male";
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= AppDatabase.getAppDatabase(this);

        ckAgree = findViewById(R.id.ck);
        edUser = findViewById(R.id.edUser);
        edDes = findViewById(R.id.edDes);
        spinner = findViewById(R.id.spinner);
        btRegister = findViewById(R.id.btRegister);
        btRegister.setOnClickListener(this);

        String[] listGender = {"Male","Female","Unknow"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listGender);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemSelected:"+listGender[i]);
                gender = listGender[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void onRegister(){
        if (!validate()){
            return;
        }
        User user = new User();
        user.username=edUser.getText().toString();
        user.des=edDes.getText().toString();
        user.gender=gender;
        long id=db.userDao().inserUser(user);
        if (id>0){
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        }
        goToListUser();
    }
    private void goToListUser(){
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }

    private boolean validate() {
        String mes = null;
        if (edUser.getText().toString().trim().isEmpty()) {
            mes = "chưa nhập username";
        } else if (edDes.getText().toString().trim().isEmpty()) {
            mes = "chưa nhập giới thiệu";
        } else if (!ckAgree.isChecked()) {
            mes = "bạn phải đồng ý điều khoản";
        }
        if (mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btRegister:onRegister();
            break;
            default:break;
        }
    }
}