package sample;

public class AdminclassPOJO {
    String name;
    int price;
    String guarantee;
    String category;

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

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public void  setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public AdminclassPOJO(String name, int price, String guarantee, String category) {
        this.name = name;
        this.price = price;
        this.guarantee = guarantee;
        this.category = category;
    }
}
