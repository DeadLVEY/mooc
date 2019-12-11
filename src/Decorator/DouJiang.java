package Decorator;

/**
 * 豆浆类
 */
public class DouJiang implements Drink{
    @Override
    public double getMoney() {
        return 2;
    }

    @Override
    public String getDesc() {
        return "纯豆浆";
    }
}
