/**
 * Copyright (c) 2016-2019, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.module.product;

import com.jfinal.aop.Inject;
import io.jpress.core.finance.ProductInfoQuerier;
import io.jpress.module.product.model.Product;
import io.jpress.module.product.service.ProductService;

import java.math.BigDecimal;


public class JPressProductInfoQuerier implements ProductInfoQuerier {

    @Inject
    private ProductService productService;

    @Override
    public BigDecimal queryDistAmount(Object productId, Long buyerUserId, Long distUserId) {
        Product product = productService.findById(productId);
        if (product == null || !product.isNormal()) {
            return null;
        }
        Boolean distEnable = product.getDistEnable();
        return distEnable != null && distEnable ? product.getDistAmount() : null;
    }

    @Override
    public BigDecimal querySalePrice(Object productId, Long buyerUserId, Long distUserId) {
        Product product = productService.findById(productId);
        return product == null ? null : product.getPrice();
    }

    @Override
    public boolean queryStatusNormal(Object productId, Long buyerUserId) {
        Product product = productService.findById(productId);
        return product != null && product.isNormal();
    }

    @Override
    public Long queryStockAmount(Object productId) {
        return null;
    }


}
