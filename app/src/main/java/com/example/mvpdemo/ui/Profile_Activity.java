package com.example.mvpdemo.ui;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mvpdemo.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Profile_Activity extends AppCompatActivity  {

  TextView Bio,Insta,Youtube,Website;
   public ImageView Img_Profile;
    private DatabaseHelper databaseHelper;
    List<Userlist> userlists=new ArrayList<>();
    int ID;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Img_Profile=(ImageView)findViewById(R.id.img_pro);

        Bio=(TextView)findViewById(R.id.txt_bio);
        Insta=(TextView)findViewById(R.id.Txt_insta);
        Youtube=(TextView)findViewById(R.id.txt_youtube);
        Website=(TextView)findViewById(R.id.txt_website);

        databaseHelper=new DatabaseHelper(this);
        back=(ImageView)findViewById(R.id.img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ID=getIntent().getIntExtra("ID",0);
        userlists= databaseHelper.getAllStudent(String.valueOf(ID));


        if(userlists.size()>0) {
            for (int i = 0; i < userlists.size(); i++)
            {
                if(userlists.get(0).getImage().equals("")||userlists.get(0).getImage().equals(null)){
                    Glide.with(this)
                            .load(R.drawable.profile)
                            .override(300, 200)
                            .into(Img_Profile);
                }else {
                    Glide.with(this)
                            .load(userlists.get(0).getImage())
                            .override(300, 200)
                            .into(Img_Profile);
                }

                if(userlists.get(0).getUserbio().equals("")){
                    Bio.setVisibility(View.GONE);
                }else{
                    Bio.setText(userlists.get(0).getUserbio());
                }
                if(userlists.get(0).getInstaurl().equals("")){
                    Insta.setVisibility(View.GONE);
                }else{
                    Insta.setText(userlists.get(0).getInstaurl());
                    Insta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse(userlists.get(0).getInstaurl());
                            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                            likeIng.setPackage("com.instagram.android");

                            try {
                                startActivity(likeIng);
                            } catch (Exception e) {
                                startActivity(new Intent(Intent.ACTION_VIEW,
                                        Uri.parse(userlists.get(0).getInstaurl())));
                            }
                        }
                    });
                }
                if(userlists.get(0).getYoutubelink().equals("")){
                    Youtube.setVisibility(View.GONE);
                }else{
                    Youtube.setText(userlists.get(0).getYoutubelink());
                    Youtube.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse(userlists.get(0).getYoutubelink());
                            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                            likeIng.setPackage("com.youtube.android");

                            try {
                                startActivity(likeIng);
                            } catch (Exception e) {
                                startActivity(new Intent(Intent.ACTION_VIEW,
                                        Uri.parse(userlists.get(0).getYoutubelink())));
                            }
                        }
                    });
                }
                if(userlists.get(0).getWebsitelink().equals("")){
                    Website.setVisibility(View.GONE);
                }else{
                    Website.setText(userlists.get(0).getWebsitelink());
                    Website.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse(userlists.get(0).getWebsitelink());
                            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                            likeIng.setPackage("com.instagram.android");

                            try {
                                startActivity(likeIng);
                            } catch (Exception e) {
                                startActivity(new Intent(Intent.ACTION_VIEW,
                                        Uri.parse(userlists.get(0).getWebsitelink())));
                            }
                        }
                    });
                }

            }

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
