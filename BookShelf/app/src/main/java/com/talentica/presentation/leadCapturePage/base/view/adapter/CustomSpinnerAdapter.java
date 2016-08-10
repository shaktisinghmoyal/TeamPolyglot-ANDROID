package com.talentica.presentation.leadCapturePage.base.view.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.talentica.databinding.SpinnerDropDownRowBinding;

import java.util.ArrayList;

/**
 * Created by ShaktiM on 15-01-2016.
 */
public abstract class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private final String Tag = "CustomSpinnerAdapter";
    Activity context;
    private ArrayList<String> arrayList;
    private SpinnerDropDownRowBinding binding;

    public CustomSpinnerAdapter(Activity context, int resource, ArrayList<String> spinnerArrayList) {

        super(context, resource, spinnerArrayList);
        this.context = context;
        arrayList = spinnerArrayList;
        Log.d(Tag, "CustomSpinnerAdapter constructor ");
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        Log.d(Tag, " getDropDownView ");

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            binding = SpinnerDropDownRowBinding.inflate(inflater, parent, false);
        }
        binding.spinnerDropdownRowText.setText(arrayList.get(position));
        convertView = binding.getRoot();
        return convertView;

    }


}
