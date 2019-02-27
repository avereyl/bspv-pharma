/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bspv.pharma.rest.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.bspv.pharma.model.Order;
import org.bspv.pharma.rest.beans.OrderBean;
import org.bspv.pharma.rest.beans.OrderItemBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @GetMapping(value = "/{id}", produces = { "application/hal+json" })
    public Resource<Order> readOrder(@PathVariable(name = "id") final UUID id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping(value = "/{id}/events/", produces = { "application/hal+json" })
    public Resource<Order> readOrderEvents(@PathVariable(name = "id") final UUID id) {
        throw new UnsupportedOperationException();
    }

    @PutMapping(value = "/")
    public void saveOrder(@RequestBody @Valid final OrderBean orderBean) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}")
    public void cancelOrder(@PathVariable(name = "id") final UUID id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ByteArrayResource> printOrder(@PathVariable(name = "id") final UUID id) {
        throw new UnsupportedOperationException();
    }

    @PostMapping(value = "/{id}/dispatch")
    public ResponseBodyEmitter printAndSendOrder(@PathVariable(name = "id") final UUID id) {
        throw new UnsupportedOperationException();
    }

    @PostMapping(value = "/{id}/items/")
    public void orderNMorePredefinedItems(@PathVariable(name = "id") final UUID id,
            @RequestBody @Validated(OrderItemBean.OrderPredefinedItemChecks.class) final OrderItemBean orderItemBean) {
        throw new UnsupportedOperationException();
    }

    @PutMapping(value = "/{id}/items/")
    public void orderStrictlyNPredefinedItems(@PathVariable(name = "id") final UUID id,
            @RequestBody @Validated(OrderItemBean.OrderPredefinedItemChecks.class) final OrderItemBean orderItemBean) {
        throw new UnsupportedOperationException();

    }

    @PostMapping(value = "/{id}/extras/")
    public void orderNMoreExtraItems(@PathVariable(name = "id") final UUID id,
            @RequestBody @Validated(OrderItemBean.OrderExtraItemChecks.class) final OrderItemBean orderItemBean) {
        throw new UnsupportedOperationException();
    }

    @PutMapping(value = "/{id}/extras/")
    public void orderStrictlyNExtraItems(@PathVariable(name = "id") final UUID id,
            @RequestBody @Validated(OrderItemBean.OrderExtraItemChecks.class) final OrderItemBean orderItemBean) {
        throw new UnsupportedOperationException();
    }

}
