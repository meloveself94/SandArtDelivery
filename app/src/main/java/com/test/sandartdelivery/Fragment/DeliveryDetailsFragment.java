package com.test.sandartdelivery.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;
import com.test.sandartdelivery.Activity.MainActivity;
import com.test.sandartdelivery.R;

import java.util.ArrayList;
import java.util.Objects;

public class DeliveryDetailsFragment extends Fragment {

    public static final String TAG = DeliveryDetailsFragment.class.getCanonicalName();

    private String bundleImageUrl, bundleProductTitle, bundleProductDesc, bundleDistance, bundlePromo;
    private double bundleProductRating = 0;
    private int bundleOutletAmount = 0;

    private ImageView ivDeliveryPhoto;
    private TextView tvDesc;
    private TextView tvRating;
    private TextView tvDistance;
    private TextView tvPromoPercent;
    private TextView tvOutletAmount;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null && !bundle.isEmpty()) {
            bundleImageUrl = bundle.getString("productImage");
            bundleProductTitle = bundle.getString("productName");
            bundleProductDesc = bundle.getString("productDesc");
            bundleProductRating = bundle.getDouble("productRating");
            bundleDistance = bundle.getString("productDistance");
            bundleOutletAmount = bundle.getInt("productOutletAmount");
            bundlePromo = bundle.getString("productPromo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delivery_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        setOnClick();
        getDeliveryDetail();
    }


    private void findViews(View rootView){
        ivDeliveryPhoto = rootView.findViewById(R.id.iv_delivery_photo);
        tvDesc = rootView.findViewById(R.id.tv_desc);
        tvRating = rootView.findViewById(R.id.tv_rating);
        tvDistance = rootView.findViewById(R.id.tv_distance);
        tvPromoPercent = rootView.findViewById(R.id.tv_promo);
        tvOutletAmount = rootView.findViewById(R.id.tv_outlet_amount);


        toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    private void setOnClick(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
    }

    @SuppressLint("ResourceType")
    private void getDeliveryDetail() {
        if (!TextUtils.isEmpty(bundleProductTitle) && !bundleProductTitle.equalsIgnoreCase("null")) {
            toolbar.setTitle(bundleProductTitle);
            toolbar.setTitleTextColor(Objects.requireNonNull(getActivity()).getResources()
                    .getColor(R.color.black, getActivity().getTheme()));
            toolbar.setSubtitleTextAppearance(getActivity(),
                    getActivity().getResources().getColor(R.color.black,getActivity().getTheme()));

        }

        if (!TextUtils.isEmpty(bundleImageUrl) && !bundleImageUrl.equalsIgnoreCase("null")){
            Picasso.get().load(bundleImageUrl).placeholder(R.drawable.icn_default_portrait)
                    .error(R.drawable.icn_default_portrait).fit().into(ivDeliveryPhoto);
        }

        if (!TextUtils.isEmpty(bundleProductDesc) && !bundleProductDesc.equalsIgnoreCase("null")) {
            tvDesc.setText(bundleProductDesc);
        } else {
            tvDesc.setText("-");
        }

        if (!TextUtils.isEmpty(bundleDistance) && !bundleDistance.equalsIgnoreCase("null")) {
            tvDistance.setText(bundleDistance);
        } else {
            tvDistance.setText("-");
        }

        if (!TextUtils.isEmpty(bundlePromo) && !bundlePromo.equalsIgnoreCase("null")) {
            tvPromoPercent.setText(bundlePromo);
        } else {
            tvPromoPercent.setText("-");
        }

        tvRating.setText(String.valueOf(bundleProductRating));
        String outletNearbyCombined = Objects.requireNonNull(getActivity()).getResources().
                getString(R.string.outlet_nearby).replace("[amount]", String.valueOf(bundleOutletAmount));
        tvOutletAmount.setText(outletNearbyCombined);
    }

}
