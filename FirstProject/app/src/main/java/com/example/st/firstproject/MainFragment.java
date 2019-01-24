package com.example.st.firstproject;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
    public final static String INDEX_KEY = "arg_indexs";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int value = getArguments().getInt(INDEX_KEY);

        Log.d("MainFragment!!!!!!", "onCreateView:____________" + value);
        return inflater.inflate(R.layout.fragment_main, null);
    }

    public static Fragment CreateNewInstance(int index){
        Fragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX_KEY, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d("MainFragment", "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d("MainFragment", "onDetach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("MainFragment", "onActivityCreated");
    }
}
