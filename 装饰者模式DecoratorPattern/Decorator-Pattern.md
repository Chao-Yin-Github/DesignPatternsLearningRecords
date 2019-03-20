# 装饰者模式

## 引入问题：

现在要模拟星巴克咖啡的种类,并且算出不同种咖啡的价格。

![饮料父类](https://github.com/YYkwSir/DesignPatternsLearningRecords/blob/master/%E8%A3%85%E9%A5%B0%E8%80%85%E6%A8%A1%E5%BC%8FDecoratorPattern/Decorator1.jpg?raw=true"饮料父类")

****

在购买咖啡时，可以加入各种调料：豆浆（soy）、摩卡（Mocha）等，而且加入调料之后会改变咖啡的价格。



### 方法一：

一种配置的咖啡就是一个类，然后……



![](https://github.com/YYkwSir/DesignPatternsLearningRecords/blob/master/%E8%A3%85%E9%A5%B0%E8%80%85%E6%A8%A1%E5%BC%8FDecoratorPattern/Decorator2.jpg?raw=true)



---

### 方法二：

利用布尔变量表示是否拥有相应的调料。

![](https://github.com/YYkwSir/DesignPatternsLearningRecords/blob/master/%E8%A3%85%E9%A5%B0%E8%80%85%E6%A8%A1%E5%BC%8FDecoratorPattern/Decorator3.jpg?raw=true.png)

但是，这样也有一些问题。

例如：

1.  调料的价格改变将要改变现有代码.
2.  一旦出现新的调料，对所有的饮料都要再次加入判断
3.  特别的，例如要双倍摩卡咖啡，此时仅有一个判断是否有此种调料，并不能判断有几个，这样也不适用。



所以，尽管继承方便快速，威力强大，但是它并不总能实现**最有弹性**和**最好维护**的设计。



---

###  **方法三：装饰者模式**



通过**`策略模式`**和**`观察者模式`**的学习，我们已经了解到利用**组合**或**委托**可以在运行时具有继承行为的效果。

现在，我们又有了一条新的设计原则：



**`设计原则:`**

```javascript
类应当对扩展开放，对修改关闭。
```



如何对扩展开放，对修改关闭？

例如在之前的观察者模式中，通过加入新的观察者，我们可以在任何时候扩展新的主题，而不需要向主题中添加新的代码。



在这个问题中，我们以饮料为主体，运行时以调料来**`装饰`**饮料，例如：

当需要一杯摩卡和奶泡咖啡，那么，

1.  先拿一个深焙咖啡（DarkRoast）对象
2.  以摩卡（Mocha）对象装饰它
3.  以奶泡（Whip）对象装饰它
4.  调用cost（）方法，并依赖委托（delegate）将调料的价钱加上去。

![](https://github.com/YYkwSir/DesignPatternsLearningRecords/blob/master/%E8%A3%85%E9%A5%B0%E8%80%85%E6%A8%A1%E5%BC%8FDecoratorPattern/Decorator4.jpg?raw=true)

![](https://github.com/YYkwSir/DesignPatternsLearningRecords/blob/master/%E8%A3%85%E9%A5%B0%E8%80%85%E6%A8%A1%E5%BC%8FDecoratorPattern/Decorator5.jpg?raw=true)



我们可以知道什么？

-   **装饰者和被装饰者具有相同的父类型**

-   **既然装饰者和被装饰者有相同的父类型，所以在任何需要原始对象（被包装的）场合，可以用装饰过的对象装饰它。**

-   **装饰者可以在所委托的被装饰者的行为之前与/或行为之后，加上自己的行为，以达到特定的目的。**

-   **对象可以在任何时候被装饰，所以可以在运行时动态的用装饰者来装饰对象。**

    

-   定义：动态的将责任附加到对象上，若要扩展功能，装饰者提供了比继承更有弹性的替代方案。



![](https://github.com/YYkwSir/DesignPatternsLearningRecords/blob/master/%E8%A3%85%E9%A5%B0%E8%80%85%E6%A8%A1%E5%BC%8FDecoratorPattern/Decorator6.jpg?raw=true)



![](https://github.com/YYkwSir/DesignPatternsLearningRecords/blob/master/%E8%A3%85%E9%A5%B0%E8%80%85%E6%A8%A1%E5%BC%8FDecoratorPattern/Decorator7.jpg?raw=true)

代码：

饮料抽象父类（component）：

```java
public abstract class Beverage {
    String description = "Unkonwn Beverage";
    public String getDescription(){
        return description;
    }
    public abstract double cost();
}
```



具体类（ConcreteConponent）1：Espresso

```java
public class Espresso extends Beverage{
    public Espresso(){
        description = "Espresso";
    }
    public double cost(){
        return 1.99;
    }
}
```



具体类2：HouseBlend：

```java
public class HouseBlend extends Beverage {
	public HouseBlend()l{
        description = "HouseBlend";
    }
    public double cost(){
        return 0.89;
    }
}
```



装饰者抽象类（CondimentDecorator）：

```java
public abstract class CondimentDecorator extends Beverage{
    public abstract String getDescription ();
}
```



具体装饰者1 Mocha：

```java
public class Mocha extends CondimentDecorator{
    Beverage beverage ;
    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription(){
        return beverage.getDescription();
    }

    public double cost(){
        return 0.20+beverage.cost();
    }

}
```



具体装饰者2 Whip：

```java
public class Whip extends CondimentDecorator{
    Beverage beverage;
    public Whip(Beverage beverage){
        this.beverage = beverage;
    }
    public double cost() {
        return 0.10+beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription();
    }
}
```



模拟咖啡价格：

```java
public class DecoratorPattern {
    public static void main(String [] args){
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()+"$"+beverage.cost());

        Beverage beverage1 = new HouseBlend();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Mocha(beverage1);
        beverage1 = new Whip(beverage1);
        System.out.println(beverage1.getDescription()+"$"+beverage1.cost());

        beverage = new Mocha (beverage);
        beverage = new Mocha(beverage);
        System.out.println(beverage.getDescription()+"$"+beverage.cost());
    }
}
```



>   结果：
>
>   Espresso$1.99
>
>   HouseBlend$1.3900000000000001
>
>   Espresso$2.39



对照一下价格表看看算对了没有？



![](https://github.com/YYkwSir/DesignPatternsLearningRecords/blob/master/%E8%A3%85%E9%A5%B0%E8%80%85%E6%A8%A1%E5%BC%8FDecoratorPattern/Decorator8.jpg?raw=true)



---

**`装饰者的缺陷：`**

1.  装饰者可以为设计注入弹性，但是，它也有黑暗面：

    有时候它会在设计中加入大量小类，这偶尔会导致别人不容易了解它的设计方式。

    例如，java的I/O库，第一次接触的人不易理解它，只有他们认识到这些各种各样的小类都是用来包装InputStream的，才能够真正理解它。

2.  它还有类型问题。你可以透明的插入装饰者，客户甚至不知道他是和装饰者打交道。但是，有些代码会依赖特定的类型，如果忽然导入装饰者，但又没有周详的考虑全局，就可能出状况。

3.  还有采用装饰者实例化组件时，将增加代码的复杂程度。一旦使用装饰者模式，不只需要实例化组件，还要把此组件包装进装饰者中，天知道有几个？

---

复习：

1.  装饰者模式意味着一群装饰者类，这些类用来包装具体组件。
2.  装饰者一般对组件的客户端时透明的，除非客户端依赖组件的具体类型。
3.  装饰者会导致设计中出现很多小对象，如果过度使用会让程序变得很复杂。
