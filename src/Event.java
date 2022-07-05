import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Event {
    static Random rnd = new Random();

    public void ordinaryDay(){
        System.out.println("Обычный день");
    }
    public void raining(TraderAndCart baryga, List<Double> quality){
        System.out.println("\"Дождь\" - снижается скорость передвижения на 2 единицы.");

        baryga.downSpeed(2);
        if(baryga.getProducts().size() > 0) {
            if (rnd.nextInt(10) + 1 < 4) {
                int index = rnd.nextInt(baryga.getProducts().size());
                if (quality.indexOf(baryga.getProducts().get(index).getQuality()) - 1 < 0) {
                    baryga.getProducts().get(index).setQuality(quality.get(0));
                } else {
                    int num = quality.indexOf(baryga.getProducts().get(index).getQuality());
                    baryga.getProducts().get(index).setQuality(quality.get(num - 1));
                }
                System.out.printf("Качество товара [%s] испортилась!\n", baryga.getProducts().get(index).getGood().getName());
            }
        }else {
            System.out.println("Товаров нету!");
        }
    }

    public void smoothRoad(TraderAndCart baryga){
        baryga.upSpeed(2);
        System.out.println("\"Ровная дорога\" - повышается скорость +2 лиги в день.");
    }

    public void wheelBroke(TraderAndCart baryga){
        baryga.setStopped(true);
        System.out.println("\"Сломалось колесо\" - день в пустую, стоим на месте.");
    }

    public void river(TraderAndCart baryga){
        baryga.setStopped(true);
        System.out.println("\"Сломалось колесо\" - день в пустую, стоим на месте.");
    }
    public int metLocal(){
        System.out.println("\"Встретил местного\" - удалось срезать часть пути");
        return rnd.nextInt(4) + 3;
    }

    public void highwaymen(TraderAndCart baryga){
        System.out.println("Разбойники большой дороги!");
        if(baryga.getMoney() > 0){
            System.out.println("Откупился деньгами");
            baryga.setMoney(0);
        } else {
            baryga.getProducts().sort(Comparator.comparing(Product::getPrice).reversed());
            for (Product product : baryga.getProducts()){
                if(product.getQuality() > 1){
                    baryga.getProducts().remove(product);
                    System.out.println("Откупился товаром: " + product.getGood().getName() + " с ценой: " + product.getPrice());
                    break;
                }
            }
        }
    }
    public void goodsIsGone(TraderAndCart baryga, List<Double> quality){
        if(baryga.getProducts().size() > 0) {
            int index = rnd.nextInt(baryga.getProducts().size());
            if (quality.indexOf(baryga.getProducts().get(index).getQuality()) - 1 < 0) {
                baryga.getProducts().get(index).setQuality(quality.get(0));
            } else {
                int num = quality.indexOf(baryga.getProducts().get(index).getQuality());
                baryga.getProducts().get(index).setQuality(quality.get(num - 1));
            }
            System.out.printf("Качество товара [%s] испортилась!\n", baryga.getProducts().get(index).getGood().getName());
        }else {
            System.out.println("Товаров нету!");
        }
    }

    public void roadsideInn(TraderAndCart baryga, Goods[] GOODS, List<Double> quality){
        if(rnd.nextBoolean()) {
            if(baryga.getProducts().size() > 0) {
                System.out.println("Началась продажа!");
                for (int i = 0; i < baryga.getProducts().size(); i++) {
                    System.out.printf("Продал [%s] за %.2f\n", baryga.getProducts().get(i).getGood().getName(), baryga.getProducts().get(i).getPrice() * baryga.getProducts().get(i).getQuality());
                    baryga.setMoney(baryga.getMoney() + baryga.getProducts().get(i).getPrice() * baryga.getProducts().get(i).getQuality());
                }
                baryga.getProducts().clear();
            }else {
                System.out.println("Товаров нету!");
            }

        }else {
                double fullWeight = baryga.getLoadCapacity();
                double fullMoney = baryga.getMoney();
                while (true) {
                    double currentWeight = rnd.nextInt(11) + 10;
                    double currentPrice = rnd.nextInt(20) + 40;
                    if (fullWeight - currentWeight >= 0 & fullMoney - currentPrice >= 0) {
                        System.out.println("Началась покупка");
                        baryga.getProducts().add(new Product(currentWeight, GOODS[rnd.nextInt(6)], quality.get(4), currentPrice));
                        fullWeight -= currentWeight;
                        fullMoney -= currentPrice;
                        continue;
                    }
                    if (fullWeight - currentWeight >= 0) {
                        System.out.println("Нет места на повозке!");
                    } else {
                        System.out.println("Недостаточно средств!");
                    }
                    break;
                }
            }
        }
    }

