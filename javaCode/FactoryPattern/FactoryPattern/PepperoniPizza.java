package FactoryPattern.FactoryPattern;

public class PepperoniPizza extends Pizza {
    PizzaFactory pizzaFactory;

    public PepperoniPizza(PizzaFactory pizzaFactory) {
        this.pizzaFactory = pizzaFactory;
    }

    @Override
    public void Process() {

        pizzaFactory.Fcut();
        pizzaFactory.FwrapUp();
    }
}
