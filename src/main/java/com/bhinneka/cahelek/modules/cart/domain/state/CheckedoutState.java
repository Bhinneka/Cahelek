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
package com.bhinneka.cahelek.modules.cart.domain.state;

import com.bhinneka.cahelek.modules.cart.domain.Cart;
import com.bhinneka.cahelek.modules.cart.domain.Status;
import com.bhinneka.cahelek.modules.state.State;
import com.bhinneka.cahelek.modules.state.StateException;

/**
 *
 * @author wurianto
 */
public class CheckedoutState implements State<Cart> {

    public void next(Cart cart) throws StateException {
        throw new StateException("this cart already checkedout");
    }

    public void prev(Cart cart) throws StateException {
        cart.setStatus(Status.Created);
        cart.setState(new CreatedState());
    }

}
