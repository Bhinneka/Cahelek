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
package com.bhinneka.cahelek.order;

import com.bhinneka.cahelek.modules.cart.domain.Cart;
import com.bhinneka.cahelek.modules.order.domain.Billing;
import com.bhinneka.cahelek.modules.order.domain.Order;
import com.bhinneka.cahelek.modules.order.domain.Status;
import com.bhinneka.cahelek.modules.state.StateException;
import com.bhinneka.cahelek.modules.product.domain.Product;
import static junit.framework.TestCase.*;

/**
 *
 * @author wurianto
 */
public class OrderTest {

    @org.junit.Test
    public void testCreateOrder() {
        Product samsung = new Product(1, "Samsung Galaxy s2", 5000000.0);
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);

        assertEquals(5000000.0, cart.getTotal());

        try {
            Order order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            assertEquals(Status.Created, order.getStatus());

            // assert if cart already checkedout
            assertEquals(com.bhinneka.cahelek.modules.cart.domain.Status.Checkedout, cart.getStatus());
        } catch (StateException ex) {
            assertNull(ex);
        }

    }

    @org.junit.Test
    public void testOrderSetToCheckout() {
        Product samsung = new Product(1, "Samsung Galaxy s2", 5000000.0);
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);

        assertEquals(5000000.0, cart.getTotal());

        try {
            Order order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            //set to pending
            order.nextState();
            assertEquals(Status.Pending, order.getStatus());
        } catch (StateException ex) {
            assertNull(ex);
        }

    }

    @org.junit.Test
    public void testOrderSetToPaid() {
        Product samsung = new Product(1, "Samsung Galaxy s2", 5000000.0);
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);

        assertEquals(5000000.0, cart.getTotal());

        Order order = null;
        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
        } catch (StateException ex) {
            assertNull(ex);
        }

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            //set to pending
            order.nextState();
        } catch (StateException ex) {
            assertNull(ex);
        }

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            //set to paid
            order.nextState();
            assertEquals(Status.Paid, order.getStatus());
        } catch (StateException ex) {
            assertNull(ex);
        }

    }

    @org.junit.Test
    public void testOrderSetToDelivered() {
        Product samsung = new Product(1, "Samsung Galaxy s2", 5000000.0);
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);

        assertEquals(5000000.0, cart.getTotal());

        Order order = null;

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            //set to pending
            order.nextState();
        } catch (StateException ex) {
            assertNull(ex);
        }

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            //set to paid
            order.nextState();
        } catch (StateException ex) {
            assertNull(ex);
        }

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            //set to delivered
            order.nextState();

            assertEquals(Status.Delivered, order.getStatus());

        } catch (StateException ex) {
            assertNull(ex);
        }

    }

    @org.junit.Test
    public void testOrderSetToCreatedAndFail() {

        Product samsung = new Product(1, "Samsung Galaxy s2", 5000000.0);
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);

        assertEquals(5000000.0, cart.getTotal());

        Order order = null;

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            order.previousState();
        } catch (StateException ex) {

            assertNotNull(ex);

            assertEquals(ex.getMessage(), "this order in its root state");

        }

    }

    @org.junit.Test
    public void testOrderSetToDeliveredAndFail() {
        Product samsung = new Product(1, "Samsung Galaxy s2", 5000000.0);
        Product nokia = new Product(2, "Nokia 6", 2500000.0);

        Cart cart = new Cart();
        cart.setId(1);
        cart.addOrUpdateItem(1, nokia, 2);

        assertEquals(5000000.0, cart.getTotal());

        Order order = null;

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            //set to pending
            order.nextState();
        } catch (StateException ex) {

            assertNull(ex);

        }

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            //set to paid
            order.nextState();
        } catch (StateException ex) {
            assertNull(ex);
        }

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            //set to delivered
            order.nextState();
        } catch (StateException ex) {
            assertNull(ex);
        }

        try {
            if (order == null) {
                order = new Order(1, cart, new Billing("Wuriyanto", "Banjarnegara", 15000.0));
            }
            //set to delivered again
            order.nextState();

        } catch (StateException ex) {

            assertNotNull(ex);

            assertEquals(ex.getMessage(), "this order already delivered");

        }

    }

}
