package com.researchBundle.amidrunk;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.researchbundle.amidrunk.telephony.R;

/**
 * Created by kunsang on 29/6/15.
 */
public class HomeScreen extends AppCompatActivity {
    ImageView imageView;
    String uri;
    UserSession session;
    TextView textViewEnable;
    String activatemesage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        imageView = (ImageView) findViewById(R.id.imageViewGlass);
        textViewEnable=(TextView)findViewById(R.id.textViewEnableMessage);
        session = new UserSession(getApplicationContext());
        boolean status = session.getEnableStatus();
        if (status) {
            uri = "@drawable/empty_glass";
            activatemesage=getResources().getString(R.string.activate_message);
            Intent i=new Intent(getApplicationContext(),LockScreen.class);
            startActivity(i);
            finish();
        } else {
            uri = "@drawable/empty_glass";
            activatemesage=getResources().getString(R.string.activate_message);
        }
        textViewEnable.setText(activatemesage);
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = session.getEnableStatus();
                if (status) {
                    session.setEnableStatus(false);
                    uri = "@drawable/empty_glass";
                    activatemesage=getResources().getString(R.string.activate_message);
                } else {
                    session.setEnableStatus(true);

                    Intent i=new Intent(getApplicationContext(),LockScreen.class);
                    startActivity(i);
                    finish();
                }
                textViewEnable.setText(activatemesage);
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                imageView.setImageDrawable(res);
            }
        });

    }


}
