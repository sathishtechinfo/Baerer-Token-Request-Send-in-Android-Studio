package com.example.mvpdemo.ui.LoginPage;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mvpdemo.R;
import com.example.mvpdemo.base.BaseActivity;
import com.example.mvpdemo.data.network.model.LoginResponse;
import com.example.mvpdemo.ui.ClientListActivity;
import com.example.mvpdemo.ui.DatabaseHelper;

import com.example.mvpdemo.ui.Userlist;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;

public class LoginActivity extends BaseActivity implements LoginMvpView {
    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;
    @BindView(R.id.edt_email)
    EditText Edt_Email;
    @BindView(R.id.login)
    Button btnLogin;
    @BindView(R.id.edi_pwd)
    EditText Edt_password;
    RequestQueue requestQueue;
    private static final String URL_ACCESS = "Enter Your Url";
    private DatabaseHelper databaseHelper;
    private Userlist userlist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        userlist=new Userlist();
        databaseHelper=new DatabaseHelper(this);
        mPresenter.onAttach(this);

        setUp();


    }
    @OnClick(R.id.login)
    public void onClearSignupClicked(View v) {
        String email=Edt_Email.getText().toString();
        String password=Edt_password.getText().toString();
        mPresenter.onPostDetails(email,password);

    }
    @Override
    protected void setUp() {



    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void updatepost(LoginResponse loginResponse) {


        if (loginResponse.getToken()!=null){
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest (Request.Method.POST, URL_ACCESS, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try{
                                // Loop through the array elements
                                for(int i=0;i<response.length();i++){
                                    // Get current json object
                                    JSONObject student = response.getJSONObject(i);

                                    // Get the current student (json object) data
                                    String firstName = student.getString("first_name");
                                    String lastName = student.getString("last_name");
                                    String email = student.getString("email");
                                    String mobileno = student.getString("mobile");
                                    String userbio = student.getString("user_bio");
                                    String image = student.getString("image");
                                    String insta = student.getString("instagram_link");
                                    String youtube = student.getString("youtube_link");
                                    String website = student.getString("website_link");
                                    String systemtoken = student.getString("device_token");

                                        if(firstName != "") {
                                            userlist.setFirstname(firstName);
                                            userlist.setLastname(lastName);
                                            userlist.setEmail(email);
                                            userlist.setMobile(mobileno);
                                            userlist.setUserbio(userbio);
                                            userlist.setImage(image);
                                            userlist.setInstaurl(insta);
                                            userlist.setYoutubelink(youtube);
                                            userlist.setWebsitelink(website);
                                            userlist.setDevicetoken(systemtoken);

                                            databaseHelper.addUserList(userlist);

                                        }
                                }
                                Intent accountsIntent = new Intent(LoginActivity.this, ClientListActivity.class);

                                startActivity(accountsIntent);
                                finish();

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("CheckError",error.toString());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + loginResponse.getToken());
                    return headers;
                }
            };

            requestQueue.add(jsonObjectRequest);

        }



    }


}

