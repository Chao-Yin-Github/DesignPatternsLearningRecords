package FactoryPattern.factoryMethod;

public abstract class Pizza {
    public void cut(){
        System.out.println("I am cutting......");
    }
    public void wrapUp(){
        System.out.println("I am wrapping......");
    }
    public  void delivery(){
        System.out.println("I am delivering......");
    }
}
