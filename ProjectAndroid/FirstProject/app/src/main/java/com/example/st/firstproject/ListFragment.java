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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment {

    private ListView mListView;
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
        mListView = view.findViewById(R.id.pizzaListLv);
        BaseAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.test_list_item, Constants.PIZZA_TYPES);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mPizzaListListener != null){
                    mPizzaListListener.onPizzaSelected(position);
                }
            }
        });
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

