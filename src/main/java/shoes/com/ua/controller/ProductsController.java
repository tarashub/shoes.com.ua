package shoes.com.ua.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import shoes.com.ua.entity.Products;
import shoes.com.ua.services.ProductsService;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("saveToBag")
    public String saveToBag(@RequestParam("brand") String brand, @RequestParam("price") double price,
                            @RequestParam("type") String type, @RequestParam("sex") String sex,
                            @RequestParam("size") double size, @RequestParam("width") String width,
                            @RequestParam("picture") MultipartFile multipartFile
    ) {

        String path = System.getProperty("user.home") + File.separator + "productsPicture\\";

        try {
            multipartFile.transferTo(new File(path + multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Products products = new Products();
        products.setBrand(brand);
        products.setPrice(price);
        products.setType(type);
        products.setSex(sex);
        products.setSize(size);
        products.setWidth(width);
        products.setPicture("\\productsPicture\\" + multipartFile.getOriginalFilename());
        productsService.save(products);

        return "saveToBag";
    }

}
