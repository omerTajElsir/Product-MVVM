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
import android.view.View;
import io.github.erikjhordanrey.product_mvvm.viewmodel.ProductViewModel;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class) public class ProductViewModelTest {

    private Context mockContext = mock(Context.class);

    private ProductViewModel productViewModel;

    @Before public void setUpMainViewModelTest() {
        productViewModel = new ProductViewModel(mockContext);
    }

    @Test public void ensureTheViewsAreInitializedCorrectly() {
        productViewModel.initializeViews();

        assertEquals(View.GONE, productViewModel.productLabel.get());
        assertEquals(View.GONE, productViewModel.productRecycler.get());
        assertEquals(View.VISIBLE, productViewModel.productProgress.get());
    }
}
