package com.researchBundle.amidrunk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by kunsang on 30/6/15.
 */
public class PhoneStateReciever extends BroadcastReceiver {
    String TAG="phoneStateReciever...";
    UserSession session;
    @Override
    public void onReceive(Context context, Intent intent) {
            session=new UserSession(context);

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state))
        {
            if(session.getEnableStatus()) {
                try {
                    LockScreen.wm.removeView(LockScreen.lockScreen);

                } catch (Exception ex) {

                }
                try {
                    LockScreen.wm.removeView(SpeedDial.speedDialScreen);

                } catch (Exception ex) {

                }

            }


            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            Log.i(TAG, "phone ringing...:" + phoneNumber);
        }
        if (TelephonyManager.EXTRA_STATE_IDLE.equals(state))
        {

            boolean lockStatus=session.getEnableStatus();
            if(lockStatus) {
                session.set_me_calling(false);
              try {
                    LockScreen.wm.removeView(SpeedDial.speedDialScreen);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    LockScreen.wm.removeView(SpeedDial.phoneCallingScreen);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    LockScreen.wm.addView(LockScreen.lockScreen, LockScreen.params);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }

            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            Log.i(TAG, "phone idle...:" + phoneNumber);
        }
        if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state))
        {
            if(session.getEnableStatus() && !session.get_me_calling()) {

                try {
                    LockScreen.wm.removeView(SpeedDial.speedDialScreen);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    LockScreen.wm.removeView(LockScreen.lockScreen);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    LockScreen.wm.addView(SpeedDial.phoneCallingScreen, LockScreen.params);
                    Log.i(TAG,"Phone Screen activated");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.i(TAG,ex.getMessage());
                }
            }

            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            Log.i(TAG, "call picked up...:" + phoneNumber);
        }


        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){

            if(session.getEnableStatus())
            {
                session.set_me_calling(true);
                try {
                    LockScreen.wm.addView(SpeedDial.phoneCallingScreen, LockScreen.params);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            Log.i(TAG, "out going call:" + phoneNumber);

        }

    }
}
