package muz.kumoteamthailand.com.mucyapp.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class AddNewUser extends AsyncTask<String ,Void,String> {

    private Context context;

    public AddNewUser(Context context) {
        this.context = context;


    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd","true")
                    .add("client_name",strings[0])
                    .add("client_lastname",strings[1])
                    .add("client_username",strings[2])
                    .add("client_password",strings[3])
                    .add("client_email",strings[4])
                    .add("client_phone",strings[5])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[6]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
}
