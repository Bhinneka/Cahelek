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

package com.bhinneka.cahelek.modules.order.domain;

import com.bhinneka.cahelek.modules.order.domain.state.State;
import com.bhinneka.cahelek.modules.cart.domain.Cart;
import com.bhinneka.cahelek.modules.order.domain.state.CreatedState;

/**
 *
 * @author wurianto
 */
public class Order {

    private Integer id;
    private Cart cart;
    private Billing billing;
    private State state;
    private Status status;

    public Order() {

    }

    public Order(Integer id, Cart cart, Billing billing) {
        this.id = id;
        this.cart = cart;
        this.billing = billing;
        this.status = Status.Created;
        this.state = new CreatedState();
    }
    
    public void nextState() {
        this.state.next(this);
    }
    
    public void previousState() {
        this.state.prev(this);
    }
    
    public Double getTotal() {
        return this.cart.getTotal() + this.billing.getShippingCost();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
