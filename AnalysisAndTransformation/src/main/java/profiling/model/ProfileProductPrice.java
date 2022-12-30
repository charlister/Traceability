package profiling.model;

import java.util.Date;

public class ProfileProductPrice extends Profile{
    private long productId;
    private float productPrice;

    public ProfileProductPrice() {
    }

    public ProfileProductPrice(long userId, Date timestamp, String event, String action, long productId, float productPrice) {
        super(userId, timestamp, event, action);
        this.productId = productId;
        this.productPrice = productPrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ProfileProductPrice{" +
                "productId=" + productId +
                ", productPrice=" + productPrice +
                ", id=" + id +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                ", event='" + event + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
