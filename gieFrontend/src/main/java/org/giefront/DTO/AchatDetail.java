package org.giefront.DTO;



public class AchatDetail {
    private long id;

    private Product product;


    private int quantity;


    private double TotalPrice;

    public AchatDetail( Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.TotalPrice = product.getPrix() * quantity;
    }

    public AchatDetail() {

    }



    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AchatDetail{" +
                ", product=" + product +
                ", quantity=" + quantity +
                ", TotalPrice=" + TotalPrice +
                '}';
    }
}
