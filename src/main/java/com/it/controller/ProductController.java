package com.it.controller;

import com.it.entity.Result;
import com.it.pojo.Product;
import com.it.pojo.User;
import com.it.service.ProductService;
import com.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping("/findProductById")
    @ResponseBody
    public Result<Product> findByProductId(Integer product_id){
        Product product = new Product();
        product.setId(product_id);
        product = productService.findProductById(product);
        return new Result<>(true,"",product);
    }

    @RequestMapping("/product")
    public ModelAndView selectProduct(){
        List<Product> products = productService.selectProduct();
//        System.out.println(product);
        modelAndView.addObject("products",products);
        modelAndView.setViewName("product");
        return modelAndView;
    }

    @RequestMapping("/editProduct")
    @ResponseBody
    public Result editProduct(Product product){
        System.out.println(product);
        if (product.getName()==""||product.getPrice()==""||product.getEan()==""||product.getSpecification()==""){
            return new Result(false, "编辑商品失败!请填写完整!");
        }
        Integer num = productService.editProduct(product);
        System.out.println(num);
        if(num != 1){
            return new Result(false, "编辑商品失败!");
        }else {
            return new Result(true, "编辑商品成功!");
        }
    }

    @RequestMapping("/addProduct")
    @ResponseBody
    public Result addProduct(Product product){
        System.out.println(product);
        if (product.getName()==""||product.getPrice()==""||product.getEan()==""||product.getSpecification()==""){
            return new Result(false, "新增商品失败!请填写完整!");
        }
        Integer num = productService.addProduct(product);
        if(num != 1){
            return new Result(false, "新增商品失败!");
        }else {
            return new Result(true, "新增商品成功!");
        }
    }

    @RequestMapping("/productSearch")
    public ModelAndView productSearch(Product product){
//        System.out.println(product);
        List<Product> products = productService.productSearch(product);
//        System.out.println(product);
        modelAndView.addObject("products",products);
        modelAndView.addObject("search",product);
        modelAndView.setViewName("product");
        return modelAndView;
    }
}

