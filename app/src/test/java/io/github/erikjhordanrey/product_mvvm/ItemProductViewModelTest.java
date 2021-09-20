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

import android.content.Context;
import android.content.Intent;
import androidx.databinding.Observable;

import io.github.erikjhordanrey.product_mvvm.model.Product;
import io.github.erikjhordanrey.product_mvvm.viewmodel.ItemProductViewModel;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ItemProductViewModelTest {

    private static final String PRODUCT_NAME = "Nike Shoe";
    private static final String PRODUCT_PRICE = "25";
    private static final String PRODUCT_IMAGE = "https://i.guim.co.uk/img/media/79adb790d149248826c27ae94deb2b19d805bc05/0_254_6193_3714/master/6193.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=813c9ec64abaedf84ce825f96a42eb56";
    private static final String PRODUCT_DESC = "DEscription";

    private Context context = mock(Context.class);

    @Test
    public void shouldGetProductNAme() {
        Product product = givenProduct();
        product.setName(PRODUCT_NAME);
        ItemProductViewModel itemProductViewModel = givenItemProductViewModel(product);

        assertEquals(product.getName(), itemProductViewModel.getFullName());
    }

    @Test
    public void shouldGetProductPrice() {
        Product product = givenProduct();
        product.setPrice(PRODUCT_PRICE);
        ItemProductViewModel itemProductViewModel = givenItemProductViewModel(product);

        assertEquals(product.getPrice(), itemProductViewModel.getPrice());
    }

    @Test
    public void shouldGetProductDesc() {
        Product product = givenProduct();
        product.setDesc(PRODUCT_DESC);
        ItemProductViewModel itemProductViewModel = givenItemProductViewModel(product);

        assertEquals(product.getDesc(), itemProductViewModel.getDesc());
    }

    @Test
    public void shouldGetProductPic() {
        Product product = givenProduct();
        product.setPicture(PRODUCT_IMAGE);
        ItemProductViewModel itemProductViewModel = givenItemProductViewModel(product);

        assertEquals(product.getPic(), itemProductViewModel.getPic());
    }


    @Test
    public void shouldStartProductDetailActivityOnItemClick() {
        Product product = givenProduct();
        ItemProductViewModel itemProductViewModel = givenItemProductViewModel(product);

        itemProductViewModel.onItemClick(new MockView(context));
        verify(context).startActivity(any(Intent.class));
    }

    @Test
    public void shouldNotifyPropertyChangeWhenSetProduct() {
        Product product = givenProduct();
        ItemProductViewModel itemProductViewModel = givenItemProductViewModel(product);
        Observable.OnPropertyChangedCallback mockCallback = mock(Observable.OnPropertyChangedCallback.class);
        itemProductViewModel.addOnPropertyChangedCallback(mockCallback);
        itemProductViewModel.setProduct(product);

        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }

    private Product givenProduct() {
        return new Product();
    }

    private ItemProductViewModel givenItemProductViewModel(Product product) {
        return new ItemProductViewModel(context, product);
    }
}
