package com.example.pisey.bindingexample1.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pisey.bindingexample1.listener.OnCustomItemClickListener;
import com.example.pisey.bindingexample1.listener.OnCustomItemLongClickListener;

/**
 * Created by pisey on 06-Jun-16.
 */
public class BindDynamicRViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private final static int KEY_DATA = -1132;
    private ObservableList<T> items;
    private int _variableId;
    private int _resourceId;
    private LayoutInflater _inflater;

    private OnCustomItemClickListener<T> onCustomItemClickListener;
    private OnCustomItemLongClickListener<T> onCustomItemLongClickListener;

    public void setOnCustomItemClickListener(OnCustomItemClickListener<T> onCustomItemClickListener) {
        this.onCustomItemClickListener = onCustomItemClickListener;
    }

    public BindDynamicRViewAdapter(int varId, int resId) {
        this._variableId = varId;
        this._resourceId = resId;
        this.items = new ObservableArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null == _inflater)
            _inflater = LayoutInflater.from(parent.getContext());

        ViewDataBinding binding = DataBindingUtil.inflate(_inflater, _resourceId, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        final T item = items.get(position);
        holder1._binding.setVariable(_variableId, item);
        holder1._binding.getRoot().setTag(KEY_DATA, item);
        holder1._binding.getRoot().setOnClickListener(this);
        holder1._binding.getRoot().setOnLongClickListener(this);
        holder1._binding.executePendingBindings();
    }


    public void addItem(T item) {
        items.add(item);
        if (getPosition(item) > -1)
            notifyItemInserted(getPosition(item));
        else
            notifyDataSetChanged();
    }

    public void removeItem(T item) {
        items.remove(item);
        if (getPosition(item) > -1)
            notifyItemRemoved(getPosition(item));
        else
            notifyDataSetChanged();
    }

    public void updateItem(T item) {
        int pos = getPosition(item);
        if (pos > -1) {
            items.set(pos, item);
            notifyItemChanged(pos);
        } else {
            notifyDataSetChanged();
        }
    }

    private int getPosition(T item) {
        return items != null ? items.indexOf(item) : -1;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (onCustomItemClickListener != null) {
            T item = (T) v.getTag(KEY_DATA);
            onCustomItemClickListener.onItemClick(item);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onCustomItemLongClickListener != null) {
            T item = (T) v.getTag(KEY_DATA);
            onCustomItemLongClickListener.onItemLongClick(item);
        }
        return true;
    }

    public void setOnCustomItemLongClickListener(OnCustomItemLongClickListener<T> onCustomItemLongClickListener) {
        this.onCustomItemLongClickListener = onCustomItemLongClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding _binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            _binding = binding;
        }
    }
}
