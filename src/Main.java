import java.util.*;

public class Main {
    static Random rnd = new Random();
    static int roadDistance;
    static TraderAndCart BARYGA = new TraderAndCart(200, rnd.nextInt(201) + 200);
    static final City[] CITIES = {
            new City("Naryn"),
            new City("Bishkek"),
            new City("Talas"),
            new City("Osh"),
            new City("Bosteri"),
            new City("Jalal-Abad"),
            new City("Batken")
    };
    static final Goods[] GOODS = Goods.values();

    static List<Double> QUALITY = new ArrayList<>(){{
        add(0.1);
        add(0.25);
        add(0.55);
        add(0.95);
        add(1.2);
    }};

    public static void main(String[] args){
        fillProducts();
        for (Product product : BARYGA.getProducts()){
            System.out.printf("Товар [%s] куплен за %.2f\n", product.getGood().getName(), product.getPrice());
        }
        run();
    }

    public static void run(){
        City chooseCity = CITIES[rnd.nextInt(7)];
        int distance = chooseCity.getDistance();
        double oldMoney = BARYGA.getMoney();
        System.out.println("Город: " + chooseCity.getName() + " расстояние до него: " + distance + " лиг");
        int day = 1;
        while (distance > 0){
            System.out.println("День №" + day);
            System.out.println("Скорость повозки: " + BARYGA.getSpeed());
            BARYGA.setStopped(false);
            roadDistance = 0;
            events(rnd.nextInt(9) + 1);
            if(!BARYGA.isStopped()) {
                distance -= BARYGA.getSpeed() + roadDistance;
            }
            day++;
            if(distance > 0) {
                System.out.println("Осталось до города: " + distance + " лиг");
            }else {
                System.out.println("Добрались до города");
            }
        }
        if(BARYGA.getProducts().size() > 0) {
            System.out.println("Началась продажа!");
            for (int i = 0; i < BARYGA.getProducts().size(); i++) {
                System.out.printf("Продал [%s] за %.2f\n", BARYGA.getProducts().get(i).getGood().getName(), BARYGA.getProducts().get(i).getPrice() * BARYGA.getProducts().get(i).getQuality());
                BARYGA.setMoney(BARYGA.getMoney() + BARYGA.getProducts().get(i).getPrice() * BARYGA.getProducts().get(i).getQuality());
            }
            BARYGA.getProducts().clear();
        }else {
            System.out.println("Товаров на продажу нету!");
        }
        if(oldMoney >= BARYGA.getMoney()){
            System.out.println("Прибыли нет");
        }else {
            System.out.println("Мы получили прибыль");
        }

    }

    static void fillProducts(){
        List<Product> productList = new ArrayList<>();
        double fullWeight = BARYGA.getLoadCapacity();
        double fullMoney = BARYGA.getMoney();
        while(true){
            double currentWeight = rnd.nextInt(11) + 10;
            double currentPrice = rnd.nextInt(20) + 40;
            if(fullWeight - currentWeight >= 0 & fullMoney - currentPrice >= 0) {
                productList.add(new Product(currentWeight, GOODS[rnd.nextInt(6)], QUALITY.get(4), currentPrice));
                fullWeight -= currentWeight;
                fullMoney -= currentPrice;
                continue;
            }
            break;
        }
        BARYGA.setProducts(productList);
        BARYGA.setMoney(fullMoney);
    }

    static void events(int key){
        Event event = new Event();
        switch (key) {
            case 1 -> event.ordinaryDay();
            case 2 -> event.raining(BARYGA, QUALITY);
            case 3 -> event.smoothRoad(BARYGA);
            case 4 -> event.wheelBroke(BARYGA);
            case 5 -> event.river(BARYGA);
            case 6 -> roadDistance = event.metLocal();
            case 7 -> event.highwaymen(BARYGA);
            case 8 -> event.roadsideInn(BARYGA, GOODS, QUALITY);
            case 9 -> event.goodsIsGone(BARYGA, QUALITY);
            default -> System.out.println("Такого события нет!");
        }
    }

}
