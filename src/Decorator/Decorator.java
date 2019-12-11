package Decorator;

/**
 * 装饰器
 */
public abstract class Decorator implements Drink{
    Drink drink;
    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public double getMoney() {
        return drink.getMoney();
    }

    @Override
    public String getDesc() {
        return drink.getDesc();
    }
}
