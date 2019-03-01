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
package org.bspv.pharma.rest.beans;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.bspv.pharma.model.Tag;
import org.bspv.pharma.rest.beans.validation.ValidUUID;
import org.springframework.hateoas.ResourceSupport;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true) // rely on super class for equals and hashCode
public class GoodsResource extends ResourceSupport {

    @NotBlank
    private String name;
    private String description = "";

    private OffsetDateTime deprecatedDate;// might be null

    @ValidUUID(nullable = true)
    private String defaultLocationId;

    @Min(value = 1L)
    private Integer minimumOrderQuantity;
    @Min(value = 1L)
    private Integer maximumOrderQuantity;
    @Min(value = 1L)
    private Integer optimumOrderQuantity;

    private Set<Tag> tags = new HashSet<>();
}
