package com.example.teamworksus;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText emails;
    EditText name;
    EditText pass;
    EditText conpass;
    Button btn;
    private ContactDAO mContactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_signup);

        mContactDAO = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getContactDAO();

        emails = (EditText) findViewById(R.id.emails);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        conpass=(EditText)findViewById(R.id.conpass);
        btn=(Button)findViewById(R.id.btnsin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkemail()){
                    if(checkname()){
                        if(confirmpass()){
                            save();
                        }else{
                            Toast.makeText(SignupActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(SignupActivity.this, "Name must be alphabets only", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignupActivity.this, "Email must be in abc@xyz.com format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean checkemail(){
        String str=emails.getText().toString().trim();
        if(str.contains("@")&&str.contains(".com")){
            return true;
        }
        return false;
    }
    public boolean checkname(){
        String str=name.getText().toString();
        if((str != null)
                && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$"))) return true;
        return false;
    }
    public boolean confirmpass(){
        String str=pass.getText().toString().trim();
        String ptr=conpass.getText().toString().trim();

        if(str.equals(ptr)){
            return true;
        }
        return false;
    }
    public void save(){
        String mail=emails.getText().toString().trim();
        String username=name.getText().toString().trim();
        String passcode=pass.getText().toString().trim();

        Contact contact = new Contact();
        contact.setMailid(mail);
        contact.setName(username);
        contact.setPassword(passcode);
        //Insert to database
        try {
            mContactDAO.insert(contact);
            setResult(RESULT_OK);
            String tex="Thank you for registration "+username;
            Toast.makeText(this, tex, Toast.LENGTH_SHORT).show();
            finish();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(SignupActivity.this, "You are already registered", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

}
