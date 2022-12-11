package sample;

public class ManagerPOJO {
    int id;
    String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ManagerPOJO(int id, String date) {
        this.id = id;
        this.date = date;
    }
}
