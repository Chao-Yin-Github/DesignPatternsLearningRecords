package FactoryPattern.FactoryPattern;

public class NewYorkPizzaFactory implements PizzaFactory {

    //NewYork工厂不知道哪个Pizza会用到下面具体那几个方法,它只能将他能够提供的加工方法都写出来
    // 具体调用哪些方法由具体的Pizza类决定.

    @Override
    public void Fcut() {
        System.out.println("Cutting into sectors......");
    }

    @Override
    public void FwrapUp() {
        System.out.println("WrapUp by hand ......");
    }

    @Override
    public void Fdelivery() {
        System.out.println("Emergency delivery ......");
    }
}
