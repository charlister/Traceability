package repository;
import java.util.List;
import exception.ProductCollectionException;
import model.Product;
public interface IProductRepository {
    List<Product> getProducts(long userId);

    Product getProductById(long productId, long userId) throws ProductCollectionException;

    boolean addProduct(Product product, long userId) throws ProductCollectionException;

    boolean deleteProduct(long productId, long userId) throws ProductCollectionException;

    Product updateProduct(Product product, long userId) throws ProductCollectionException;
}