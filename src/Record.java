package Product;

// Class
public class Record {

    // Instance variables
    private String name;
    private int idNumber;
    private String productCategory;

    // Default Constructor
    public Record() {
    }

    // Parameterized Constructor
    // @param name
    // @param idNumber
    // @param Category
    public Record(String name, int idNumber,
                  String productCategory) {

        // this keyword refers to current instance itself
        this.name = name;
        this.idNumber = idNumber;
        this.productCategory = productCategory;
    }

    // Getting the value of Category
    // @return the value of Category
    public String getProductCategory() {
        return productCategory;
    }

    // Set the value of Category
    // @param contactNumber new value of contactNumber
    public void setProductCategory(String Category) {

        this.productCategory = productCategory;
    }

    // Getting the value of idNumber
    // @return the value of idNumber
    public int getIdNumber() {
        return idNumber;
    }

    // Setting the value of idNumber
    // @param idNumber new value of idNumber
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    // Getting the value of name
    // @return the value of name
    public String getName() {
        return name;
    }

    // Setting the value of name
    // @param name new value of name
    public void setName(String name) {
        this.name = name;
    }

    // toString() Method
    // @return
    @Override
    public String toString() {

        // Returning the record
        return "Records{"
                + "name=" + name + ", idNumber=" + idNumber
                + ", productCategory=" + productCategory + '}';
    }
}

