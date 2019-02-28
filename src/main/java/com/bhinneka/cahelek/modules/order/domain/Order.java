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

import com.bhinneka.cahelek.modules.state.State;
import com.bhinneka.cahelek.modules.cart.domain.Cart;
import com.bhinneka.cahelek.modules.order.domain.state.CreatedState;
import com.bhinneka.cahelek.modules.state.StateException;

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

    public Order(Integer id, Cart cart, Billing billing) throws StateException {
        this.id = id;
        this.cart = cart;
        
        // set cart to next state (checkouted)
        this.cart.nextState();
        
        this.billing = billing;
        this.status = Status.Created;
        this.state = new CreatedState();
    }

    // process to next state
    public void nextState() throws StateException {
        this.state.next(this);
    }

    // process to previous state
    public void previousState() throws StateException {
        this.state.prev(this);
    }

    // show current state
    public State currentState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
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
