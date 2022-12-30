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
            final String msg = "there is no product identified by : "+productId;
            throw new ProductCollectionException(msg);
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
            final String msg = "the indicated id is already bind to a product: "+product.getId();
            throw new ProductCollectionException(msg);
        }
        return this.products.add(product);
    }

    @Override
    public boolean deleteProduct(long productId, long userId) throws ProductCollectionException {
        Optional<Product> productWrapper = this.products
                .stream()
                .filter(product -> product.getId() == productId && product.getUserId() == userId)
                .findFirst();
        if (productWrapper.isEmpty()) {
            final String msg = "there is no product identified by: "+productId;
            throw new ProductCollectionException(msg);
        }

        Product product = productWrapper.get();
        return this.products.remove(product);
    }

    @Override
    public Product updateProduct(Product product, long userId) throws ProductCollectionException {
        Optional<Product> productWrapper = this.products
                .stream()
                .filter(p -> p.getId() == product.getId() && p.getUserId() == userId)
                .findFirst();
        if (productWrapper.isEmpty()) {
            final String msg = "there is no product identified by : "+product.getId();
            throw new ProductCollectionException(msg);
        }

        Product p = productWrapper.get();

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
