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

package com.bhinneka.cahelek.modules.order.domain.state;

import com.bhinneka.cahelek.modules.state.State;
import com.bhinneka.cahelek.modules.order.domain.Order;
import com.bhinneka.cahelek.modules.order.domain.Status;

/**
 *
 * @author wurianto
 */
public class PendingState implements State<Order> {

    @Override
    public void next(Order order) {
        order.setStatus(Status.Paid);
        order.setState(new PaidState());
    }

    @Override
    public void prev(Order order) {
        order.setStatus(Status.Created);
        order.setState(new CreatedState());
    }
    
}
