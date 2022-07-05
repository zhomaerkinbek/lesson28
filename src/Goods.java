public enum Goods {
    MEAT("Мясо"),
    DRIED_FRUITS("Сухофрукты"),
    GRAIN("Зерно"),
    FLOUR("Мука"),
    FABRIC("Ткань"),
    PAINT("Краска");

    private String name;

    public String getName() {
        return name;
    }

    Goods(String name) {
        this.name = name;
    }
}
