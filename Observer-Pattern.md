# 观察者模式

-   观察者模式概述
    -   定义：这个模式定义了一对多依赖，这样，**当一个对象被修改时，被观察者==自动==通知它依赖的观察者。**
    -   分类：属于行为模式
    -   实例：西游记里面悟空请求菩萨降服红孩儿，菩萨洒了一地水招来一个老乌龟，这个乌龟就是观察者，他观察菩萨洒水这个动作。
![This is a picture!](http://www.runoob.com/wp-content/uploads/2014/08/observer_pattern_uml_diagram.jpg)
------

## 需求：

现在有一个气象站，检测温度，湿度，气压三个值。
当气象站检测到这三个值有改变时，自动通知三个公告板，board1，board2，board3。
其中board1输出新得到的温度，湿度，气压这三个值，board2输出三个值中的最大值和最小值，board3输出三个值的平均值。

------

## 方法一：

直接声明一个类，在此类里有一系列getter方法，来获取到温度，湿度，气压三个值，再实现change方法，将每次气象站改变的值传给board1，2，3。

代码：

```java
public class WeatherData{
     //实例变量声明
     public void measurementChanged(){
           float temperature = getTemperature();
           float humidity = getHumidity();
           float pressure = getPressure();
           
           board3.update(temp,humidity,pressure);//公告板1的更新
           board2.update(temp,humidity,pressure);//公告板2的更新
           board3.update (temp,humidity,pressure);//公告板3的更新
     }
}
```

这样似乎可行，但是这样有几个缺点：

1.  这样是针对具体实现编程，而非ie针对接口编程。
2.  对于每个新的公告板，我们都得修改代码。
3.  公告板没有一个公共的接口。
4.  没有封装完整。

------

## 方法二：

既然本文将观察者模式，那就有观察者模式的办法：

但是，

观察者模式是什么呢？

**观察者模式 = 订阅者+出版社**

当订阅者想知道目前最新的消息时，就成为出版社的订阅者，随时可以接收到出版社出版的报纸。
当订阅者不再想接收到报纸时，就联系出版社可以直接取消订阅，此后，订阅者就不能够再收到报纸了。

换个名字，即出版社是subject，订阅者是观察者【Object】。

虽然叫观察者，但实际上观察者并不能通过自己的眼睛来看到subject的状态改变，而必须由subject每次更新时，主动发送给观察者Object

![看张图：](https://upload-images.jianshu.io/upload_images/14812713-32dd695c9c727010.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240#pic_center)

实现观察者模式不只有一种方式：可以用继承类或者实现接口。

### 在实现接口的方式里：

subject是抽象接口，observer也是抽象接口（因为有不同类型的观察jjj者）。

realsubject才是正真的具体主题，object也才是正真的具体观察者。

![再来一张图](https://upload-images.jianshu.io/upload_images/14812713-e43fddcbabb0dcd4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**观察者实际上实现了对象之间的松耦合。**

因为主题唯一依赖的是一系列observer的对象列表，只知道观察者实现了observer的update接口，做了哪些事。

主题是数据的持有者，而observer只是主题的依赖者。这样没有多个对象控制同一个数据，得到更干净的OO设计。

这样，当有新的观察者出现时，主题不需要修改现有的代码，因为主题的任务只是将信心发送给观察者，即使二者传递消息但二者并非耦合。


_设计原则_

**努力做到交互对象之间的松耦合。**


这样交互对象之间的依赖降到了最低。



代码

抽象主题Subject:

```java
public interface CollectData {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyAllObserver();
}
```

抽象观察者Observer：

```java
public interface Observer {
    public void update(double a,double b, double c);
}
```

具体观察者者公告板1：

```java
public class board1 implements Observer{
    private double temperature,humidity,pressure;
    
    @Override
    public void update(double temperature,double humidity,double pressure){
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        read();
    }
    
    //公告板1的任务是将更新的数据输出。
    public void read(){
        System.out.println("message updated!"+temperature+","+humidity+","+pressure);
    }
}
```

具体观察者公告板2：

```java
public class board2 implements Observer {
    private double temperature,humidity,pressure;
    
    @Override
    public void update(double temperature,double humidity,double pressure){
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        read();
    }
    
    //公告板2的任务是找到三个值里的最大值和最小值。
    public void read(){
        double max=temperature>pressure?(temperature>humidity?temperature:humidity):pressure>humidity?pressure:humidity;
        double min=temperature<pressure?(temperature<humidity?temperature:humidity):pressure<humidity?pressure:humidity;
        System.out.println("board2 read some different:\nmax is :"+max+"\nmin is :"+min);
    }
}
```

具体观察者公告板3：

```java
public class board3 implements Observer{
    double temperature,humidity,pressure;

    @Override
    public void update(double temperature,double humidity,double pressure){
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        read();
    }
    
    //公告板3的任务是输出湿度。
    public void read(){
        System.out.println("board3 see something strange:\n"+humidity);
    }
}
```

具体的主题观测站：

```java
import java.util.*;

public class WeatherStation implements CollectData {
    private double temperature;
    private double humidity;
    private double pressure;

    ArrayList<Observer>list = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void notifyAllObserver() {
        for(Observer observer:list){
            /*主题无需知道到底是那个公告板，它只负责通知到观察者即可*/
            observer.update(temperature,humidity,pressure);
        }
    }

    public void setInformation(double temperature,double humidity,double pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyAllObserver();
    }
}
```

主函数负责更新数据：

```java
public class ObserverPattern {
    public static void main(String [] args){
        WeatherStation weatherStation = new WeatherStation();
        board1 observer1 = new board1();
        board2 observer2 = new board2();
        board3 observer3 = new board3();

        //分别注册各个观察者
        weatherStation.registerObserver(observer1);
        weatherStation.registerObserver(observer2);
        weatherStation.registerObserver(observer3);

        //一次更新
        weatherStation.setInformation(89,99,100);
        //而次更新
        weatherStation.setInformation(10,6,0);
        //三次更新
        weatherStation.setInformation(35,36,37);
    }
}
```

运行结果：

>message updated!89.0,99.0,100.0

>board2 read some different:

>max is :100.0

>min is :89.0

>board3 see something strange:99.0
---

>message updated!10.0,6.0,0.0

>board2 read some different:

>max is :10.0

>min is :0.0

>board3 see something strange:6.0
---

>message updated!35.0,36.0,37.0

>board2 read some different:

>max is :37.0

>min is :35.0
>
>board3 see something strange:36.0
