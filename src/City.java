import java.util.Random;

public class City {
    private String name;
    private int distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public City(String name) {
        this.name = name;
        setRandomDistance();
    }

    void setRandomDistance(){
        Random rnd = new Random();
        setDistance(rnd.nextInt(51) + 50);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }
}
