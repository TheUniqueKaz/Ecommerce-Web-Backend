package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstrants;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO,
                                                 @PathVariable Long categoryId) {
        ProductDTO savedproductDTO = productService.addProduct(categoryId, productDTO);

        return new ResponseEntity<ProductDTO>( savedproductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/product")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name ="pageNumber", defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name ="pageSize", defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name ="sortBy", defaultValue =  AppConstrants.SORT_PRODUCT_BY, required = false) String sortBy,
            @RequestParam(name ="sortOrder", defaultValue = AppConstrants.SORT_DIR) String sortOrder
    ) {
        ProductResponse productResponse = productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable Long categoryId,
                                                                @RequestParam(name ="pageNumber", defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                @RequestParam(name ="pageSize", defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
                                                                @RequestParam(name ="sortBy", defaultValue =  AppConstrants.SORT_PRODUCT_BY, required = false) String sortBy,
                                                                @RequestParam(name ="sortOrder", defaultValue = AppConstrants.SORT_DIR) String sortOrder
    ) {
        ProductResponse productResponse =  productService.searchByCategory(categoryId,pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword,
                                                               @RequestParam(name ="pageNumber", defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                               @RequestParam(name ="pageSize", defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
                                                               @RequestParam(name ="sortBy", defaultValue =  AppConstrants.SORT_PRODUCT_BY, required = false) String sortBy,
                                                               @RequestParam(name ="sortOrder", defaultValue = AppConstrants.SORT_DIR, required = false) String sortOrder){
        ProductResponse productResponse = productService.searchByKeyword(keyword,pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,
                                                    @PathVariable Long productId) {
        ProductDTO savedproductDTO = productService.updateProduct(productId,productDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public  ResponseEntity<ProductDTO> deleteProductById(@PathVariable Long productId) {
        ProductDTO productDTO = productService.deleteProductById(productId);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId,
                                                         @RequestParam("Image") MultipartFile image) throws IOException {
        ProductDTO updatedProduct =  productService.updateProductImage(productId,image);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
