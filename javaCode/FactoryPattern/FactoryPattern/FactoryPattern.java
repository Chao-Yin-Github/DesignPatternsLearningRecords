package FactoryPattern.FactoryPattern;

public class FactoryPattern {
    public static void main(String [] args){
        PizzaStore nyStore = new NewYorkPizzaSotre();
        PizzaStore cStore = new ChicargoPizzaStore();

        System.out.println("NewYorkChess:"+System.getProperty("line.separator")+"----------");
        Pizza pizza = nyStore.orderPizza("chess");
        System.out.println("ChicargoChess:"+System.getProperty("line.separator")+"----------");
        pizza = cStore .orderPizza("chess");
        System.out.println("NewYorkPepperoni:"+System.getProperty("line.separator")+"----------");
        pizza = nyStore.orderPizza("pepperoni");
        System.out.println("ChicargoPepperoni:"+System.getProperty("line.separator")+"----------");
        pizza = cStore.orderPizza("pepperoni");
    }
}
