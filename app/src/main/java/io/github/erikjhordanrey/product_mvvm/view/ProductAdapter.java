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

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.erikjhordanrey.product_mvvm.R;
import io.github.erikjhordanrey.product_mvvm.databinding.ItemProductBinding;
import io.github.erikjhordanrey.product_mvvm.model.Product;
import io.github.erikjhordanrey.product_mvvm.viewmodel.ItemProductViewModel;

import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder> implements View.OnClickListener {

    private List<Product> productList;

    ProductAdapter() {
        this.productList = Collections.emptyList();
    }

    @NonNull
    @Override
    public ProductAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding itemProductBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product, parent, false);

        return new ProductAdapterViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(ProductAdapterViewHolder holder, int position) {
        holder.bindProduct(productList.get(position));


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
               if(v.equals(R.id.delete)){
            productList.remove(0);
        }
    }

    class ProductAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;

        ProductAdapterViewHolder(ItemProductBinding binding) {
            super(binding.itemProduct);
            this.binding = binding;
            binding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }

        void bindProduct(Product product) {
            if (binding.getProductViewModel() == null) {
                binding.setProductViewModel(new ItemProductViewModel(itemView.getContext(), product));
            } else {
                binding.getProductViewModel().setProduct(product);
            }
        }
    }
}
