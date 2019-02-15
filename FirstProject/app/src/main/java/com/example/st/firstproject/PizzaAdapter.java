package com.example.st.firstproject;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    private List<String> pizzas;
    private ItemClickListener listener;


    public PizzaAdapter(Context context, List<String> pizzas){
        this.pizzas = pizzas;
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_pizza, null);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        TextView view = viewHolder.itemView.findViewById(R.id.rssTitleTv);
        view.setText(pizzas.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }



    public class  ViewHolder extends RecyclerView.ViewHolder{
        private final TextView pizzaText;

        public ViewHolder(View itemView){
            super(itemView);
            pizzaText = itemView.findViewById(R.id.rssTitleTv);
        }

        void bind(String pizza, final int index){
            pizzaText.setText(pizza);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        listener.onItemClick(index);
                    }
                }
            });
        }
    }

    public interface ItemClickListener{
        void onItemClick(int index);
    }
}
