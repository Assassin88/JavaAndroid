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
import com.example.st.firstproject.adapters.RssAdapter;
import com.example.st.firstproject.model.RssFeed;
import com.example.st.firstproject.model.RssFeedItem;
import java.util.List;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ListFragment extends Fragment {

    private RecyclerView mRecycleView;
    private PizzaSelectedListener mPizzaListListener;
    private CompositeDisposable compositeDisposable;

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
        compositeDisposable = new CompositeDisposable();
        callRssService((IServiceProvider) context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        compositeDisposable.dispose();
    }

    private void callRssService(IServiceProvider service){
        Disposable disposable = service.GetRssServiceProvider().getItems().subscribeWith(new DisposableObserver<RssFeed>() {
            @Override
            public void onNext(RssFeed rssFeed) {
                mRecycleView.setVisibility(View.VISIBLE);
                getView().findViewById(R.id.rssErrorTv).setVisibility(View.GONE);
                updateAdapter(rssFeed.getChannel().getItemList());
            }

            @Override
            public void onError(Throwable e) {
                getView().findViewById(R.id.rssErrorTv).setVisibility(View.VISIBLE);
                mRecycleView.setVisibility(View.GONE);
            }

            @Override
            public void onComplete() {

            }
        });

        compositeDisposable.add(disposable);
    }

    private void initUI(View view){
        mRecycleView = view.findViewById(R.id.pizzaListLv);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void updateAdapter(List<RssFeedItem> items){
        RssAdapter adapter = new RssAdapter(getContext(), items);

        mRecycleView.setAdapter(adapter);

        adapter.setItemClickListener(
                new RssAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int index) {
                        if(mPizzaListListener != null){
                            mPizzaListListener.onPizzaSelected(index);
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

