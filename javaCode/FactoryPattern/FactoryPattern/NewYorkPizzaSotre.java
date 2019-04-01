package FactoryPattern.FactoryPattern;

public class NewYorkPizzaSotre extends PizzaStore{
    protected Pizza createPizza(String order){
        Pizza pizza = null;
        PizzaFactory factory = new NewYorkPizzaFactory();
        if(order.equals("chess")){
            pizza = new ChessPizza(factory);
        }
        else if(order.equals("pepperoni")){
            pizza = new PepperoniPizza(factory);
        }
        else {
            System.out.println("pizza not fountd!");
        }
        return pizza;
    }
}
