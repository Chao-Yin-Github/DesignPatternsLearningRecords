package FactoryPattern.SimpleFactory;

import java.util.*;

public class PizzaStore {
    public static void main(String[] args){
        Pizza pizza = null;
        SimpleFactory factory = new SimpleFactory();
        Scanner in = new Scanner(System.in);
        String order = in.nextLine();
        pizza = factory.createPizza(order);

        //只是创建的对象种类不同,其方法都一样.
        pizza.cut();
        pizza.wrapUp();
        pizza.delivery();

    }

}
