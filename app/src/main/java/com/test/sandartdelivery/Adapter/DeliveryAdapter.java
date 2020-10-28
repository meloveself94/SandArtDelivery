package com.test.sandartdelivery.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.sandartdelivery.Model.DeliveryPojo;
import com.test.sandartdelivery.R;


import java.util.ArrayList;
import java.util.Objects;


public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryListHolder> {

    private Context mContext;
    private ArrayList<DeliveryPojo> mDeliveryPojo;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onEachDeliveryClicked(int position);
    }

    //a method to navigate to next fragment / activity
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public DeliveryAdapter(Context context, ArrayList<DeliveryPojo> horoscopeMainList){
        mContext = context;
        mDeliveryPojo = horoscopeMainList;
    }

    @NonNull
    @Override
    //need to return viewholder for adapter instead of returning null.
    public DeliveryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_delivery, parent, false);
        return new DeliveryListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryListHolder holder, int position) {
        //our masters array list each position will be save in the current item variable
        DeliveryPojo currentItem = mDeliveryPojo.get(position);
        //and then we can use this currentItem variable to get image and strings

        int deliveryId = currentItem.getId();
        String imageUrl = currentItem.getImageUrl();
        boolean isClose = currentItem.isClose();
        String closeLabel = currentItem.getCloseLabel();
        String productName = currentItem.getProductName();
        String productDesc = currentItem.getProductDesc();
        double starRating = currentItem.getStarRating();
        String distance = currentItem.getDistance();
        String promoDesc = currentItem.getPromoDesc();
        int outletAmount = currentItem.getOutletAmount();



        if (!TextUtils.isEmpty(imageUrl) && !imageUrl.equalsIgnoreCase("null")){
            Picasso.get().load(imageUrl).placeholder(R.drawable.icn_default_portrait)
                    .error(R.drawable.icn_default_portrait).fit().centerCrop().into(holder.mIvCard);
        }

        if (isClose){
            holder.llGlassView.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(closeLabel) && !closeLabel.equalsIgnoreCase("null")){
                holder.tvCloseLabel.setText(closeLabel);
            }
        }

        if (!TextUtils.isEmpty(productName) && !productName.equalsIgnoreCase("null")){
            holder.tvTitle.setText(productName);
        } else {
            holder.tvTitle.setText("-");
        }

        if (!TextUtils.isEmpty(productDesc) && !productDesc.equalsIgnoreCase("null")){
            holder.tvDesc.setText(productDesc);
        } else {
            holder.tvDesc.setText("-");
        }

        holder.tvRating.setText(String.valueOf(starRating));

        if (!TextUtils.isEmpty(distance) && !distance.equalsIgnoreCase("null")) {
            holder.tvDistance.setText(distance);
        } else {
            holder.tvDistance.setText("-");
        }

        if (!TextUtils.isEmpty(promoDesc) && !promoDesc.equalsIgnoreCase("null")) {
            holder.tvDiscountRate.setText(promoDesc);
        } else {
            holder.tvDiscountRate.setText("-");
        }

        String outletNearbyCombined = Objects.requireNonNull(mContext).getResources().
                getString(R.string.outlet_nearby).replace("[amount]", String.valueOf(outletAmount));
        holder.tvShopsAmount.setText(outletNearbyCombined);
    }

    @Override
    //return the arraylist.size because we want as many items in our adapter with as many items
    // we have in our arraylist.
    public int getItemCount() {
        return mDeliveryPojo.size();
    }

    class DeliveryListHolder extends RecyclerView.ViewHolder{

        ImageView mIvCard;
        LinearLayout llGlassView;
        TextView tvTitle;
        TextView tvDesc;
        TextView tvRating;
        TextView tvDistance;
        TextView tvDiscountRate;
        TextView tvShopsAmount;
        TextView tvCloseLabel;


        DeliveryListHolder(View itemView) {
            super(itemView);
            mIvCard = itemView.findViewById(R.id.iv_card);
            tvTitle = itemView.findViewById(R.id.tv_product_name);
            llGlassView = itemView.findViewById(R.id.ll_glass_view);
            tvDesc = itemView.findViewById(R.id.tv_product_desc);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvDistance = itemView.findViewById(R.id.tv_distance);
            tvDiscountRate = itemView.findViewById(R.id.tv_discount_rate);
            tvShopsAmount = itemView.findViewById(R.id.tv_shops_amount);
            tvCloseLabel = itemView.findViewById(R.id.tv_closed);

            //mCardMastersImage.bringToFront();

            itemView.setOnClickListener(v -> {
                if (mListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        mListener.onEachDeliveryClicked(position);
                    }
                }
            });
        }
    }
}
