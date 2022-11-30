package com.example.absensirtj.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.absensirtj.R;
import com.example.absensirtj.item.CustomSpinerItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomSpinerAdapter extends ArrayAdapter<CustomSpinerItem> {

    public CustomSpinerAdapter(Context context, ArrayList<CustomSpinerItem> customSpinerItemslist){
        super(context,0,customSpinerItemslist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View converView, ViewGroup parent){
        if (converView == null){
            converView = LayoutInflater.from(getContext()).inflate(R.layout.spinner,parent,false);
        }
        TextView textViewName = converView.findViewById(R.id.custom_spinner_item);
        CustomSpinerItem currentItem = getItem(position);

        if (currentItem !=null){
            textViewName.setText(currentItem.getCustomeSpinername());
        }
        return converView;
    }
}
