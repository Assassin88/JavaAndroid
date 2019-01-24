package com.example.st.firstproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private static final String DETAILS_KEY
            = DetailsFragment.class.getSimpleName() + ".Details";

    public static Fragment newInstance(String details){
        Fragment result = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(DETAILS_KEY, details);
        result.setArguments(args);
        return result;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("SecondFragment!!!!!!", "onCreateView");
        return inflater.inflate(R.layout.fragment_details, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView detailsTv = view.findViewById(R.id.detailsTV);
        String details = getArguments().getString(DETAILS_KEY);

        detailsTv.setText(details);
    }
}
