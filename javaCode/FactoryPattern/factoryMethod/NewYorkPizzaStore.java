package FactoryPattern.factoryMethod;

public class NewYorkPizzaStore extends PizzaStore{

    @Override
    public void createPizza(String order) {
        if(order.equals("chess")){
            pizza = new NewYorkStyleChessPizza();
        }
        else if(order.equals("pepperoni")){
            pizza = new NewYorkStylePepperoniPizza();
        }
        else {
            System.out.println(order+"not found");
        }
    }
}
