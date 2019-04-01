package FactoryPattern.factoryMethod;

public class ChicargoPizzaStore extends PizzaStore{

    @Override
    public void createPizza(String order) {
        if (order.equals("chess")){
            pizza = new ChicargoStyleChessPizza();
        }
        else if(order.equals("pepperoni")){
            pizza = new ChicargoStylePepperoniPizza();
        }
        else {
            System.out.println("Pizza not fount");
        }
    }
}
