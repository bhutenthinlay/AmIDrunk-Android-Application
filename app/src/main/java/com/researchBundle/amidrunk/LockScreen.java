package com.researchBundle.amidrunk;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.researchbundle.amidrunk.telephony.R;

import org.w3c.dom.Text;

/**
 * Created by kunsang on 29/6/15.
 */
public class LockScreen extends AppCompatActivity {
    ImageView lock, speedDial;
    static ViewGroup lockScreen;
    View dialogView,dialogQuestion1,dialogQuestion2,dialogQuestion3;
    static WindowManager wm;
   // Dialog dialog;
    Button buttonDialogCancel, buttonDialogQuestion;
    TextView textViewDialog;
    Button buttonDialogQuestion1Cancel,buttonDialogQuestion1Ok;
    Button buttonDialogQuestion2Cancel,buttonDialogQuestion2Ok;
    Button buttonDialogQuestion3Cancel,buttonDialogQuestion3Ok;
    TextView textViewTimer1,textViewTimer2,textViewTimer3;
    static WindowManager.LayoutParams params;
    WindowManager.LayoutParams dialog_params;
    String stringUnlock="Unlock";
    String stringQuestion1="#Question 1";
    String stringTimeUp="Your time is up... try again later...!!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
// Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.

        }
         dialog_params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);

        wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);

        LayoutInflater mInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        lockScreen = (ViewGroup) mInflater.inflate(R.layout.lock_screen, null, false);
        //dialog message...
        dialogView = mInflater.inflate(R.layout.dialogbox_message, null, false);
        buttonDialogCancel = (Button) dialogView.findViewById(R.id.buttonDialogBoxCancel);
        buttonDialogQuestion = (Button) dialogView.findViewById(R.id.buttonDialogBoxQuestion);
        textViewDialog=(TextView) dialogView.findViewById(R.id.textViewDialogMessage);
        buttonDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewDialog.setText(getResources().getString(R.string.dialog_Message));
                buttonDialogQuestion.setText(stringQuestion1);
                wm.removeView(dialogView);
                //dialog.dismiss();
            }
        });
        buttonDialogQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buttonDialogQuestion.getText().equals(stringUnlock))
                {
                    UserSession session = new UserSession(getApplicationContext());
                    session.setEnableStatus(false);
                    wm.removeView(dialogView);
                    wm.removeView(lockScreen);
                    finish();
                }
                else {
                    //  UserSession session = new UserSession(getApplicationContext());
                    //  session.setEnableStatus(false);
                    wm.removeView(dialogView);
                    wm.addView(dialogQuestion1, dialog_params);
                    new CountDownTimer(10000, 1000) {//CountDownTimer(edittext1.getText()+edittext2.getText()) also parse it to long

                        public void onTick(long millisUntilFinished) {
                            textViewTimer1.setText("seconds remaining: " + millisUntilFinished / 1000);
                            //here you can have your logic to set text to edittext
                        }

                        public void onFinish() {
                           wm.removeView(dialogQuestion1);
                            wm.addView(dialogView,dialog_params);
                        }
                    }
                            .start();
                    //    dialog.dismiss();
                    //  wm.removeView(lockScreen);

                    //  finish();
                }
            }
        });

        //dialog question 1
        dialogQuestion1 = mInflater.inflate(R.layout.question1, null, false);
        buttonDialogQuestion1Cancel=(Button)dialogQuestion1.findViewById(R.id.buttonQuestion1Cancel);
        buttonDialogQuestion1Ok=(Button)dialogQuestion1.findViewById(R.id.buttonQuestion1OK);
        textViewTimer1=(TextView)dialogQuestion1.findViewById(R.id.textViewQuestion1Time);
        buttonDialogQuestion1Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wm.removeView(dialogQuestion1);
                //dialog.dismiss();
            }
        });
        buttonDialogQuestion1Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  UserSession session = new UserSession(getApplicationContext());
                //  session.setEnableStatus(false);
                wm.removeView(dialogQuestion1);
                wm.addView(dialogQuestion2, dialog_params);
                //    dialog.dismiss();
                //  wm.removeView(lockScreen);

                //  finish();
            }
        });
        //dialog question 2
        dialogQuestion2 = mInflater.inflate(R.layout.question2, null, false);
        buttonDialogQuestion2Cancel=(Button)dialogQuestion2.findViewById(R.id.buttonQuestion2Cancel);
        buttonDialogQuestion2Ok=(Button)dialogQuestion2.findViewById(R.id.buttonQuestion2OK);
        textViewTimer2=(TextView)dialogQuestion2.findViewById(R.id.textViewQuestion2Time);
        buttonDialogQuestion2Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wm.removeView(dialogQuestion2);
                //dialog.dismiss();
            }
        });
        buttonDialogQuestion2Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  UserSession session = new UserSession(getApplicationContext());
                //  session.setEnableStatus(false);
                wm.removeView(dialogQuestion2);
                wm.addView(dialogQuestion3, dialog_params);
                //    dialog.dismiss();
                //  wm.removeView(lockScreen);

                //  finish();
            }
        });
        //dialog question 3
        dialogQuestion3 = mInflater.inflate(R.layout.question3, null, false);
        buttonDialogQuestion3Cancel=(Button)dialogQuestion3.findViewById(R.id.buttonQuestion3Cancel);
        buttonDialogQuestion3Ok=(Button)dialogQuestion3.findViewById(R.id.buttonQuestion3OK);
        textViewTimer3=(TextView)dialogQuestion3.findViewById(R.id.textViewQuestion3Time);
        buttonDialogQuestion3Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wm.removeView(dialogQuestion3);
                //dialog.dismiss();
            }
        });
        buttonDialogQuestion3Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wm.removeView(dialogQuestion3);
                buttonDialogQuestion.setText(stringUnlock);
                textViewDialog.setText(getResources().getString(R.string.dialog_Message_correct));
                wm.addView(dialogView, dialog_params);

            }
        });
        lock = (ImageView) lockScreen.findViewById(R.id.imageViewUnlock);
        speedDial = (ImageView) lockScreen.findViewById(R.id.imageViewSpeedDial);

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              // dialog = new Dialog(LockScreen.this);

                wm.addView(dialogView, dialog_params);


              //  dialog.show();

               /* UserSession session=new UserSession(getApplicationContext());
                session.setEnableStatus(false);
                wm.removeView(speedDialScreen);
                finish();*/

            }
        });
        speedDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(getApplicationContext(), SpeedDial.class));
                // wm.removeView(speedDialScreen);
                //finish();
            }
        });
        getWindow().setAttributes(params);
        try {
            wm.removeView(SpeedDial.speedDialScreen);
        } catch (Exception ex) {
        }
        try {
            wm.removeView(SpeedDial.phoneCallingScreen);
        } catch (Exception ex) {
        }
        try {
            wm.addView(lockScreen, params);
        } catch (Exception ex) {

        }

    }


}
