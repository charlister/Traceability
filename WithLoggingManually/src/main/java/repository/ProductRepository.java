package repository;

import exception.ProductCollectionException;
import model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.*;
import java.util.stream.Collectors;

public class ProductRepository implements IProductRepository {
    // insertion de LOGGER
    private static final Logger LOGGER = Logger.getLogger(ProductRepository.class.getName());
    // insertion un fileHandler
    Handler fileHandler  = null;
    List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        // ajouter le fileHandler dans un block try/catch du constructeur
        try {
            // créer le fileHander
            fileHandler  = new FileHandler("./log_app.log");

            // ajouter le fileHander à LOGGER
//            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            // modifier le niveau de verbosité autorisé dans fileHander et LOGGER
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            // ajouter une invocation de log
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
    }

    public ProductRepository(List<Product> products) {
        this.products = products;
        // ajouter ces handler dans le constructeur et dans un block try/catch
        try {
            // créer le fileHander
            fileHandler  = new FileHandler("./log_app.log");

            // ajouter le fileHander à LOGGER
//            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            // modifier le niveau de verbosité autorisé dans fileHander et LOGGER
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            // ajouter une invocation de log
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
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
            // suppression / mise en commentaire de throw
//            throw new ProductCollectionException("there is no product identified by : "+productId);
            LOGGER.log(Level.WARNING, "there is no product identified by : "+productId);
            return null;
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
//            throw new ProductCollectionException("the indicated id is already bind to a product: "+product.getId());
            LOGGER.log(Level.WARNING, "the indicated id is already bind to a product: "+product.getId());
        }
        if (product.getUserId() == userId)
            return this.products.add(product);
        return false;
    }

    @Override
    public boolean deleteProduct(long productId, long userId) throws ProductCollectionException {
        Optional<Product> productWrapper = this.products
                .stream()
                .filter(product -> product.getId() == productId && product.getUserId() == userId)
                .findFirst();
        if (productWrapper.isEmpty()) {
            // suppression / mise en commentaire de throw
//            throw new ProductCollectionException("there is no product identified by : "+productId);
            LOGGER.log(Level.WARNING, "there is no product identified by : "+productId);
            return false;
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
            // suppression / mise en commentaire de throw
//            throw new ProductCollectionException("there is no product identified by : "+productId);
            LOGGER.log(Level.WARNING, "there is no product identified by : "+product.getId());
            return null;
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
