package com.researchBundle.amidrunk;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.researchBundle.amidrunk.LockScreen;
import com.researchbundle.amidrunk.telephony.R;

import java.lang.reflect.Method;

/**
 * Created by kunsang on 29/6/15.
 */
public class SpeedDial extends AppCompatActivity {


    boolean speakerStatus = false;
    boolean onCallingIdle = true;
    ImageView one, two, three, four, five, six, seven, eight, nine, zero, back;
    Button endCall, speaker;
    static ViewGroup speedDialScreen, phoneCallingScreen;
    AudioManager audioManager;
    LayoutInflater mInflater;
    UserSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session=new UserSession(getApplicationContext());

        audioManager = (AudioManager)
                getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_CALL);
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


        mInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        phoneCallingScreen = (ViewGroup) mInflater.inflate(R.layout.phone_activity, null, false);
        speedDialScreen = (ViewGroup) mInflater.inflate(R.layout.speed_dial, null, false);
        one = (ImageView) speedDialScreen.findViewById(R.id.imageViewOne);
        two = (ImageView) speedDialScreen.findViewById(R.id.imageViewTwo);
        three = (ImageView) speedDialScreen.findViewById(R.id.imageViewThree);
        four = (ImageView) speedDialScreen.findViewById(R.id.imageViewFour);
        five = (ImageView) speedDialScreen.findViewById(R.id.imageViewFive);
        six = (ImageView) speedDialScreen.findViewById(R.id.imageViewSix);
        seven = (ImageView) speedDialScreen.findViewById(R.id.imageViewSeven);
        eight = (ImageView) speedDialScreen.findViewById(R.id.imageViewEight);
        nine = (ImageView) speedDialScreen.findViewById(R.id.imageViewNine);
        zero = (ImageView) speedDialScreen.findViewById(R.id.imageViewZero);
        back = (ImageView) speedDialScreen.findViewById(R.id.speedDialBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LockScreen.wm.removeView(speedDialScreen);
                // Intent i=new Intent(getApplicationContext(),LockScreen.class);
                // startActivity(i);
                finish();

            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.set_me_calling(true);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:8130074378"));
                startActivity(callIntent);
                endCall = (Button) phoneCallingScreen.findViewById(R.id.buttonEndCall);
                speaker = (Button) phoneCallingScreen.findViewById(R.id.buttonSpeaker);
                endCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        disconnectCall(); finish();
                    }
                });

                speaker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (speakerStatus) {
                            speakerStatus = false;
                        } else {
                            speakerStatus = true;
                        }
                        audioManager.setSpeakerphoneOn(speakerStatus);
                    }
                });


                //   finish();


            }
        });
        getWindow().setAttributes(LockScreen.params);
        try {
            LockScreen.wm.addView(speedDialScreen, LockScreen.params);
        } catch (Exception ex) {

        }
        try
        {
            finish();
        }
        catch (Exception ex)
        {}
    }


    public void disconnectCall() {
        try {

            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";
            Class<?> telephonyClass;
            Class<?> telephonyStubClass;
            Class<?> serviceManagerClass;
            Class<?> serviceManagerNativeClass;
            Method telephonyEndCall;
            Object telephonyObject;
            Object serviceManagerObject;
            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
            Method getService = // getDefaults[29];
                    serviceManagerClass.getMethod("getService", String.class);
            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);
            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);
            telephonyObject = serviceMethod.invoke(null, retbinder);
            telephonyEndCall = telephonyClass.getMethod("endCall");
            telephonyEndCall.invoke(telephonyObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

