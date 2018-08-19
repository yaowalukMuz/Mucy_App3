package muz.kumoteamthailand.com.mucyapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import muz.kumoteamthailand.com.mucyapp.R;
import muz.kumoteamthailand.com.mucyapp.utility.MyAlert;

public class RegisterFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Reister Controller
        reisterController();


    }//Main method


    private void reisterController() {
        Button button = getActivity().findViewById(R.id.btnRegister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText laseNameEditText = getView().findViewById(R.id.edtLastName);
                EditText emailEditText = getView().findViewById(R.id.edtEmail);
                EditText phoneEditText = getView().findViewById(R.id.edtPhone);
                EditText usernameEditText = getView().findViewById(R.id.edtUsername);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);


                String nameString = nameEditText.getText().toString().trim();
                String lastNameString = laseNameEditText.getText().toString().trim();
                String emailString = emailEditText.getText().toString().trim();
                String phoneString = phoneEditText.getText().toString().trim();
                String usernameString = usernameEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                MyAlert myAlert = new MyAlert(getActivity());


                if (nameString.isEmpty() || lastNameString.isEmpty() || emailString.isEmpty() || phoneString.isEmpty() || usernameString.isEmpty() || passwordString.isEmpty()) {
                myAlert.normalDialog("Have Space","Please Fill Every Blank");

                } else {
//                    Non Space

                }


            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        return view;
    }

}
