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

import com.bhinneka.cahelek.modules.product.domain.Product;

/**
 *
 * @author wurianto
 */
public class Item {

    private Integer id;
    private Product product;
    private Integer quantity;
    private Double subTotal;

    public Item() {

    }

    public Item(Integer id, Product product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.subTotal = quantity * product.getPrice();
    }

    public void update(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.subTotal = quantity * product.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + ", subTotal=" + subTotal + '}';
    }

}
