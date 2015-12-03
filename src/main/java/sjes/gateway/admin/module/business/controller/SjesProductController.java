package sjes.gateway.admin.module.business.controller;

import client.api.item.ProductFeign;
import client.api.item.domain.Product;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangzhe on 15-12-3.
 */
@RestController
@RequestMapping("/sjes_product")
public class SjesProductController {
    @Autowired
    private ProductFeign productFeign;

    /**
     *商品信息维护列表
     * @return 分页列表
     */
    @RequestMapping("/informationList")
    public Object commodityInformationList(int page, int limit,Integer status,String name,String sn,Long id){
        Product searchProduct = new Product();
        searchProduct.setStatus(status);
        searchProduct.setId(id);
        searchProduct.setName(name);
        searchProduct.setSn(sn);
        SearchCoditionModel<Product> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(searchProduct);
        PageModel<Product> productPageModel = productFeign.search(searchCoditionModel);
        return productPageModel;
    }

    /**
     * 根据主键得到商品信息
     * @param id 主键
     * @return 商品信息
     */
    @RequestMapping(value = "/findInformationById/{id}", method = RequestMethod.GET)
    public Product findInformationById(@PathVariable("id") Long id){
          return productFeign.findById(id);
    }
}
