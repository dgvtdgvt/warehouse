package com.it.controller;

import cn.hutool.core.date.DateTime;
import com.it.entity.Result;
import com.it.pojo.Operations;
import com.it.pojo.Product;
import com.it.pojo.User;
import com.it.pojo.Warehouse;
import com.it.service.OperationsService;
import com.it.service.ProductService;
import com.it.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OperationsService operationsService;

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping("/warehouse")
    public ModelAndView findWarehouse(){
        List<Warehouse> warehouses = warehouseService.findWarehouse();
//        System.out.println(warehouses);
        modelAndView.addObject("warehouses",warehouses);
        modelAndView.setViewName("warehouse");
        return modelAndView;
    }

    @RequestMapping("/ProductIn")
    @ResponseBody
    public Result ProductIn(Warehouse warehouse,HttpServletRequest request){
        if (warehouse.getProduct_quantity()==null || warehouse.getName()==""){
            return new Result<>(false,"商品入库失败!请填写完整!");
        }

        DateTime dateTime = new DateTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = dateFormat.format(dateTime);

        User user = (User) request.getSession().getAttribute("USER");

        Integer quantity = warehouse.getProduct_quantity();

//        获取仓库信息
        Warehouse warehouse1 = warehouseService.findWarehouseByproductIdAndName(warehouse);
        if (warehouse1 != null){
            warehouse1.setProduct_quantity(warehouse1.getProduct_quantity()+warehouse.getProduct_quantity());
            Integer num = warehouseService.updateProduct(warehouse1);
            return getOperations(time, user, quantity, warehouse1, num);
        }else {
//                      获取商品信息
            Product product =new Product();
            product.setId(warehouse.getProduct_id());
            product = productService.findProductById(product);

            warehouse.setProduct_name(product.getName());
            warehouse.setProduct_ean(product.getEan());
            warehouse.setProduct_price(product.getPrice());
            warehouse.setProduct_specification(product.getSpecification());

            Integer num = warehouseService.addWarehouse(warehouse);
            return getOperations(time, user, quantity, warehouse, num);
        }
    }

    private Result getOperations(String time, User user, Integer quantity, Warehouse warehouse1, Integer num) {
        if (num != 0){

            Operations operations = new Operations();
            operations.setProduct_id(warehouse1.getProduct_id());
            operations.setProduct_name(warehouse1.getProduct_name());
            operations.setProduct_ean(warehouse1.getProduct_ean());
            operations.setProduct_price(warehouse1.getProduct_price());
            operations.setProduct_specification(warehouse1.getProduct_specification());
            operations.setQuantity(quantity);
            operations.setWarehouse_name(warehouse1.getName());
            operations.setType("IN");
            operations.setUser(user.getName());
            operations.setTime(time);
            operationsService.addOperations(operations);

            return new Result<>(true,"商品入库成功");
        }else {
            return new Result<>(false,"商品入库失败");
        }
    }


    @RequestMapping("/productOut")
    @ResponseBody
    public Result productOut(Warehouse warehouse,HttpServletRequest request){
        if (warehouse.getProduct_quantity()==null){
            return new Result<>(false,"商品出库失败!请填写完整!");
        }
        DateTime dateTime = new DateTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = dateFormat.format(dateTime);
        User user = (User) request.getSession().getAttribute("USER");
        Integer quantity = warehouse.getProduct_quantity();
        warehouse = warehouseService.findWarehouseById(warehouse);
        if (quantity>warehouse.getProduct_quantity()){
            return new Result<>(false,"商品出库失败!商品数量不足!");
        }
        warehouse.setProduct_quantity(warehouse.getProduct_quantity()-quantity);
        Integer num = warehouseService.updateProduct(warehouse);
        if (num != 0){

            Operations operations = new Operations();
            operations.setProduct_id(warehouse.getProduct_id());
            operations.setProduct_name(warehouse.getProduct_name());
            operations.setProduct_ean(warehouse.getProduct_ean());
            operations.setProduct_price(warehouse.getProduct_price());
            operations.setProduct_specification(warehouse.getProduct_specification());
            operations.setQuantity(quantity);
            operations.setWarehouse_name(warehouse.getName());
            operations.setType("OUT");
            operations.setUser(user.getName());
            operations.setTime(time);
            operationsService.addOperations(operations);

            return new Result<>(true,"商品出库成功");
        }else {
            return new Result<>(false,"商品出库失败");
        }
    }

    @RequestMapping("/warehouseSearch")
    public ModelAndView warehouseSearch(Warehouse warehouse){
//        System.out.println(warehouse);
        List<Warehouse> warehouses = warehouseService.warehouseSearch(warehouse);
        modelAndView.addObject("warehouses",warehouses);
        modelAndView.addObject("search",warehouse);
        modelAndView.setViewName("warehouse");
        return modelAndView;
    }
}
