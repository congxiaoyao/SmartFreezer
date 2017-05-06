package com.jaycejia.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.jaycejia.R;
import com.jaycejia.databinding.FragmentRefrigeratorListBinding;
import com.jaycejia.utils.ToastUtil;


/**
 * Created by NiYang on 2017/4/23.
 */

public class RefrigeratorListFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentRefrigeratorListBinding binding = null;
    private View viewCache = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (this.viewCache == null) {
            this.binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_refrigerator_list, container, false);
            this.viewCache = this.binding.getRoot();
            initSortSpinner();
        }
        return this.viewCache;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initSortSpinner() {
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        this.binding.sortSpinner.measure(w, h);
        int height = this.binding.sortSpinner.getMeasuredHeight();
        this.binding.sortSpinner.setDropDownVerticalOffset(height);
        this.binding.sortSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] sortNames = getResources().getStringArray(R.array.sort_selector);
        ToastUtil.showToast(sortNames[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
