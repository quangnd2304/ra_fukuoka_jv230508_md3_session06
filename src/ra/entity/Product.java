package ra.entity;

import java.util.Scanner;

public class Product {
    private int productId;
    private String productName;
    private float price;
    private boolean productStatus;

    public Product() {
    }

    public Product(int productId, String productName, float price, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productStatus = productStatus;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner){
        System.out.println("Nhập vào tên sản phẩm:");
        this.productName = scanner.nextLine();
        System.out.println("Nhập vào giá sản phẩm:");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào trạng thái sản phẩm:");
        this.productStatus = Boolean.parseBoolean(scanner.nextLine());
    }
    public void displayData(){
        System.out.printf("Mã SP: %d - Tên SP: %s - Giá: %f - Trạng thái: %b\n",this.productId,this.productName,this.price,this.productStatus);
    }
}
