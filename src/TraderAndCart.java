import java.util.List;
import java.util.Random;

public class TraderAndCart {
    private double loadCapacity;
    private int speed;
    private double money;
    private boolean stopped = false;
    private List<Product> products;

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void upSpeed(int speed){
        setSpeed(Math.min(getSpeed() + speed, 5));
    }

    public void downSpeed(int speed){
        setSpeed(Math.max(getSpeed() - speed, 1));
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public TraderAndCart(double loadCapacity, double money) {
        Random rnd = new Random();
        this.loadCapacity = loadCapacity;
        this.speed = rnd.nextInt(5) + 1;
        this.money = money;
    }

}
