package Model;

public class Product {
    private String productID;
    private String productName;
    private int stock;
    private double price;

    //Constructor
    public Product(String pID, String pName, int amount, double money){
        setProductID(pID);
        setProductName(pName);
        setStock(amount);
        setPrice(money);
    }

    //Setters
    public void setProductID(String prodID){
        productID = prodID;
    }

    public void setProductName(String prodName){
        productName = prodName;
    }

    public void setStock(int howMany){
        stock = howMany;
    }

    public void setPrice(double cost){
        price = cost;
    }


    //Getters
    public String getProductID(){
        return productID;
    }

    public String getProductName(){

        return productName;
    }

    public int getStock(){
        return stock;
    }

    public double getPrice(){
        return price;
    }
}
