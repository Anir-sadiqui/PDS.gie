package org.giefront.DTO;



public class AchatDetail {

    private Achat achat;


    private Product product;


    private int quantity;


    private double TotalPrice;

    public AchatDetail(Achat achat, Product product, int quantity) {
        this.achat = achat;
        this.product = product;
        this.quantity = quantity;
        this.TotalPrice = product.getPrix() * quantity;
    }

    public AchatDetail() {

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
}
