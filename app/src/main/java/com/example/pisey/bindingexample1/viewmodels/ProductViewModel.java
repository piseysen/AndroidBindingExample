package com.example.pisey.bindingexample1.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.pisey.bindingexample1.BR;
import com.example.pisey.bindingexample1.models.ProductModel;

/**
 * Created by pisey on 06-Jun-16.
 */
public class ProductViewModel extends BaseObservable {
    private final ProductModel productModel;

    public ProductViewModel(ProductModel productModel) {
        this.productModel = productModel;

    }

    @Bindable
    public String getId() {
        return this.productModel.getId();
    }

    public void setId(String id) {
        this.productModel.setId(id);
    }

    @Bindable
    public String getName() {
        return this.productModel.getName();
    }

    public double getPrice() {
        return this.productModel.getPrice();
    }

    public void setName(String name) {
        this.productModel.setName(name);
        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.id);
    }

}
