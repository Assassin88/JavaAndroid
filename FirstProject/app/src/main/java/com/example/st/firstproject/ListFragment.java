package com.example.st.firstproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class ListFragment extends Fragment {

    private RecyclerView mRecycleView;
    private PizzaSelectedListener mPizzaListListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("SecondFragment!!!!!!", "onCreateView");
        View result =  inflater.inflate(R.layout.fragment_list, null, false);
        initUI(result);

        return result;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initListener(context);
    }

    private void initUI(View view){
        mRecycleView = view.findViewById(R.id.pizzaListLv);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        PizzaAdapter adapter = new PizzaAdapter(getContext(), Arrays.asList(Constants.PIZZA_TYPES));

        mRecycleView.setAdapter(adapter);

        adapter.setItemClickListener(
                new PizzaAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int index) {
                        if(mPizzaListListener != null){
                            mPizzaListListener.onPizzaSelected(index);
                        }
                    }
                }
        );
    }

    private void initListener(Context context){
        if(context instanceof PizzaSelectedListener){
            mPizzaListListener = (PizzaSelectedListener)context;
        }
        else{
            throw new IllegalStateException("Contex doesn`t implement pizza listener!!!");
        }
    }

    interface PizzaSelectedListener{
        void onPizzaSelected(int index);
    }
}

