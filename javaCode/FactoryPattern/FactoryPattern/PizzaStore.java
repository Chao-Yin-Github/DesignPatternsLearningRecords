package FactoryPattern.FactoryPattern;

public abstract class PizzaStore {
    protected abstract Pizza createPizza(String order);

    public Pizza orderPizza(String order){
        Pizza pizza = createPizza(order);
        pizza.prepared();
        pizza.Process();
        pizza.over();
        return pizza;
    }
}
