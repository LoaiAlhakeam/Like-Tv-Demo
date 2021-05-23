package com.example.likedemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.likedemo.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button loginBtn;
    EditText usernameEditText;
    EditText passwordEditText;
    JSONObject jsonSend;
    CheckBox rememberMeCheck;

    SharedPreferences tokenSharedPerf;
    SharedPreferences.Editor tokenEditor;
    SharedPreferences loginSharedPrefes;
    SharedPreferences.Editor loginPrefsEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.logInButton);
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        rememberMeCheck = (CheckBox) findViewById(R.id.rememberMeCheck);

        jsonSend = new JSONObject();
        loadRememberMe();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                saveRememberMe(rememberMeCheck.isChecked());
            }
        });

        rememberMeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveRememberMe(rememberMeCheck.isChecked());
            }
        });

    }

    //Login Function
    public void login (){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        try {
            jsonSend.put("username",username);
            jsonSend.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("http://10.237.239.2/v4/auth/client")
                .addJSONObjectBody(jsonSend)
                .setTag("test")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            try {
                                tokenSharedPerf = getSharedPreferences("theToken",MODE_PRIVATE);
                                tokenEditor = tokenSharedPerf.edit();

                                JSONObject obj = new JSONObject(response.toString());
                                Log.d("done 1",obj.toString());
                                Log.d("done 2",obj.getString("token"));
                                String token = obj.getString("token");
                                Log.d("done 3",token);
                                tokenEditor.putString("token",token);
                                tokenEditor.apply();
                                Log.d("done 4",tokenSharedPerf.getString("token","0"));
                                Intent allMovieActivityIntent = new Intent(Login.this, AllMovie.class);
                                allMovieActivityIntent.putExtra("token",token);
                                Login.this.startActivity(allMovieActivityIntent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d("test", "onError errorCode : " + error.getErrorCode()+error.toString());
                            Log.d("test", "onError errorBody : " + error.getErrorBody()+error.toString());
                            Log.d("test", "onError errorDetail : " + error.getErrorDetail()+error.toString());

                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("test", "onError errorDetail : " + error.getErrorDetail()+error.toString());
                        }
                    }
                });
    }

    //Remember Me Check
    //Load username and password
    public void loadRememberMe() {
        //loginPrefs key :  saved(Boolean), username, password
        loginSharedPrefes = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginSharedPrefes.edit();

        if(!loginSharedPrefes.getBoolean("saved",false)){
            usernameEditText.setText(" ");
            passwordEditText.setText(" ");
            rememberMeCheck.setChecked(false);
        }else{
            usernameEditText.setText(loginSharedPrefes.getString("username",""));
            passwordEditText.setText(loginSharedPrefes.getString("password",""));
            rememberMeCheck.setChecked(true);
        }
    }
    //Save Username and Password
    public void saveRememberMe(Boolean isChecked){

                if(isChecked){
                    loginPrefsEditor.putBoolean("saved",true);
                    loginPrefsEditor.putString("username",usernameEditText.getText().toString());
                    loginPrefsEditor.putString("password",passwordEditText.getText().toString());
                }else{
                    loginPrefsEditor.clear();
                    loginPrefsEditor.apply();
                    loginPrefsEditor.putBoolean("saved",false);
                }
                loginPrefsEditor.apply();
        };




}

