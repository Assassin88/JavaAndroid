package com.example.st.firstproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView detailsTv;

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
         this.detailsTv = view.findViewById(R.id.detailsTV);
         this.progressBar = view.findViewById(R.id.detailsPb);
        String details = getArguments().getString(DETAILS_KEY);

        this.detailsTv.setText(details);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doLongTask();
//            }
//        }).start();
        new Task().execute();
    }

    public class Task extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            int index = 0;
            while (index < 5){
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                publishProgress(index);
                index++;
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d("Tag", "Thread id" + Thread.currentThread().getId());
            Log.d("Tag", "on progress update" + values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            DoSomeUiStuff();
        }
    }

    private void doLongTask(){
        try{
            Log.d("Tag", "Thread id" + Thread.currentThread().getId());
            Thread.sleep(6000);
        }
        catch (InterruptedException e){

        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("Tag", "mainLooper" + Looper.getMainLooper().getThread().getId());
                Log.d("Tag", "ui update Thread id" + Thread.currentThread().getId());
                DoSomeUiStuff();
            }
        });
    }

    private void DoSomeUiStuff() {
            progressBar.setVisibility(View.GONE);
            detailsTv.setVisibility(View.VISIBLE);
    }
}
