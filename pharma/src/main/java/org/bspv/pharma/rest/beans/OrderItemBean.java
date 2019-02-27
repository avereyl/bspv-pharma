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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.bspv.pharma.rest.beans.validation.ValidUUID;

import lombok.Data;

@Data
public class OrderItemBean {

    public interface OrderPredefinedItemChecks {
    }

    public interface OrderExtraItemChecks {
    }

    @ValidUUID(groups = { OrderPredefinedItemChecks.class })
    @Null(groups = { OrderExtraItemChecks.class })
    private String goodsId;// for predefined goods

    @Null(groups = { OrderPredefinedItemChecks.class })
    @NotNull(groups = { OrderExtraItemChecks.class })
    private String extraGoods;// for extra goods

    @NotNull
    @Min(value = 0L)
    private Long quantity;

}
