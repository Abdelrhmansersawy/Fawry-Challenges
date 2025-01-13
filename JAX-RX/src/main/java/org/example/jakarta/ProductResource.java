package org.example.jakarta;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.jakarta.product.Product;
import org.example.jakarta.product.ProductRepository;

import java.util.List;

@Path("/product")
public class ProductResource {

    // Add product
    @POST
    @Path("/create")
    public void addProduct(@QueryParam("name") String name, @QueryParam("price") String price) {
        if (name == null || price == null) {
            throw new BadRequestException("Name and price must be provided");
        }
        ProductRepository.addProduct(name, Double.parseDouble(price));
    }

    // update price of product name
    @PUT
    @Path("/update")
    public void updateProductByName(@QueryParam("name") String name, @QueryParam("price") String price) {
        if (name == null || price == null) {
            throw new BadRequestException("Name and price must be provided");
        }
        ProductRepository.updateProductByName(name, Double.parseDouble(price));
    }

    // delete product
    @DELETE
    @Path("/deleteByName")
    public void deleteProductByName(@QueryParam("name") String name) {
        if (name == null) {
            throw new BadRequestException("Name must be provided");
        }
        ProductRepository.deleteProductByName(name);
    }

    // find product by name
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findProduct")
    public List<Product> getProductByName(@QueryParam("name") String name) {
        if (name == null) {
            throw new BadRequestException("Name must be provided");
        }
        return ProductRepository.findProductsByName(name);
    }

    // show all product
    @GET
    @Path("/showAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProduct() {
        return ProductRepository.getAllProducts();
    }
}