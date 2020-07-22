package com.example.teamworksus;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    Button btn;
    private ContactDAO mContactDAO;
    String name;
    public final static String kit="com.example.teamwork.kit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_login);

        mContactDAO = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getContactDAO();


        et1=(EditText)findViewById(R.id.email);
        et2=(EditText)findViewById(R.id.password);
        btn=(Button)findViewById(R.id.btnlog);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkemail()){
                    verify();
                }else{
                    Toast.makeText(LoginActivity.this, "Email must be in abc@xyz.com format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean checkemail(){
        String str=et1.getText().toString().trim();
        if(str.contains("@")&&str.contains(".com")){
            return true;
        }
        return false;
    }
    public void verify(){
        String mail=et1.getText().toString().trim();
        String password=et2.getText().toString().trim();
        Contact contact = new Contact();
        try{
            contact = mContactDAO.getContactWithId(mail);

            name=contact.getName();
            String passcode=contact.getPassword();
            if(password.equals(passcode)){
                Toast.makeText(this, "Signin Successful", Toast.LENGTH_SHORT).show();
                openPage();
            }else{
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(LoginActivity.this,"Email not registered",Toast.LENGTH_LONG).show();
        }

    }
    public void openPage(){
        Intent intent=new Intent(LoginActivity.this,PageActivity.class);
        intent.putExtra(kit,name);
        startActivity(intent);
    }

}
