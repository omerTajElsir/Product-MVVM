/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.erikjhordanrey.product_mvvm;

import android.view.View;
import io.github.erikjhordanrey.product_mvvm.model.Product;
import io.github.erikjhordanrey.product_mvvm.viewmodel.ProductDetailViewModel;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailViewModelTest {

    @Test
    public void shouldGetURLPictureProfile() {
        Product product = givenProduct();
        ProductDetailViewModel productDetailViewModel = givenProductDetailViewModel(product);

        assertEquals(product.getPic(), productDetailViewModel.getPic());
    }

    @Test
    public void shouldGetUserName() {
        Product product = givenProduct();
        ProductDetailViewModel productDetailViewModel = givenProductDetailViewModel(product);

        assertEquals(product.getName(), productDetailViewModel.getFullUserName());
    }

    @Test
    public void shouldGetPrice() {
        Product product = givenProduct();
        ProductDetailViewModel productDetailViewModel = givenProductDetailViewModel(product);

        assertEquals(product.getPrice(), productDetailViewModel.getPrice());
    }

    @Test
    public void shouldGetDesc() {
        Product product = givenProduct();
        ProductDetailViewModel productDetailViewModel = givenProductDetailViewModel(product);

        assertEquals(product.getDesc(), productDetailViewModel.getDesc());
    }


    private Product givenProduct() {
        return FakeRandomUserGeneratorAPI.getProduct();
    }

    private ProductDetailViewModel givenProductDetailViewModel(Product product) {
        return new ProductDetailViewModel(product);
    }
}
