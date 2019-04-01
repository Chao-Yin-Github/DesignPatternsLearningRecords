package FactoryPattern.FactoryPattern;

public class ChicargoPizzaStore extends PizzaStore {
    protected Pizza createPizza(String order) {
        Pizza pizza = null;
        PizzaFactory factory = new ChicargoPizzaFactory();
        if (order.equals("chess")) {
            pizza = new ChessPizza(factory);
        } else if (order.equals("pepperoni")) {
            pizza = new PepperoniPizza(factory);
        } else {
            System.out.println("pizza not fountd!");
        }
        return pizza;
    }
}
