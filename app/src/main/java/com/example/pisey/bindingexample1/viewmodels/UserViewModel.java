package com.example.pisey.bindingexample1.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.pisey.bindingexample1.BR;
import com.example.pisey.bindingexample1.models.UserModel;

/**
 * Created by pisey on 06-Jun-16.
 */
public class UserViewModel extends BaseObservable {
    private final UserModel userModel;

    public UserViewModel(UserModel userModel){
        this.userModel=userModel;

    }

    @Bindable
    public String getId(){
        return this.userModel.getId();
    }

    public void setId(String id){
        this.userModel.setId(id);
    }

    @Bindable
    public String getName(){
        return this.userModel.getName();
    }

    public void setName(String name){
        this.userModel.setName(name);
        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.id);
    }

}
