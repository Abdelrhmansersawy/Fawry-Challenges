package product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductRepository {
    private static List<Product> products = new ArrayList<>();

    public static void addProduct(String name , double price){
        Product createdProduct = new Product(name , price);
        products.add(createdProduct);
    }
    public static List<Product> findProductsByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
    public static boolean isExistProductByName(String name){
        return products.stream()
                .anyMatch((product -> product.getName().equalsIgnoreCase(name)));
    }
    public static void deleteProductByName(String name){
        products = products.stream()
                .filter(product -> !product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public static List<Product> getAllProducts(){ return products; }
}
