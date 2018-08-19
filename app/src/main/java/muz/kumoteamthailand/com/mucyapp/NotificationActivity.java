package muz.kumoteamthailand.com.mucyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import muz.kumoteamthailand.com.mucyapp.R;
import muz.kumoteamthailand.com.mucyapp.fragment.NotificationFragment;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFragmentNotification,new NotificationFragment())
                    .commit();
        }
    }//main method
}
