package com.muhammadhidayah.apps.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.muhammadhidayah.apps.model.kontenModel;
import com.muhammadhidayah.apps.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by sontoloyo on 5/29/17.
 */

public class kontenAdapter extends BaseAdapter {
    private Context context;
    private List<kontenModel> originalData = null;
    private List<kontenModel> kontenModelList = null;
    private ItemFilter mFilter = new ItemFilter();

    public kontenAdapter(Context context, List<kontenModel> kontenModelList) {
        this.originalData = kontenModelList;
        this.kontenModelList = kontenModelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return kontenModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return kontenModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return kontenModelList.get(position).getIdKonten();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.list_konten, null);

        ImageView imgKonten = (ImageView) v.findViewById(R.id.imgmateri);
        TextView lblJudul = (TextView) v.findViewById(R.id.lblJudul);

        int id = v.getResources().getIdentifier("com.muhammadhidayah.apps:drawable/" + kontenModelList.get(position).getGambar(), null, null);
        imgKonten.setImageResource(id);
        lblJudul.setText(kontenModelList.get(position).getNamaMateri());

        return v;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<kontenModel> list = originalData;

            int count = list.size();

            final ArrayList<kontenModel> nList = new ArrayList<kontenModel>(count);

            kontenModel filterableKonten;

            for (int i = 0; i < count; i++) {
                filterableKonten = list.get(i);
                if (filterableKonten.toString().toLowerCase(Locale.ENGLISH).contains(filterString)) {
                    nList.add(filterableKonten);
                }
            }

            results.values = nList;
            results.count = nList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            kontenModelList = (ArrayList<kontenModel>) results.values;
            notifyDataSetChanged();
        }
    }

    public kontenAdapter() {

    }
}
