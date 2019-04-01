package FactoryPattern.FactoryPattern;

public class ChicargoPizzaFactory implements PizzaFactory {

    @Override
    public void Fcut() {
        System.out.println("Cut the Pizza into pieces ......");
    }

    @Override
    public void FwrapUp() {
        System.out.println("Wrapped by machine ......");
    }

    @Override
    public void Fdelivery() {
        System.out.println("Delivering ......");
    }
}
