package com.ermile.salamquran.online.fragmen.quran;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ermile.salamquran.R;
import com.ermile.salamquran.online.fragmen.quran.tabLayout.BookMark;
import com.ermile.salamquran.online.fragmen.quran.tabLayout.JuzList;
import com.ermile.salamquran.online.fragmen.quran.tabLayout.SureList;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quran extends Fragment {
    private static String TAG = "fQuran";
    View fragment_quran;
    TabLayout tabLayout_NavQuran;
    ViewPager viewPager_NavQuran;

    public Quran() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment_quran =  inflater.inflate(R.layout.fragment_quran, container, false);

        viewPager_NavQuran = fragment_quran.findViewById(R.id.viewPager_NavQuran);
        tabLayout_NavQuran = fragment_quran.findViewById(R.id.tabLayout_NavQuran);

        NavQuran_adapterTab adapterTab = new NavQuran_adapterTab(getChildFragmentManager());

        adapterTab.addFragment(new SureList() , "Sureh");
        adapterTab.addFragment(new JuzList() , "Juz");
        adapterTab.addFragment(new BookMark() , "Bookmark");

        viewPager_NavQuran.setAdapter(adapterTab);
        tabLayout_NavQuran.setupWithViewPager(viewPager_NavQuran);
        return fragment_quran;
    }

    class NavQuran_adapterTab extends FragmentPagerAdapter {
        private final List<Fragment> ftagment_list = new ArrayList<>();
        private final List<String> title_list = new  ArrayList<>();

        public NavQuran_adapterTab(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int posetion) {
            return ftagment_list.get(posetion);
        }

        @Override
        public int getCount() {
            return title_list.size();
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title_list.get(position);
        }

        public void addFragment(Fragment fragment , String title){
            ftagment_list.add(fragment);
            title_list.add(title);
        }
    }

}
