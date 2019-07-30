package com.mezcla.chai.cocktails.views.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mezcla.chai.cocktails.R;
import com.mezcla.chai.cocktails.adapters.DrinkDetailListAdapter;
import com.mezcla.chai.cocktails.models.apiResponseWrappers.DrinkListingAPIResponse;
import com.mezcla.chai.cocktails.models.apiResponseWrappers.NameListingAPIResponse;
import com.mezcla.chai.cocktails.models.pojos.DrinkDetail;
import com.mezcla.chai.cocktails.utils.Constants;
import com.mezcla.chai.cocktails.viewModels.DrinkDetailListingViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrinkDetailListingActivity extends AppCompatActivity {

    @BindView(R.id.detailedList)
    RecyclerView detailedList;

    DrinkDetailListingViewModel viewModel;
    DrinkDetailListAdapter adapter;

    String type;
    String name;

    List<DrinkDetail> drinksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail_listing);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this)
                .get(DrinkDetailListingViewModel.class);

        fetchUrlDetails();
        drinksList = new ArrayList<>();
        initAdapterWithNoData();
        subscribeToResponseObserver();
    }

    public void fetchUrlDetails() {
        if (getIntent() != null) {
            type = getIntent().getStringExtra(Constants.TYPE);
            name = getIntent().getStringExtra(Constants.CATEGORY_NAME);
        }
    }

    private void initAdapterWithNoData() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        detailedList.setLayoutManager(layoutManager);
        adapter =
                new DrinkDetailListAdapter(drinksList, this);
        detailedList.setAdapter(adapter);
    }

    private void loadDataWithSubscription(List<DrinkDetail> categories) {
        drinksList.clear();
        drinksList.addAll(categories);
        adapter.notifyDataSetChanged();
    }

    private void subscribeToResponseObserver() { //
        if (type != null && name != null) {
            viewModel.getApiResponse(type, name).observe(this, new Observer<DrinkListingAPIResponse>() {
                @Override
                public void onChanged(@Nullable DrinkListingAPIResponse drinkListingAPIResponse) {
                    switch (drinkListingAPIResponse.getResponseType()) {
                        case NameListingAPIResponse.SUCCESSFUL_RESPONSE:
                            loadDataWithSubscription(drinkListingAPIResponse.getDrinks());
                            break;
                        case NameListingAPIResponse.REQUEST_ERROR_RESPONSE:
                        case NameListingAPIResponse.THROWABLE_ERROR_RESPONSE:
                            displayNetworkingErrorToast();
                    }
                }
            });
        }

    }

    private void displayNetworkingErrorToast() { //Unable to retrieve the list due to network error
        Toast.makeText(this,
                "Unable to get the list",
                Toast.LENGTH_LONG).show();
    }


}
