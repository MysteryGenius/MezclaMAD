package com.mezcla.chai.cocktails.views.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mezcla.chai.cocktails.R;
import com.mezcla.chai.cocktails.utils.Constants;
import com.mezcla.chai.cocktails.views.activities.DrinkDetailListingActivity;

public class SearchFragment extends Fragment {

    View view;
    EditText editText;
    Button submitBtn;
    TextView checkTxt;

    EditText searchName;
    Button nameBtn;

    public SearchFragment() {
    }

    @Override
    public String toString() {
        return Constants.FILTER_CATEGORY;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        editText = view.findViewById(R.id.editText);
        submitBtn = view.findViewById(R.id.submitBtn);
        checkTxt = view.findViewById(R.id.textView);
        searchName = view.findViewById(R.id.SearchByName);
        nameBtn = view.findViewById(R.id.nameBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitBtnHandler();
            }
        });

        nameBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                nameBtnHandler();
            }
        });

        return view;
    }
    void submitBtnHandler(){
        String ingredients = editText.getText().toString();
        Intent intent = new Intent(view.getContext(), DrinkDetailListingActivity.class);
        intent.putExtra(Constants.CATEGORY_NAME, ingredients);
        intent.putExtra(Constants.TYPE, Constants.FILTER_INGREDIENTS);
        view.getContext().startActivity(intent);
    }

    void nameBtnHandler(){
        String ingredients = searchName.getText().toString();
        Intent intent = new Intent(view.getContext(), DrinkDetailListingActivity.class);
        intent.putExtra(Constants.CATEGORY_NAME, ingredients);
        intent.putExtra(Constants.TYPE, Constants.LOOKUP_BY_NAME);
        view.getContext().startActivity(intent);
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
