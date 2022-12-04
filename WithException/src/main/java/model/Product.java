package model;

import java.util.Date;

public class Product {
    private static long counter = 0;
    private final long id;
    private String name;
    private float price;
    private Date expirationDate;
    private long userId;

    public Product(String name, float price, Date expirationDate, long userId) {
        this.id = ++counter;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", expirationDate=" + expirationDate +
                ", userId=" + userId +
                '}';
    }
}
