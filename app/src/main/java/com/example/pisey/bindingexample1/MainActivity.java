package com.example.pisey.bindingexample1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.pisey.bindingexample1.adapters.BindDynamicRViewAdapter;
import com.example.pisey.bindingexample1.databinding.ActivityMainBinding;
import com.example.pisey.bindingexample1.listener.OnCustomItemClickListener;
import com.example.pisey.bindingexample1.listener.OnCustomItemLongClickListener;
import com.example.pisey.bindingexample1.models.ProductModel;
import com.example.pisey.bindingexample1.models.UserModel;
import com.example.pisey.bindingexample1.viewmodels.ProductViewModel;
import com.example.pisey.bindingexample1.viewmodels.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final UserModel userModel = new UserModel();
        userModel.setId("001");
        userModel.setName("Soknara sa");
        mainBinding.setUser(new UserViewModel(userModel));

        mainBinding.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBinding.getUser().setId("002");
                mainBinding.getUser().setName("Pisey Sen");
            }
        });

        final BindDynamicRViewAdapter<ProductViewModel> bindRecyclerViewAdapter = new BindDynamicRViewAdapter<>(com.example.pisey.bindingexample1.BR.product, R.layout.item_product);
        mainBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.recycler.setAdapter(bindRecyclerViewAdapter);
        bindRecyclerViewAdapter.setOnCustomItemClickListener(new OnCustomItemClickListener<ProductViewModel>() {
            @Override
            public void onItemClick(ProductViewModel object) {
                object.setName("New Name");
                bindRecyclerViewAdapter.updateItem(object);
                Toast.makeText(MainActivity.this, "data " + object.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        bindRecyclerViewAdapter.setOnCustomItemLongClickListener(new OnCustomItemLongClickListener<ProductViewModel>() {
            @Override
            public void onItemLongClick(ProductViewModel object) {
                bindRecyclerViewAdapter.removeItem(object);
            }
        });

        ProductModel product;
        for (int i = 0; i < 100; i++) {
            product = new ProductModel();
            product.setId("001");
            product.setName("Soknara sa" + i);
            product.setPrice(i + 1);
            bindRecyclerViewAdapter.addItem(new ProductViewModel(product));
        }
    }
}
