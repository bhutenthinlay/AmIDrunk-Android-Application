package com.researchBundle.amidrunk;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserSession {

    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "AndroidExamplePref";


    // All Shared Preferences Keys
    public static final String KEY_Enable_Status = "enable_status";
    public static final String KEY_Me_Calling = "Me_Calling";

    // Constructor
    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public boolean getEnableStatus()
    {
        return pref.getBoolean(KEY_Enable_Status, false);
    }

    public void setEnableStatus(boolean status) {
        editor.putBoolean(KEY_Enable_Status, status);
        editor.commit();
    }

    public boolean get_me_calling()
    {
        return pref.getBoolean(KEY_Me_Calling, false);
    }

    public void set_me_calling(boolean status) {
        editor.putBoolean(KEY_Me_Calling, status);
        editor.commit();
    }
}