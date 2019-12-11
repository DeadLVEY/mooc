package Decorator;

/**
 * Created by 瑾 on 2019/10/13.
 */
public class Product1 extends Decorator {
    public Product1(Drink drink) {
        super(drink);
    }

    @Override
    public double getMoney() {
        return super.getMoney()+1.5;
    }

    @Override
    public String getDesc() {
        return super.getDesc()+"+p1特别料理";
    }



    public static void main(String[] args) {
        System.out.println(new Product1(new DouJiang()).getMoney());
        System.out.println(new Product1(new DouJiang()).getDesc());
    }
}
