package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {



    Button btnLogin;
    EditText edtCorreo,edtContrasena;
    Switch swRecordar;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();
        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        setCredentiasIsExist();

                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = edtCorreo.getText().toString();
                        String contrasena = edtContrasena.getText().toString();
                        if (login(email, contrasena)){
                            goToMain();
                            saveOnPreferences(email,contrasena);
                        }
                    }
                });
    }



    private void bindUI(){
        btnLogin = findViewById(R.id.btnLogin);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtContrasena = findViewById(R.id.edtContrasena);
        swRecordar = findViewById(R.id.swRecordar);
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidPass(String contrasena){
        return contrasena.length() > 4;
    }
    private boolean login(String correo, String contrasena ){

        if(!isValidEmail(correo)){
            Toast.makeText(this,"Correo Incorrecto", Toast.LENGTH_LONG).show();
            return false;
        }else if (!isValidPass(contrasena)){

            Toast.makeText(this,"contraseñaa Incorrecto", Toast.LENGTH_LONG).show();
            return false;
        }else{
            Toast.makeText(this,"Login Correcto", Toast.LENGTH_LONG).show();
            return true;
        }
    }
    private void goToMain(){

        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void saveOnPreferences(String correo,String contrasena){

        if (swRecordar.isChecked()){

            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("correo",correo);
            edit.putString("contrasena",contrasena);
            //con apply el codigo siga aun no terminada la tarea a diferencia del commit que se detiene al guardar todas las shared Preferences edit.commit();
            edit.apply();

        }

    }
    private void setCredentiasIsExist(){
        String correo = getUserEmailPreferences();
        String contrasena = getUserPassPreferences();
        if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contrasena)){

            edtCorreo.setText(correo);
            edtContrasena.setText(contrasena);

        }
    }
    private String getUserEmailPreferences(){
        return preferences.getString("correo", "");
    }
    private String getUserPassPreferences(){
        return preferences.getString("contrasena", "");
    }

}
