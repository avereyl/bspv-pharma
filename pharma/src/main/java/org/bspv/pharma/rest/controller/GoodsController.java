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

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bspv.pharma.model.Goods;
import org.bspv.pharma.model.Goods.GoodsBuilder;
import org.bspv.pharma.model.Location;
import org.bspv.pharma.process.MasterdataProcessService;
import org.bspv.pharma.rest.beans.GoodsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private MasterdataProcessService masterdataProcessService;

    /**
     *
     * @param id
     * @param resource
     * @param optLock
     * @return
     */
    @PutMapping(value = "/{id}", produces = { "application/hal+json" })
    public ResponseEntity<Void> savePieceOfGoods(@PathVariable(value = "id") final UUID id,
            @RequestBody final GoodsResource resource,
            @RequestHeader(name = HttpHeaders.IF_MATCH, defaultValue = "0") final String optLock) {

        final Long version = Long.valueOf(optLock.replace("\"", ""));
        final HttpStatus expectedStatus = version.equals(0L) ? HttpStatus.CREATED : HttpStatus.OK;

        final UUID userId = UUID.randomUUID();// FIXME get from spring security context

        // build model from resource
        GoodsBuilder goodsBuilder = Goods.builder().name(resource.getName()).id(id).version(version);
        final OffsetDateTime now = OffsetDateTime.now();
        if (version.equals(0L)) {
            goodsBuilder = goodsBuilder.createdBy(userId).createdDate(now);
        } else {
            goodsBuilder = goodsBuilder.lastModifiedBy(userId).lastModifiedDate(now);
        }
        final Location defaultLocation = this.masterdataProcessService
                .findLocationById(UUID.fromString(resource.getDefaultLocationId()));
//      @formatter:off
        final Goods goodsToBeSaved = goodsBuilder
                .description(resource.getDescription())
                .deprecatedDate(resource.getDeprecatedDate())
                .minimumOrderQuantity(resource.getMinimumOrderQuantity())
                .maximumOrderQuantity(resource.getMaximumOrderQuantity())
                .optimumOrderQuantity(resource.getOptimumOrderQuantity())
                .defaultLocation(defaultLocation)
                .build();
//      @formatter:on

        // save entity FIXME catch exceptions and react
        final Goods savedGoods = this.masterdataProcessService.save(goodsToBeSaved);

        // build response
        final BodyBuilder responseEntityBuilder = ResponseEntity.status(expectedStatus)
                .eTag(savedGoods.getVersion().toString());
        savedGoods.getLastModifiedDate().ifPresent(odt -> responseEntityBuilder.lastModified(odt.toZonedDateTime()));
        return responseEntityBuilder.build();
    }

    /**
     *
     * @param id
     * @param optLock
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePieceOfGoods(@PathVariable(value = "id") final UUID id,
            @RequestHeader(name = HttpHeaders.IF_MATCH) final String optLock) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     *
     * @param id
     */
    @GetMapping(value = "/{id}", produces = { "application/hal+json" })
    public ResponseEntity<GoodsResource> readPieceOfGoods(@PathVariable(value = "id") final UUID id) {

        final Goods goods = null;
        if (goods == null) {
            return ResponseEntity.notFound().build();
        }
        final GoodsResource goodsResource = null;
        final BodyBuilder responseEntityBuilder = ResponseEntity.ok().eTag(goods.getVersion().toString());
        goods.getLastModifiedDate().ifPresent(odt -> responseEntityBuilder.lastModified(odt.toZonedDateTime()));
        return responseEntityBuilder.body(goodsResource);
    }

    @GetMapping(value = "/", produces = { "application/hal+json" })
    public PagedResources<Resource<Goods>> listGoods(
            @RequestParam(value = "range", required = false) final String range,
            @RequestParam(value = "sort", required = false, defaultValue = "") final String sort) {

        final List<Goods> content = Arrays.asList(Goods.builder().name("Dummy goods")
                .createdBy(UUID.fromString("03b2ca47-d8ac-40a9-8579-392c23905878")).build());
        final PageMetadata metadata = new PageMetadata(10, 0, 15);
        final PagedResources<Resource<Goods>> goods = PagedResources.wrap(content, metadata);
        // goods.add(link);

        return goods;
    }

}
