package com.test.sandartdelivery.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sandartdelivery.Adapter.DeliveryAdapter;
import com.test.sandartdelivery.Helper.ApiHelper;
import com.test.sandartdelivery.Model.DeliveryPojo;
import com.test.sandartdelivery.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements DeliveryAdapter.OnItemClickListener {

    public static final String TAG = HomeFragment.class.getCanonicalName();

    //Views
    private RecyclerView rvMain;

    //Variables
    private ArrayList<DeliveryPojo> arrDeliveries;
    private DeliveryAdapter deliveryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrDeliveries = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        setOnClick();
        getDeliveryList();

    }

    private void findViews(View rootView){
        View commonToolbar = rootView.findViewById(R.id.common_toolbar);
        TextView tvToolbarTitle = commonToolbar.findViewById(R.id.toolbar_title);
        tvToolbarTitle.setText("沙雕外卖");
        rvMain = rootView.findViewById(R.id.rv_main);
        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setOnClick(){

    }

    private void getDeliveryList() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://2826536e-d00d-4575-b35e-5562354bf840.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiHelper apiHelper = retrofit.create(ApiHelper.class);

        Call<List<DeliveryPojo>> call = apiHelper.getDeliveries();

        call.enqueue(new Callback<List<DeliveryPojo>>() {
            @Override
            public void onResponse(Call<List<DeliveryPojo>> call, Response<List<DeliveryPojo>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                List<DeliveryPojo> deliveryPojos = response.body();
                if (deliveryPojos != null) {
                    for (DeliveryPojo deliveries : deliveryPojos)  {
                        arrDeliveries.add(new DeliveryPojo(deliveries.getId(), deliveries.getImageUrl(),
                                deliveries.isClose(), deliveries.getCloseLabel(), deliveries.getProductName(),
                                deliveries.getProductDesc(), deliveries.getStarRating(),
                                deliveries.getDistance(), deliveries.getPromoDesc(), deliveries.getOutletAmount()));
                    }
                    deliveryAdapter = new DeliveryAdapter(getActivity(), arrDeliveries);
                    rvMain.setAdapter(deliveryAdapter);
                    deliveryAdapter.setOnItemClickListener(HomeFragment.this);
                }
            }

            @Override
            public void onFailure(Call<List<DeliveryPojo>> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onEachDeliveryClicked(int position) {
        Fragment fragment = new DeliveryDetailsFragment();
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        //to get clicked item position
        DeliveryPojo clickedItem = arrDeliveries.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("productName", clickedItem.getProductName());
        bundle.putString("productDesc", clickedItem.getProductDesc());
        bundle.putString("productImage", clickedItem.getImageUrl());
        bundle.putDouble("productRating", clickedItem.getStarRating());
        bundle.putString("productDistance", clickedItem.getDistance());
        bundle.putInt("productOutletAmount", clickedItem.getOutletAmount());
        bundle.putString("productPromo", clickedItem.getPromoDesc());
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
                .hide(HomeFragment.this)
                .add(R.id.main_frame_layout, fragment)
                .addToBackStack(TAG)
                .commit();
    }
}
