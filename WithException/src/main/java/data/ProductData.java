package data;

import exception.ProductCollectionException;
import model.Product;
import repository.ProductRepository;

import java.util.Date;
import java.util.Random;

public class ProductData {
    public ProductData(ProductRepository repository) throws ProductCollectionException {
        buildProducts(repository);
    }

    private void buildProducts(ProductRepository repository) throws ProductCollectionException {
        Random rand = new Random();
        float price;
        long tmpUserId;
        for (int i = 0; i<10; i++) {
            price = rand.nextFloat()*3;
            tmpUserId = rand.nextLong()%2;
            repository.addProduct(new Product("product"+i, price, new Date(), tmpUserId), tmpUserId); // the last value is in [0;1] because we only define 2 users in user_data.dart
        }
    }
}
