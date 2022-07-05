public class Product {
    private double weight;
    private Goods good;
    private double quality;
    private double price;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(double weight, Goods good, double quality, double price) {
        this.weight = weight;
        this.good = good;
        this.quality = quality;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "weight=" + weight +
                ", good=" + good.getName() +
                ", quality=" + quality +
                ", price=" + price +
                '}';
    }
}
