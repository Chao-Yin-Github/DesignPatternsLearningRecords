package FactoryPattern.FactoryPattern;

public class ChessPizza extends Pizza{
    PizzaFactory pizzaFactory;

    public ChessPizza(PizzaFactory pizzaFactory) {
        this.pizzaFactory = pizzaFactory;
    }

    @Override
    public void Process() {

        //不管是哪一个工厂,对于ChessPizza的处理过程都要有下面三个步骤
        // 而对于PepperoniPizza,它都没有delivery这个步骤.
        //即不同种类的Pizza在Process自定义专属于自己的特色.并且保证不同地区之间,都有类似的特色,只是有不同的特色实现方式

        pizzaFactory.Fcut();
        pizzaFactory.FwrapUp();
        pizzaFactory.Fdelivery();
    }
}
