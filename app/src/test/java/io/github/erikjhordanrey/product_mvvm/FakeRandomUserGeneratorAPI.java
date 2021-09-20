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

import io.github.erikjhordanrey.product_mvvm.model.Product;

import java.util.ArrayList;
import java.util.List;

public class FakeRandomUserGeneratorAPI {

    private static final String PRODUCT_NAME = "Nike Shoe";
    private static final String PRODUCT_PRICE = "25";
    private static final String PRODUCT_IMAGE = "https://i.guim.co.uk/img/media/79adb790d149248826c27ae94deb2b19d805bc05/0_254_6193_3714/master/6193.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=813c9ec64abaedf84ce825f96a42eb56";
    private static final String PRODUCT_DESC = "DEscription";

    public static List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(getProduct());
        }
        return products;
    }

    public static Product getProduct() {
        final Product product = new Product();
        product.setPicture(PRODUCT_IMAGE);
        product.setPrice(PRODUCT_PRICE);
        product.setDesc(PRODUCT_DESC);
        product.setName(PRODUCT_NAME);
        return product;
    }
}
