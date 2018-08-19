package muz.kumoteamthailand.com.mucyapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import muz.kumoteamthailand.com.mucyapp.R;
import muz.kumoteamthailand.com.mucyapp.utility.MyConstant;
import muz.kumoteamthailand.com.mucyapp.utility.PacketSaleAdapter;
import muz.kumoteamthailand.com.mucyapp.utility.ReadAllData;

public class PackageSaleFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create RecycleView
        createRecycleView();

    }//Main Method





    private void createRecycleView() {
        RecyclerView recyclerView = getView().findViewById(R.id.recycleViewPackageSales);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(linearLayoutManager);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        MyConstant myConstant = new MyConstant();

        String[] columnStrings = myConstant.getColumn_tm_packetpoint();

        ArrayList<String > nameStringArrayList = new ArrayList<>();
        ArrayList<String> priceStringArrayList = new ArrayList<>();
        ArrayList<String> pointStringArrayList = new ArrayList<>();


        try {
            ReadAllData readAllData = new ReadAllData(getActivity());
            readAllData.execute(myConstant.getUrlPackageSales());

            String jsonString = readAllData.get();
            Log.d("19AugV3", "Json-->" + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i=0; i<jsonArray.length();i+=1) {
                JSONObject  jsonObject = jsonArray.getJSONObject(i);
                nameStringArrayList.add(jsonObject.getString(columnStrings[1]));
                priceStringArrayList.add(jsonObject.getString(columnStrings[4]));
                pointStringArrayList.add(jsonObject.getString(columnStrings[5]));

            }

            PacketSaleAdapter packetSaleAdapter = new PacketSaleAdapter(getActivity(), nameStringArrayList, priceStringArrayList, pointStringArrayList);

            recyclerView.setAdapter(packetSaleAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package_sale, container, false);
        return view;
    }
}
