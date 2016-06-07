package com.example.pisey.bindingexample1.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.pisey.bindingexample1.viewmodels.UserViewModel;

/**
 * Created by pisey on 06-Jun-16.
 */
public class BindRecyclerViewAdapter extends RecyclerView.Adapter<BindRecyclerViewAdapter.MyViewHolder> {
    private ObservableList<UserViewModel> items;
    private int _variableId;
    private int _resourceId;
    private LayoutInflater _inflater;

    public BindRecyclerViewAdapter(int varId, int resId) {
        this._variableId = varId;
        this._resourceId = resId;
        this.items = new ObservableArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null == _inflater)
            _inflater = LayoutInflater.from(parent.getContext());

        ViewDataBinding binding = DataBindingUtil.inflate(_inflater, _resourceId, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserViewModel item = items.get(position);
        holder._binding.setVariable(_variableId,item);
        holder._binding.executePendingBindings();
    }

    public void addItem(UserViewModel item) {
        items.add(item);
        if (getPosition(item) > -1)
            notifyItemInserted(getPosition(item));
        else
            notifyDataSetChanged();
    }

    private int getPosition(UserViewModel item) {
        return items != null ? items.indexOf(item) : -1;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding _binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            _binding = binding;
        }
    }
}
