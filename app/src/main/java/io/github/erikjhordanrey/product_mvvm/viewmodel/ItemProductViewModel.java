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

package io.github.erikjhordanrey.product_mvvm.viewmodel;

import android.content.Context;
import android.view.View;
import androidx.databinding.BaseObservable;
import io.github.erikjhordanrey.product_mvvm.model.Product;
import io.github.erikjhordanrey.product_mvvm.view.ProductDetailActivity;

public class ItemProductViewModel extends BaseObservable {

    private final Context context;
    private Product product;

    public ItemProductViewModel(Context context, Product product) {
        this.context = context;
        this.product = product;
    }

    public String getFullName() {
        return product.getName();
    }

    public String getPic() {
        return product.getPic();
    }

    public String getDesc() {
        return product.getDesc();
    }

    public String getPrice() {
        return product.getPrice();
    }

    public void onItemClick(View view) {
        context.startActivity(ProductDetailActivity.launchDetail(view.getContext(), product));
    }

    public void onDel(View view) {
        context.startActivity(ProductDetailActivity.launchDetail(view.getContext(), product));
    }



    public void setProduct(Product product) {
        this.product = product;
        notifyChange();
    }
}
