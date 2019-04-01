package FactoryPattern.OldStyle;

import FactoryPattern.SimpleFactory.ChessPizza;
import FactoryPattern.SimpleFactory.PepperoniPizza;
import FactoryPattern.SimpleFactory.Pizza;

import java.util.Scanner;

public class OldStyle {
    public static void main(String[] args){
        Pizza pizza;
        Scanner in = new Scanner(System.in);
        String order = in.nextLine();
        pizza = orderPizza(order);

        //只是创建的对象种类不同,其方法都一样.
        pizza.cut();
        pizza.wrapUp();
        pizza.delivery();

    }

    private static Pizza orderPizza(String order) {
        Pizza pizza = null;

        //当需要添加或者删除某一种Pizza时,需要添加或删除if-else 条件判断.
        /*即没有对修改封闭*/
        if(order.equals("chess")){
            pizza = new ChessPizza();
        }
        else if(order.equals("pepperoni")){
            pizza = new PepperoniPizza();
        }

        return pizza;
    }
}
