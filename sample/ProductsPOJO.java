package sample;

public class ProductsPOJO {
    String name;
    int price;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductsPOJO(int id,String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}