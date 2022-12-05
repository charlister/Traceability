package repository;

import exception.ProductCollectionException;
import model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getProducts(long userId);
    Product getProductById(long productId, long userId) throws ProductCollectionException;
    boolean addProduct(Product product, long userId) throws ProductCollectionException;
    boolean deleteProduct(long productId, long userId) throws ProductCollectionException;
    Product updateProduct(Product product, long userId) throws ProductCollectionException;
}
