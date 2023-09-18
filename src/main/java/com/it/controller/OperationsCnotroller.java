package com.it.controller;

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
import java.util.List;

@Controller
public class OperationsCnotroller {
    @Autowired
    private OperationsService operationsService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping("/operations")
    public ModelAndView findOperations(){
        List<Operations> operations = operationsService.findOperations();
        modelAndView.addObject("operations",operations);
        modelAndView.setViewName("operations");
        return modelAndView;
    }

    @RequestMapping("/operationsSearch")
    public ModelAndView operationsSearch(Operations operations){
        System.out.println(operations);
        if (operations.getType().equals("入库")){
            operations.setType("IN");
        }else if(operations.getType().equals("出库")) {
            operations.setType("OUT");
        }else {
            operations.setType("");
        }
        List<Operations> operations1 = operationsService.operationsSearch(operations);
        modelAndView.addObject("operations",operations1);
        modelAndView.addObject("search",operations);
        modelAndView.setViewName("operations");
        return modelAndView;
    }
}
