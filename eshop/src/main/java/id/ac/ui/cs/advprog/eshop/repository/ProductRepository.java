package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Product edit(Product searchedProduct){
        for(int i = 0; i < productData.size(); ++i){
            String searchedProductId = searchedProduct.getProductId();
            String currentProductId = productData.get(i).getProductId();
            if (searchedProductId.equals(currentProductId)){
                String newProductName = searchedProduct.getProductName();
                int newProductQuantity = searchedProduct.getProductQuantity();
                Product oldProduct = productData.get(i);
                oldProduct.setProductName(newProductName);
                oldProduct.setProductQuantity(newProductQuantity);
                return searchedProduct;
            }
        }

        return null;
    }

    public String generateId(){
        return Integer.toString(productData.size() + 1);
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }
}
