package com.easykitchen.waterweb.root.easykitchen.Tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easykitchen.waterweb.root.easykitchen.Models.CatItem;
import com.easykitchen.waterweb.root.easykitchen.R;

import java.util.List;

/**
 * Created by root on 7/28/15.
 */
class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder>{

    //private ClickListener clickListener;
    private LayoutInflater inflater;
    List<CatItem> data = null;
    Context con;

    public void CatAdapterInit(Context context, List<CatItem> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row_cat, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CatItem current = data.get(position);
        holder.titleIn.setText(current.item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleIn;

        public ViewHolder(View itemView) {
            super(itemView);
            titleIn = (TextView) itemView.findViewById(R.id.listTextCat);
        }

    }

}