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
package com.bhinneka.cahelek.modules.cart.domain;

import com.bhinneka.cahelek.modules.cart.domain.state.CreatedState;
import com.bhinneka.cahelek.modules.product.domain.Product;
import com.bhinneka.cahelek.modules.state.State;
import com.bhinneka.cahelek.modules.state.StateException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wurianto
 */
public class Cart {

    private Integer id;
    private final Map<Integer, Item> items;
    private Status status;
    private State state;

    public Cart() {
        this.items = new HashMap<Integer, Item>();
        this.status = Status.Created;
        this.state = new CreatedState();
    }

    public Cart(Integer id) {
        this.id = id;
        this.items = new HashMap<Integer, Item>();
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

    public void addOrUpdateItem(int itemId, Product product, int quantity) {
        if (this.items.containsKey(itemId)) {
            Item existingItem = this.items.get(itemId);
            existingItem.update(quantity, product);
        } else {
            Item item = new Item(itemId, product, quantity);
            this.items.put(item.getId(), item);
        }

    }

    public Item getItem(int itemId) {
        Item item = null;
        if (this.items.containsKey(itemId)) {
            item = this.items.get(itemId);
        }
        return item;
    }

    public Item removeItem(int itemId) {
        Item item = null;
        if (this.items.containsKey(itemId)) {
            item = this.items.remove(itemId);
        }
        return item;
    }

    public Double getTotal() {
        double total = 0.0;
        for (Item v : this.items.values()) {
            total += v.getSubTotal();
        }
        return total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
