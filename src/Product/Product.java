package Product;

// Abstract class Product that defines properties common to all products.
public class Product {
    private int ProductId;
    private String ProductName;
    private String ProductCategory ="none";
    private double ProductPrice;

    // constructor Product properties
    public Product(int ProductId, String productName, double price, String category) {
        this.ProductId = ProductId;
        ProductName = productName;
        ProductCategory = category;
        ProductPrice = price;
    }

    public Product(int id, String productName) {

    }
    // public method getProductId() that returns the ProductID
    public int getProductId() {return ProductId;}

    public void setProductId(int ProductId) {this.ProductId = ProductId;}

    // public method getProductName() that returns the ProductName
    public String getProductName() {return ProductName;}

    public void setProductName(String productName) {ProductName = productName;}

    // public method getProductCategory() that returns the ProductCategory
    public String getProductCategory() {return ProductCategory;}

    public void setProductCategory(String category) {ProductCategory = category;}

    // public method getProductPrice() that returns the ProductPrice
    public double getProductPrice() {return ProductPrice;}

    public void setProductPrice(double price) {ProductPrice = price;}


    @Override
    public String toString() {
        return "Product{" + "ProductId=" + ProductId + ", ProductName='" + ProductName + '\''  + ", ProductCategory='" + ProductCategory + ", ProductPrice=" + ProductPrice +'\'' + '}';
    }
}

