package com.example.cwiczenia;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity{



    private EditText email, password;
    private Button btn_login;
    private Button btn_zadanie1;
    private ProgressBar loading;
    private static String URL_LOGIN ="http://192.168.0.38/cwiczenia/login1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password =  findViewById(R.id.password);
        loading = findViewById(R.id.loading);
        btn_login = findViewById(R.id.btn_login);
        btn_zadanie1 = findViewById(R.id.zadanie1);
        btn_zadanie1.setOnClickListener(new View.OnClickListener(){

        //TODO : ZADANIE 1

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WelcomeScreen.class);
                startActivity(intent);

            }

        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if(!mEmail.isEmpty() || !mPass.isEmpty()){
                    Login(mEmail, mPass);

                }else{
                    email.setError("Please insert email");
                    password.setError("Please insert password");
                }
            }
        });



    }



    //TODO:  ZADANIE 2
    private  void Login(final String email, final String password){
        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            // JSONArray jsonArray = jsonObject.getString("login");

                            if(success.equals("1")){

                                ///for(int i=0; 1<jsonArray.length(); i++) {
                                //JSONObject object = jsonArray.getJSONObject(i);

                                    /*String name = object.getString("name").trim();
                                    String email  = object.getString("email").trim();*/

                                String email = jsonObject.getString("email");


                                //displaying all paramiters on login screen
                                Toast.makeText(LoginActivity.this,
                                        "Success Login. \nHello "
                                                , Toast.LENGTH_SHORT)
                                        .show();
                                Intent intent = new Intent(LoginActivity.this, WelcomeScreen.class);

                                intent.putExtra("email", email);

                                startActivity(intent);

                                loading.setVisibility(View.GONE);
                                btn_login.setVisibility(View.VISIBLE);
                                //}
                            }else{
                                Toast.makeText(LoginActivity.this,
                                        "Login Error \nWrong Email or Password"

                                        , Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }catch(JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Error JSON "+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }





}

