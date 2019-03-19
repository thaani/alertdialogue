package com.example.alertdialogue;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button closeButton;
    Button secondalert;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        closeButton = (Button) findViewById(R.id.button);
        secondalert=(Button)findViewById(R.id.second);
        

        builder = new AlertDialog.Builder(this);

        secondalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
                final EditText etUsername = alertLayout.findViewById(R.id.et_username);
                final EditText etEmail = alertLayout.findViewById(R.id.et_email);
                final CheckBox cbToggle = alertLayout.findViewById(R.id.cb_show_pass);
                cbToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // to encode password in dots
                            etEmail.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            // to display the password in normal text
                            etEmail.setTransformationMethod(null);
                        }
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Info");
                // this is set the view from XML inside AlertDialog
                builder.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                builder.setCancelable(false);
                builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user = etUsername.getText().toString();
                        String pass = etEmail.getText().toString();
                        Toast.makeText(getBaseContext(), "Username: " + user + " Email: " + pass, Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }
                });
                AlertDialog alerting = builder.create();
                //Setting the title manually
                // alert.setTitle("AlertDialogExample");
                alerting.show();


            }
        });









        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);
                builder.setMessage("Do you want to close this application ?").setCancelable(false)
                         .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int id) {
                                 finish();
                                 Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                         Toast.LENGTH_SHORT).show();

                             }
                         })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
               // alert.setTitle("AlertDialogExample");
                alert.show();


            }
        });

    }
}
