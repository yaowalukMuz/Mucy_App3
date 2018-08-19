package muz.kumoteamthailand.com.mucyapp.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import muz.kumoteamthailand.com.mucyapp.R;

public class PacketSaleAdapter extends RecyclerView.Adapter<PacketSaleAdapter.PacketViewHolder>{
    private Context context;
    private ArrayList<String> namePacketStringArrayList,priceStringArrayList,pointStringArrayList;
    private LayoutInflater layoutInflater;


    public PacketSaleAdapter(Context context,
                             ArrayList<String> namePacketStringArrayList,
                             ArrayList<String> priceStringArrayList,
                             ArrayList<String> pointStringArrayList) {

        this.layoutInflater = LayoutInflater.from(context);
        this.namePacketStringArrayList = namePacketStringArrayList;
        this.priceStringArrayList = priceStringArrayList;
        this.pointStringArrayList = pointStringArrayList;

    }

    @NonNull
    @Override
    public PacketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycleview_package_sales, parent, false);
        PacketViewHolder packetViewHolder = new PacketViewHolder(view);

        return packetViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PacketViewHolder holder, int position) {
        String nameString = namePacketStringArrayList.get(position);
        String priceString = priceStringArrayList.get(position);
        String pointString = pointStringArrayList.get(position);

        holder.nameTextView.setText(nameString);
        holder.priceTextView.setText(priceString + " Bath.-");
        holder.pointTextView.setText("Point : " + pointString + " Point.");


    }

    @Override
    public int getItemCount() {
        return namePacketStringArrayList.size();
    }

    public class PacketViewHolder extends RecyclerView.ViewHolder{

        private TextView nameTextView, priceTextView, pointTextView;

        public PacketViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.txtNamePacket);
            priceTextView = itemView.findViewById(R.id.txtPrice);
            pointTextView = itemView.findViewById(R.id.txtPoint);


        }
    }   // Packget Class









}   // Main Class
