package com.example.user.wowdc;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvWOW = (ListView) view.findViewById(R.id.lvWOW);


        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_wow_row,
                R.id.tvWOW,
                items
        );
        lvWOW.setAdapter(adapter);

        return view;
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_wow_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }
    private class RefreshDataTask extends AsyncTask<Void, Object, ArrayList<WOW>> {
        @Override
        protected ArrayList<WOW> doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String level = preferences.getString("level", "80");
            String Opciones = preferences.getString("todos", "nivel");
            APIWOW api = new APIWOW();
            ArrayList<WOW> result = null;
            if (Opciones.equals("nivel")) {
                result = api.getInfoBoss();
            } else {
                result = api.getLevelBoss(level);
            }

            Log.d("DEBUG", result != null ? result.toString() : null);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<WOW> wow) {

            super.onPostExecute(wow);

            adapter.clear();
            for (int i = 0; i < wow.size(); i++) {
                adapter.add(wow.get(i).getName() + " " + wow.get(i).getLevel());
            }
        }
    }
}
