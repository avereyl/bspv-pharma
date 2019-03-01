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

import org.bspv.pharma.model.Goods;
import org.bspv.pharma.model.Location;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @PostMapping(value = "/", produces = { "application/hal+json" })
    public Resource<Goods> addLocation(@RequestBody final Location location) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteLocation(@PathVariable(value = "id") final UUID id) {
    }

    @GetMapping(value = "/", produces = { "application/hal+json" })
    public PagedResources<Resource<Location>> listLocations(
            @RequestParam(value = "range", required = false) final String range,
            @RequestParam(value = "sort", required = false, defaultValue = "") final String sort) {

        return null;
    }

}
