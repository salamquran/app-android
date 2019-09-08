package com.ermile.salamquran.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ermile.salamquran.Actitvty.Quran;
import com.ermile.salamquran.Item.item_QuranList;
import com.ermile.salamquran.R;

import java.util.ArrayList;

public class QuranListAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<item_QuranList> QuranList;
    Context mContext;
    int total_types;

    public static class SurahTypeViewHolder extends RecyclerView.ViewHolder {


        TextView number_surah,title_surah,decs_surah,page_surah;
        LinearLayout rootItem_surah;

        public SurahTypeViewHolder(View itemView) {
            super(itemView);

            this.number_surah = itemView.findViewById(R.id.number_surah);
            this.title_surah =  itemView.findViewById(R.id.title_surah);
            this.decs_surah = itemView.findViewById(R.id.decs_surah);
            this.page_surah = itemView.findViewById(R.id.page_surah);
            this.rootItem_surah = itemView.findViewById(R.id.rootItem_sureh);

        }

    }

    public static class JuzTypeViewHolder extends RecyclerView.ViewHolder {


        TextView title_juz , page_juz;
        LinearLayout rootItem_juz;

        public JuzTypeViewHolder(View itemView) {
            super(itemView);

            this.title_juz = itemView.findViewById(R.id.title_juz);
            this.page_juz = itemView.findViewById(R.id.page_juz);
            this.rootItem_juz = itemView.findViewById(R.id.rootItem_juz);

        }

    }

    public static class HezbTypeViewHolder extends RecyclerView.ViewHolder {


        TextView number_hezb, title_hezb, decs_hezb, page_hezb;
        LinearLayout rootItem_hizb;

        public HezbTypeViewHolder(View itemView) {
            super(itemView);

            this.number_hezb = itemView.findViewById(R.id.number_hizb);
            this.title_hezb = itemView.findViewById(R.id.title_hizb);
            this.decs_hezb = itemView.findViewById(R.id.decs_hizb);
            this.page_hezb = itemView.findViewById(R.id.page_hizb);
            this.rootItem_hizb = itemView.findViewById(R.id.rootItem_hizb);


        }

    }

    public QuranListAdaptor(ArrayList<item_QuranList> data, Context context ) {
        this.QuranList = data;
        this.mContext = context;
        total_types = QuranList.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case item_QuranList.SURAH_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quranlist_sure, parent, false);
                return new QuranListAdaptor.SurahTypeViewHolder(view);
            case item_QuranList.JUZ_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quranlist_juz, parent, false);
                return new QuranListAdaptor.JuzTypeViewHolder(view);
            case item_QuranList.HEZB_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quranlist_hezb, parent, false);
                return new QuranListAdaptor.HezbTypeViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (QuranList.get(position).type) {
            case 0:
                return item_QuranList.SURAH_TYPE;
            case 1:
                return item_QuranList.JUZ_TYPE;
            case 2:
                return item_QuranList.HEZB_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
        final Intent open_page = new Intent(mContext, Quran.class);/*Intent for Go to Page Quran*/

        final item_QuranList object = QuranList.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case item_QuranList.SURAH_TYPE:
                    ((QuranListAdaptor.SurahTypeViewHolder) holder).number_surah.setText(object.numbers);
                    ((QuranListAdaptor.SurahTypeViewHolder) holder).title_surah.setText(object.titles);
                    ((QuranListAdaptor.SurahTypeViewHolder) holder).decs_surah.setText(object.desc);
                    ((QuranListAdaptor.SurahTypeViewHolder) holder).page_surah.setText(object.page);

                    ((SurahTypeViewHolder) holder).rootItem_surah.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            open_page
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .putExtra("open_page", object.page);
                            v.getContext().startActivity(open_page);
                        }
                    });
                    break;

                case item_QuranList.JUZ_TYPE:
                    ((QuranListAdaptor.JuzTypeViewHolder) holder).title_juz.setText(object.titles);
                    ((QuranListAdaptor.JuzTypeViewHolder) holder).page_juz.setText(object.page);

                    ((JuzTypeViewHolder) holder).rootItem_juz.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            open_page
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .putExtra("open_page", object.page);
                            v.getContext().startActivity(open_page);
                        }
                    });
                    break;

                case item_QuranList.HEZB_TYPE:
                    ((QuranListAdaptor.HezbTypeViewHolder) holder).number_hezb.setText(object.hezb);
                    ((QuranListAdaptor.HezbTypeViewHolder) holder).title_hezb.setText(object.titles);
                    ((QuranListAdaptor.HezbTypeViewHolder) holder).decs_hezb.setText(object.desc);
                    ((QuranListAdaptor.HezbTypeViewHolder) holder).page_hezb.setText(object.page);
                    ((HezbTypeViewHolder) holder).number_hezb.setBackgroundResource(object.bg_hezb);

                    ((HezbTypeViewHolder) holder).rootItem_hizb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            open_page
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .putExtra("open_page", object.page);
                            v.getContext().startActivity(open_page);
                        }
                    });
                    break;

            }
        }

    }

    @Override
    public int getItemCount() {
        return QuranList.size();
    }


}