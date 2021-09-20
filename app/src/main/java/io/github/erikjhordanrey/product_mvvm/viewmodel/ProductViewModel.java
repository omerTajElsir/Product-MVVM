/**
 * Copyright 2016 Erik Jhordan Rey. <p/> Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at <p/> http://www.apache.org/licenses/LICENSE-2.0 <p/> Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package io.github.erikjhordanrey.product_mvvm.viewmodel;

import android.content.Context;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.annotation.NonNull;
import android.view.View;
import io.github.erikjhordanrey.product_mvvm.ProductApplication;
import io.github.erikjhordanrey.product_mvvm.R;
import io.github.erikjhordanrey.product_mvvm.data.ProductFactory;
import io.github.erikjhordanrey.product_mvvm.data.ProductResponse;
import io.github.erikjhordanrey.product_mvvm.data.ProductService;
import io.github.erikjhordanrey.product_mvvm.model.Product;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ProductViewModel extends Observable {

    public ObservableInt productProgress;
    public ObservableInt productRecycler;
    public ObservableInt productLabel;
    public ObservableField<String> messageLabel;

    private List<Product> productList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ProductViewModel(@NonNull Context context) {

        this.context = context;
        this.productList = new ArrayList<>();
        productProgress = new ObservableInt(View.GONE);
        productRecycler = new ObservableInt(View.GONE);
        productLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_loading_product));



    }

    public void onClickFabLoad(View view) {

        Product newProduct = new Product();
        newProduct.setName("New Shoe");
        newProduct.setDesc("Up Your Game With The Latest Shoes, Clothes & Accessories at Nike.com");
        newProduct.setPrice("56");
        newProduct.setPicture("https://i.guim.co.uk/img/media/79adb790d149248826c27ae94deb2b19d805bc05/0_254_6193_3714/master/6193.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=813c9ec64abaedf84ce825f96a42eb56");
        addItem(newProduct);

    }

    public void onClickRefresh(View view) {
        productList.clear();
        initializeViews();
        fetchProductList();
    }


    //It is "public" to show an example of test
    public void initializeViews() {
        productLabel.set(View.GONE);
        productRecycler.set(View.GONE);
        productProgress.set(View.VISIBLE);
    }

    // NOTE: This method can return the rx observer and just subscribe to it in the activity or fragment,
    // an Activity or Fragment needn't to implement from the Observer java class
    // (it was my first approach to avoid RX in UI now we can use LiveData instead)
    public void fetchProductList() {

        ProductApplication productApplication = ProductApplication.create(context);
        ProductService productService = productApplication.getProductService();

        Disposable disposable = productService.fetchProduct(ProductFactory.RANDOM_USER_URL)
                .subscribeOn(productApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductResponse>() {
                    @Override
                    public void accept(ProductResponse productResponse) {
                        changeProductDataSet(productResponse.getProductList());
                        productProgress.set(View.GONE);
                        productLabel.set(View.GONE);
                        productRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        messageLabel.set(context.getString(R.string.error_loading_product));
                        productProgress.set(View.GONE);
                        productLabel.set(View.VISIBLE);
                        productRecycler.set(View.GONE);
                        throwable.printStackTrace();
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void changeProductDataSet(List<Product> products) {
        productList.addAll(products);
        setChanged();
        notifyObservers();
    }

    private void addItem(Product products) {
        productList.add(products);
        setChanged();
        notifyObservers();
    }


    public List<Product> getProductList() {
        return productList;
    }

    public void reset() {
        compositeDisposable.dispose();
        compositeDisposable = null;
        context = null;
    }
}
