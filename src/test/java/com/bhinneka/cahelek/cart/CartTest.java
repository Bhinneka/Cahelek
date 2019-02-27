/*
 * Copyright 2019 wuriyanto.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bhinneka.cahelek.cart;

import com.bhinneka.cahelek.modules.cart.domain.Cart;
import com.bhinneka.cahelek.modules.cart.domain.Item;
import com.bhinneka.cahelek.modules.product.domain.Product;
import static junit.framework.TestCase.*;

/**
 *
 * @author wurianto
 */
public class CartTest {

    @org.junit.Test
    public void testCreateCart() {
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);

        assertEquals(5000000.0, cart.getTotal());
    }

    @org.junit.Test
    public void testGetItem() {
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);

        Item expectedItem = new Item(1, nokia, 2);

        assertEquals(expectedItem.getId(), cart.getItem(1).getId());
    }

    @org.junit.Test
    public void testRemoveItem() {
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);

        Item expectedItem = new Item(1, nokia, 2);

        assertEquals(expectedItem.getId(), cart.removeItem(1).getId());
        assertNull(cart.getItem(1));
    }

    @org.junit.Test
    public void testGetTotal() {
        Product samsung = new Product(1, "Samsung Galaxy s2", 5000000.0);
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);
        cart.addOrUpdateItem(2, samsung, 1);

        assertEquals(10000000.0, cart.getTotal());
    }

}
