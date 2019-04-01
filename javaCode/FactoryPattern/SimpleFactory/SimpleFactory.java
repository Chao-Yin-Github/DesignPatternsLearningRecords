package FactoryPattern.SimpleFactory;


public class SimpleFactory {

    public Pizza createPizza(String order) {
        Pizza pizza = null;

        //在此处修改,虽然封装了,但是还是违背开闭原则
        if(order.equals("chess")){
            pizza = new ChessPizza();
        }
        else if(order.equals("pepperoni")){
            pizza = new PepperoniPizza();
        }

        return pizza;
    }
}
