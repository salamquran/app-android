package com.ermile.salamquran.online.fragmen.quran.tabLayout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ermile.salamquran.QuranPage;
import com.ermile.salamquran.R;

import java.util.ArrayList;

public class QuranList_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<QuranList_item> QuranList;
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

    public QuranList_adapter(ArrayList<QuranList_item> data, Context context ) {
        this.QuranList = data;
        this.mContext = context;
        total_types = QuranList.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case QuranList_item.SURAH_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modle_sura, parent, false);
                return new QuranList_adapter.SurahTypeViewHolder(view);
            case QuranList_item.JUZ_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modle_juz, parent, false);
                return new QuranList_adapter.JuzTypeViewHolder(view);
            case QuranList_item.HEZB_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modle_hezb, parent, false);
                return new QuranList_adapter.HezbTypeViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (QuranList.get(position).type) {
            case 0:
                return QuranList_item.SURAH_TYPE;
            case 1:
                return QuranList_item.JUZ_TYPE;
            case 2:
                return QuranList_item.HEZB_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
        final Intent open_page = new Intent(mContext, QuranPage.class);/*Intent for Go to Page Quran*/

        final QuranList_item object = QuranList.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case QuranList_item.SURAH_TYPE:
                    ((QuranList_adapter.SurahTypeViewHolder) holder).number_surah.setText(object.numbers);
                    ((QuranList_adapter.SurahTypeViewHolder) holder).title_surah.setText(object.titles);
                    ((QuranList_adapter.SurahTypeViewHolder) holder).decs_surah.setText(object.desc);
                    ((QuranList_adapter.SurahTypeViewHolder) holder).page_surah.setText(object.page);

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

                case QuranList_item.JUZ_TYPE:
                    ((QuranList_adapter.JuzTypeViewHolder) holder).title_juz.setText(object.titles);
                    ((QuranList_adapter.JuzTypeViewHolder) holder).page_juz.setText(object.page);

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

                case QuranList_item.HEZB_TYPE:
                    ((QuranList_adapter.HezbTypeViewHolder) holder).number_hezb.setText(object.hezb);
                    ((QuranList_adapter.HezbTypeViewHolder) holder).title_hezb.setText(object.titles);
                    ((QuranList_adapter.HezbTypeViewHolder) holder).decs_hezb.setText(object.desc);
                    ((QuranList_adapter.HezbTypeViewHolder) holder).page_hezb.setText(object.page);
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