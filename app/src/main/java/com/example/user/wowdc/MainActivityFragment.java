package com.example.user.wowdc;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvWOW = (ListView) view.findViewById(R.id.lvWOW);
        String[] data = {
                "Golem Ancestral",
                "Goblin pequeño",
                "Dragon de fuego",
                "Angel de luz",
                "Llamarada",
                "Espectro",
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_wow_row,
                R.id.tvWOW,
                items
        );
        lvWOW.setAdapter(adapter);

        return view;
    }
}
