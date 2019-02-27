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

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.bspv.pharma.rest.beans.validation.ValidUUID;

import lombok.Data;

@Data
public class LocationBean {

    @ValidUUID
    private UUID id;
    private Long version = 0L;
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    private String description = "";
}
