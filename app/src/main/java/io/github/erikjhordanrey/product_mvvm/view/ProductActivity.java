/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.erikjhordanrey.product_mvvm.view;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import io.github.erikjhordanrey.product_mvvm.R;
import io.github.erikjhordanrey.product_mvvm.databinding.ProductActivityBinding;
import io.github.erikjhordanrey.product_mvvm.viewmodel.ProductViewModel;
import java.util.Observable;
import java.util.Observer;

public class ProductActivity extends AppCompatActivity implements Observer {

    private ProductViewModel productViewModel;

    private ProductActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupListProductView(binding.recyclerProduct);
        setupObserver(productViewModel);
        productViewModel.initializeViews();
        productViewModel.fetchProductList();
    }



    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.product_activity);
        productViewModel = new ProductViewModel(this);
        binding.setMainViewModel(productViewModel);
    }

    private void setupListProductView(RecyclerView recyclerProduct) {
        ProductAdapter adapter = new ProductAdapter();
        recyclerProduct.setAdapter(adapter);
        recyclerProduct.setHasFixedSize(true);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productViewModel.reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof ProductViewModel) {
            ProductAdapter productAdapter = (ProductAdapter) binding.recyclerProduct.getAdapter();
            ProductViewModel productViewModel = (ProductViewModel) observable;
            if (productAdapter != null) {
                productAdapter.setProductList(productViewModel.getProductList());
            }
        }
    }
}
