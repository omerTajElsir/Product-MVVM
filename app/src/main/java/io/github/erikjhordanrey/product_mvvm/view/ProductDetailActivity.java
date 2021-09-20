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

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import io.github.erikjhordanrey.product_mvvm.R;
import io.github.erikjhordanrey.product_mvvm.databinding.ProductDetailActivityBinding;
import io.github.erikjhordanrey.product_mvvm.model.Product;
import io.github.erikjhordanrey.product_mvvm.viewmodel.ProductDetailViewModel;

public class ProductDetailActivity extends AppCompatActivity {

  private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";

  private ProductDetailActivityBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.product_detail_activity);
    setSupportActionBar(binding.toolbar);
    displayHomeAsUpEnabled();
    getExtrasFromIntent();
  }

  public static Intent launchDetail(Context context, Product product) {
    Intent intent = new Intent(context, ProductDetailActivity.class);
    intent.putExtra(EXTRA_PEOPLE, product);
    return intent;
  }

  private void displayHomeAsUpEnabled() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  private void getExtrasFromIntent() {
    Product product = (Product) getIntent().getSerializableExtra(EXTRA_PEOPLE);
    ProductDetailViewModel productDetailViewModel = new ProductDetailViewModel(product);
    binding.setProductDetailViewModel(productDetailViewModel);
    setTitle(product.getName());
  }
}
