package FactoryPattern.factoryMethod;

public class FactoryMethod {
    public static void main(String [] args){
        NewYorkPizzaStore nyStore = new NewYorkPizzaStore();
        ChicargoPizzaStore cStore = new ChicargoPizzaStore();

        System.out.println("----------"+System.getProperty("line.separator")+"NewYorkStyle ChessPizza:");
        Pizza nyCPizza = nyStore.orderPizza("chess");

        System.out.println("----------"+System.getProperty("line.separator")+"ChicargoStyle ChessPizza:");
        Pizza cPCizza = cStore.orderPizza("chess");

        System.out.println("----------"+System.getProperty("line.separator")+"NewYorkStyle PepperoniPizza:");
        Pizza nyPPizza = nyStore.orderPizza("pepperoni");

        System.out.println("----------"+System.getProperty("line.separator")+"ChicargoStyle PepperoniPizza:");
        Pizza cpPPizza = cStore.orderPizza("pepperoni");
    }
}
