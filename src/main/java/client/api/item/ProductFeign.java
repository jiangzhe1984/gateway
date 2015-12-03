package client.api.item;

import client.api.item.domain.Product;
import client.api.item.domain.ProductModel;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by mac on 15/8/28.
 */
@FeignClient("sjes-api-item")
@RequestMapping(value = "products")
public interface ProductFeign {

    /**
     * 根据商品Id得到商品详细信息
     * @param erpGoodsId ERP商品管理码
     * @return 详细信息
     */
    @RequestMapping(value = "getProductDetail", method = RequestMethod.GET)
    ProductModel getProductDetail(@RequestParam("erpGoodsId") Long erpGoodsId, @RequestParam("status") Integer status);

    /**
     * 分页查询单品列表
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<Product> search(SearchCoditionModel<Product> searchCoditionModel);


    /**
     * 根据主键得到商品信息
     * @param id 主键
     * @return 商品信息
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    Product findById(@RequestParam("id")  Long id);


}
