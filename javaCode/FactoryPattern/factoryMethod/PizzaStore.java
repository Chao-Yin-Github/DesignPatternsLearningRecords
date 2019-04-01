package FactoryPattern.factoryMethod;

public abstract class PizzaStore {
    Pizza pizza;

    public Pizza orderPizza(String order){
        createPizza(order);

        pizza.cut();
        pizza.wrapUp();
        pizza.delivery();

        return pizza;
    }

    protected abstract void createPizza(String order);
}
