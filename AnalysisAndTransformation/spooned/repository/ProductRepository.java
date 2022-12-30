package repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import exception.ProductCollectionException;
import model.Product;
public class ProductRepository implements IProductRepository {
    private static final Logger LOGGER;

    private final Handler fileHandler;

    List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        this.LOGGER = java.util.logging.Logger.getLogger(ProductRepository.class.getName());;
        try {
           fileHandler  = new java.util.logging.FileHandler("./traceability.log");
           LOGGER.addHandler(fileHandler);
           fileHandler.setLevel(java.util.logging.Level.ALL);
           LOGGER.setLevel(java.util.logging.Level.ALL);
        } catch (java.io.IOException e) {
           LOGGER.log(java.util.logging.Level.SEVERE, "Error occur in FileHandler.", e);
        };
    }

    public ProductRepository(List<Product> products) {
        this.products = products;
        this.LOGGER = java.util.logging.Logger.getLogger(ProductRepository.class.getName());;
        try {
           fileHandler  = new java.util.logging.FileHandler("./traceability.log");
           LOGGER.addHandler(fileHandler);
           fileHandler.setLevel(java.util.logging.Level.ALL);
           LOGGER.setLevel(java.util.logging.Level.ALL);
        } catch (java.io.IOException e) {
           LOGGER.log(java.util.logging.Level.SEVERE, "Error occur in FileHandler.", e);
        };
    }

    @Override
    public List<Product> getProducts(long userId) {
        LOGGER.log(Level.INFO, String.format("{\"user_id\":%d}", userId));
        return this.products.stream().filter(product -> product.getUserId() == userId).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(long productId, long userId) throws ProductCollectionException {
        Optional<Product> productWrapper = this.products.stream().filter(product -> (product.getId() == productId) && (product.getUserId() == userId)).findFirst();
        if (productWrapper.isEmpty()) {
            final String msg = "there is no product identified by : " + productId;
            LOGGER.log(Level.WARNING, String.format("{\"user_id\":%d,\"msg\":%s}", userId, msg));
            throw new ProductCollectionException(msg);
        }
        LOGGER.log(Level.INFO, String.format("{\"user_id\":%d,\"product_id\":%d,\"product_price\":%s}", userId, productId, productWrapper.get().getPrice()));
        return productWrapper.get();
    }

    @Override
    public boolean addProduct(Product product, long userId) throws ProductCollectionException {
        Optional<Product> productWrapper = this.products.stream().filter(p -> p.getId() == product.getId()).findFirst();
        if (productWrapper.isPresent()) {
            final String msg = "the indicated id is already bind to a product: " + product.getId();
            LOGGER.log(Level.WARNING, String.format("{\"user_id\":%d,\"msg\":%s}", userId, msg));
            throw new ProductCollectionException(msg);
        }
        LOGGER.log(Level.INFO, String.format("{\"user_id\":%d}", userId));
        return this.products.add(product);
    }

    @Override
    public boolean deleteProduct(long productId, long userId) throws ProductCollectionException {
        Optional<Product> productWrapper = this.products.stream().filter(product -> (product.getId() == productId) && (product.getUserId() == userId)).findFirst();
        if (productWrapper.isEmpty()) {
            final String msg = "there is no product identified by: " + productId;
            LOGGER.log(Level.WARNING, String.format("{\"user_id\":%d,\"msg\":%s}", userId, msg));
            throw new ProductCollectionException(msg);
        }
        Product product = productWrapper.get();
        LOGGER.log(Level.INFO, String.format("{\"user_id\":%d}", userId));
        return this.products.remove(product);
    }

    @Override
    public Product updateProduct(Product product, long userId) throws ProductCollectionException {
        Optional<Product> productWrapper = this.products.stream().filter(p -> (p.getId() == product.getId()) && (p.getUserId() == userId)).findFirst();
        if (productWrapper.isEmpty()) {
            final String msg = "there is no product identified by : " + product.getId();
            LOGGER.log(Level.WARNING, String.format("{\"user_id\":%d,\"msg\":%s}", userId, msg));
            throw new ProductCollectionException(msg);
        }
        Product p = productWrapper.get();
        if ((product.getName() != null) && (!product.getName().trim().isEmpty())) {
            p.setName(product.getName());
        }
        p.setPrice(product.getPrice());
        if ((product.getExpirationDate() != null) && product.getExpirationDate().after(new Date())) {
            p.setExpirationDate(product.getExpirationDate());
        }
        LOGGER.log(Level.INFO, String.format("{\"user_id\":%d}", userId));
        return p;
    }
}