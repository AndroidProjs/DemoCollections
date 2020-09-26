package com.edger.recycleviewdemo.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edger.recycleviewdemo.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    public List<String> data;
    private LayoutInflater layoutInflater;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
        void onItemLongClickListener(View view, int position);
    }

    public RecyclerViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        RecyclerViewHolder myViewHolder = new RecyclerViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(data.get(position));

        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClickListener(viewHolder.itemView, layoutPosition);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClickListener(viewHolder.itemView, layoutPosition);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void add(int position) {
        data.add(data.get(position));
        notifyItemInserted(position);
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    class RecyclerViewHolder extends ViewHolder {
        TextView textView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

}
