package Object;

public class Contractor {
    public int id;
    public String name;
    public String contact;
    public boolean available;

    public Contractor(int id, String name, String contact, boolean available) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.available = available;
    }

    @Override
    public String toString() {
        return name + " (" + contact + ")";
    }
}
