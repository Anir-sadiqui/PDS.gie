package org.giefront.DTO;

public class AchatDetail {
    private Achat achat;
    private Product product;
    private int quantity;
    private double TotalPrice;

    public AchatDetail(Product product, int quantity, double totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.TotalPrice = totalPrice;
    }

    public Achat getAchat() {
        return achat;
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

    public void setAchat(Achat achat) {
        this.achat = achat;
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
    public AchatDetail(){}
}
