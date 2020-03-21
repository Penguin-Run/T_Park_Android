package com.example.hw1_fragments_with_list;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FragmentMain extends Fragment {
    private static final int INITIAL_ELEMENTS_COUNT = 100;
    private static final String ITEMLIST_KEY = "itemList";
    private static final int VERTICAL_ORIENTATION_SPANCOUNT = 3;
    private static final int HORIZONTAL_ORIENTATION_SPANCOUNT = 4;

    private ArrayList<Integer> itemList;
    private Activity activity;
    private RecyclerView numbersList;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(ITEMLIST_KEY, itemList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // creating list
        numbersList = view.findViewById(R.id.mainList);
        if (savedInstanceState != null) itemList = savedInstanceState.getIntegerArrayList(ITEMLIST_KEY);
        else {
            itemList = new ArrayList<>();
            for (int i = 0; i < INITIAL_ELEMENTS_COUNT; i++) itemList.add(i+1);
        }
        if (numbersList != null) {
            int spanCount;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                spanCount = HORIZONTAL_ORIENTATION_SPANCOUNT;
            } else spanCount = VERTICAL_ORIENTATION_SPANCOUNT;
            numbersList.setAdapter(new mainListAdapter(itemList));
            numbersList.setLayoutManager(new GridLayoutManager(view.getContext(), spanCount, RecyclerView.VERTICAL, false));
        }

        // attaching lusteners
        Button btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add(itemList.size()+1);
                numbersList.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity)context;
        }
    }

    class mainListAdapter extends RecyclerView.Adapter<mainListViewHolder> {
        ArrayList<Integer> integers;

        mainListAdapter(ArrayList<Integer> integers) {
            this.integers = integers;
        }

        @NonNull
        @Override
        public mainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
            return new mainListViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull mainListViewHolder holder, int position) {
            if (integers.get(position) % 2 == 0) {
                holder.digit.setText(String.valueOf(integers.get(position)));
                holder.digit.setTextColor(Color.RED);
            } else {
                holder.digit.setText(String.valueOf(integers.get(position)));
                holder.digit.setTextColor(Color.BLUE);
            }
            holder.digit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView digit = v.findViewById(v.getId());
                    int number = Integer.parseInt((String)digit.getText());
                    ((ActivityAccess) activity).onItemClick(number);
                }
            });

        }

        @Override
        public int getItemCount() {
            return integers.size();
        }


    }

    static class mainListViewHolder extends RecyclerView.ViewHolder {
        TextView digit;

        mainListViewHolder(@NonNull View itemView) {
            super(itemView);
            digit = itemView.findViewById(R.id.digit);
        }
    }


}
