package com.example.st.firstproject.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.st.firstproject.R;
import com.example.st.firstproject.model.RssFeedItem;
import java.util.List;

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.ViewHolder> {

    private List<RssFeedItem> items;
    private ItemClickListener listener;
    private Context context;


    public RssAdapter(Context context, List<RssFeedItem> items){
        this.context = context;
        this.items = items;
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_pizza, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.bind(items.get(i), i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView publicationDate;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.rssTitleTv);
            publicationDate = itemView.findViewById(R.id.rssDateTv);
        }

        void bind(RssFeedItem item, final int index){
            title.setText(item.getTitle());
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