package muz.kumoteamthailand.com.mucyapp.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.JetPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import muz.kumoteamthailand.com.mucyapp.NotificationActivity;
import muz.kumoteamthailand.com.mucyapp.R;
import muz.kumoteamthailand.com.mucyapp.utility.MyAlert;
import muz.kumoteamthailand.com.mucyapp.utility.MyConstant;
import muz.kumoteamthailand.com.mucyapp.utility.ReadAllData;

public class MainFragment extends Fragment{

    //Explicit

    private Handler handler = new Handler();
    private int timeAnInt = 0;
    private boolean resultABoolean = false;
    // Threat runable
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
           // timeAnInt +=1;
           // Log.d("18AugV1", "time-->" + timeAnInt);
           // if (timeAnInt == 10) {
            //    myNotification();
           // }

            checkResult();
            if (resultABoolean) {
                myNotification();
            }else{
                handler.postDelayed(runnable, 1000);

            }



        }
    };


    //
    private void checkResult() {
        try {
            MyConstant myConstant = new MyConstant();
            ReadAllData readAllData = new ReadAllData(getActivity());
            readAllData.execute(myConstant.getUrlTestString());

            String jsonString = readAllData.get();
            Log.d("18AgugV1", "JSON--->" + jsonString);

            JSONArray jsonArray  = new JSONArray(jsonString);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            String resultString = jsonObject.getString("result");
            resultABoolean = Boolean.parseBoolean(resultString);







        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler.postDelayed(runnable, 1000);
      //  myNotification();

//        Regiter Controller
         regiterController();


//        Login Controller
        loginController();


    }//Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userNameEditText = getView().findViewById(R.id.edtUsername);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userNameEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();


                MyAlert myAlert = new MyAlert(getActivity());
                MyConstant myConstant = new MyConstant();
                String[] columStrings = myConstant.getColumn_tm_client();
                ArrayList<String> stringArrayList = new ArrayList<>();
                boolean loginBool = true;


                if (userString.isEmpty() || passwordString.isEmpty()) {
                    myAlert.normalDialog("Have Space","Please fill every blank");

                } else {
                    try {
                        ReadAllData readAllData = new ReadAllData(getActivity());
                        readAllData.execute(myConstant.getUrlReadAllUser());
                        String jsonString = readAllData.get();
                        Log.d("19AugV1", "JsonString ==>" + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);
                        for (int i=0; i<jsonArray.length(); i+=1) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (userString.equals(jsonObject.getString(columStrings[3]))) {
                                loginBool = false;
                                for (int i1=0; i1< columStrings.length; i1+=1) {
                                    stringArrayList.add(jsonObject.getString(columStrings[i1]));
                                }// for

                            }   // if

                        }   // for loop


                        if (loginBool) {
                            myAlert.normalDialog("User False", "No " + userString + "in my database");
                        } else if (passwordString.equals(stringArrayList.get(4))) {
                            Toast.makeText(getActivity(),"Welcome " + stringArrayList.get(1),Toast.LENGTH_SHORT).show();
                        } else {
                            myAlert.normalDialog("Password False","Please try again");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });
    }


    //method onclick register
    private void regiterController() {
        Button button = getView().findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentFragmentMain, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    private void myNotification() {
        Intent intent = new Intent(getActivity(),NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                                                    getActivity(),
                                                     (int) System.currentTimeMillis(),
                                                    intent,
                                                    0 );

        Uri uri = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND);

        Notification notification = new Notification.Builder(getActivity())
                .setContentTitle("This is Title")
                .setContentText("This is text")
                .setSmallIcon(R.drawable.ic_action_noti)
                .setSound(uri)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0,notification);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }

}
