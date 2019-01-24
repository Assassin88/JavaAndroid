package com.example.st.firstproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("SecondFragment!!!!!!", "onCreateView");
        return inflater.inflate(R.layout.second_fragment, null);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d("SecondFragment", "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d("SecondFragment", "onDetach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("SecondFragment", "onActivityCreated");
    }
}
