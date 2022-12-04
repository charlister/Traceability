package repository;

import exception.ProductCollectionException;
import model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepository implements IProductRepository {
    List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    @Override
    public List<Product> getProducts(long userId) {
        return this.products
                .stream()
                .filter(product -> product.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(long productId, long userId) throws ProductCollectionException {
        Optional<Product> productWrapper = this.products
                .stream()
                .filter(product -> product.getId() == productId && product.getUserId() == userId)
                .findFirst();
        if (productWrapper.isEmpty()) {
            throw new ProductCollectionException("there is no product identified by : "+productId);
        }
        return productWrapper.get();
    }

    @Override
    public boolean addProduct(Product product, long userId) throws ProductCollectionException {
        Optional<Product> productWrapper = this.products
                .stream()
                .filter(p -> p.getId() == product.getId())
                .findFirst();
        if (productWrapper.isPresent()) {
            throw new ProductCollectionException("the indicated id is already bind to a product: "+product.getId());
        }
        if (product.getUserId() == userId)
            return this.products.add(product);
        return false;
    }

    @Override
    public boolean deleteProduct(long productId, long userId) throws ProductCollectionException {
        Product product = this.getProductById(productId, userId);
        return this.products.remove(product);
    }

    @Override
    public Product updateProduct(Product product, long userId) throws ProductCollectionException {
        Product p = this.getProductById(product.getId(), userId);
        if (product.getName()!=null && !product.getName().trim().isEmpty()) {
            p.setName(product.getName());
        }
        p.setPrice(product.getPrice());
        if (product.getExpirationDate()!=null && product.getExpirationDate().after(new Date())) {
            p.setExpirationDate(product.getExpirationDate());
        }
        return p;
    }
}
