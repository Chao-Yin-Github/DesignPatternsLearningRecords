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

直接声明一个类，在此类里有一系列getter方法，来获取到温度，湿度，气压三个值，再实现change方法，将每次气象站改变的值传给board1,board2,board3。

代码：

```java
public class WeatherData{
     //实例变量声明
     public void change(){
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

换个名字，即出版社是主题【subject】，订阅者是观察者【Observer】。

![看张图](https://upload-images.jianshu.io/upload_images/14812713-32dd695c9c727010.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240#pic_center"看张图：")

实现观察者模式不只有一种方式：可以用继承类或者实现接口。

### 1.实现接口的方式：

虽然叫观察者，但实际上**观察者observer**并不能通过自己的眼睛来看到**主题subject**的状态改变，而必须由主题subject每次更新时，主动发送给观察者Observer【主题推（"push"）给观察者】



subject是抽象接口，observer也是抽象接口（因为有不同类型的observer）。

ConcreteSubject才是正真的具体主题，ConcreteObserver也才是正真的具体观察者。

![再来一张图](https://upload-images.jianshu.io/upload_images/14812713-e43fddcbabb0dcd4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**观察者实际上实现了对象之间的松耦合。**

因为主题唯一依赖的是一系列observer的对象列表，只知道观察者实现了observer的update接口，做了哪些事。

主题是数据的持有者，而observer只是主题的依赖者。没有多个对象控制同一个数据，得到更干净的OO设计。

这样，当有新的观察者出现时，主题不需要修改现有的代码，因为主题的任务只是将信息发送给观察者，即使二者虽然有消息传递但二者并没有耦合严重。

****

<font color=red>**`设计原则`**</font>  
**`努力做到交互对象之间的松耦合。`**


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
        //每当数据改变时，就将新的数据通知给每一个Observer
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
>
>board2 read some different:
>
>max is :100.0
>
>min is :89.0
>
>board3 see something strange:99.0

---

>message updated!10.0,6.0,0.0
>
>board2 read some different:
>
>max is :10.0
>
>min is :0.0
>
>board3 see something strange:6.0

---

>message updated!35.0,36.0,37.0
>
>board2 read some different:
>
>max is :37.0
>
>min is :35.0
>
>board3 see something strange:36.0 

---

### 2. 继承类的方式

用推的方式`push`，会传给observer很多不需要的数据，而observer无法主动向subject索取`pull`数据。



例如board3中的功能，只是实现输出humidity，但update却必须要传入temperature，humidity，pressure三个值。



而且，当subject要扩展功能，新增更多状态时，用`pull`的方式就不用修改对观察者的调用，subject只需改变自己的getter方法即可让观察者获取更多的状态。 



**其实，Java有内置的观察者模式！**



在java.util包里，包含最基本的Observer接口和Observable类，你可以用 `推` 或 `拉` 的方式传送数据。

[![来自沸点Java组分享](https://github.com/YYkwSir/DesignPatternsLearningRecords/blob/master/14812713-a70d9bdadf3a8eb8.jpg?raw=true"来自沸点Java组分享")](https://www.jianshu.com/p/7286e8d3d8e5)

用此方法实现时，Observerable调用的addObserver方法来将Observer注册到主题Observerable。



其中的内置方法：

1.  setChanged（）
2.  notifyObservers（）
3.  update（Observable o，Object arg）



观察者如何送出通知？

1.  先调用setChanged（）方法，标记状态改变的事实。

    （这样可以控制天气更新的程度，使操作更有弹性。例如控制天气改变到一定程度时，再通知，防止进行太多次无效的更新）

2.  再调用两种通知方式的任一种：

    notifyObservers（）或者notifyObservers（Observer arg）

观察者如何接收通知？

​	修改update方法:

​	    update（Observable o,Observer arg）

​	    第一个变量：主题Observable让观察者知道是哪个主题通知给他的，

​	    第二个变量：Observer是传入notifyObservers的对象，没有则为空。

代码：



被观察者Observable：

````java
import java.util.*;
public class WeatherStation extends Observable {
    private double temperature;
    private double humidity;
    private double pressure;

    public void setInformation(double temperature,double humidity,double pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        setChanged();
        notifyObservers();
    }

    public double getTemperature(){
        return temperature;
    }

    public double getHumidity(){
        return humidity;
    }

    public double getPressure(){
        return pressure;
    }
}
````



board1：

```java
import java.util.*;

public class board1 implements Observer ,Display{
    private double temperature,humidity,pressure;
    Observable observable;

    public board1(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable observable,Object arg){
        if(observable instanceof WeatherStation){
            WeatherStation weatherStation = (WeatherStation)observable;
            this.temperature = weatherStation.getTemperature();
            this.humidity = weatherStation.getHumidity();
            this.pressure = weatherStation.getPressure();
            display();
        }
    }

    private void display() {
        System.out.println("ObserverPattern.board1:\n"+temperature+","+humidity+","+pressure);
    }
}
```



board2:

```java
import java.util.*;

public class board2 implements Observer,Display{
    private double temperature,humidity,pressure;
    Observable observable;

    public board2(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }
    public void update (Observable observable,Object object){
        if(observable instanceof WeatherStation){
            WeatherStation weatherStation = (WeatherStation) observable;
            this.humidity = weatherStation.getHumidity();
            this.temperature = weatherStation.getTemperature();
            this.pressure = weatherStation.getPressure();
            display();
        }
    }

    public void display(){
        double max = temperature>pressure?(temperature>humidity?temperature:humidity):pressure>humidity?pressure:humidity;
        double min = temperature<pressure?(temperature<humidity?temperature:humidity):pressure<humidity?pressure:humidity;
        System.out.println("board2 says some different:"+"\nmax is :"+max+"\nmin:"+min);
    }
}
```



board3:

```java
import java.util.*;

public class board3 implements Display,Observer{
    private double humidity;
    Observable observable;

    public board3(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }
    public void update (Observable o,Object object){
        if(o instanceof WeatherStation){
            WeatherStation weatherStation = (WeatherStation) o;
            this.humidity=weatherStation.getHumidity();
            display();
        }
    }

    private void display() {
        System.out.println("board3 see something strange: "+humidity);
    }
}
```


Display:

```java
import java.util.*;

public interface Display {
    public void update(Observable observable,Object object);
}
```

ObservaPattern:

```java
public class ObserverPattern {
    public static void main(String[] args){
        WeatherStation weatherStation = new WeatherStation();
        board1 observer1 = new board1(weatherStation);
        board2 observer2 = new board2(weatherStation);
        board3 observer3 = new board3(weatherStation);

        weatherStation.setInformation(89,99,100);
        weatherStation.setInformation(10,6,0);
        weatherStation.deleteObserver(observer3);
        weatherStation.setInformation(35,36,37);
    }
}

    }
}
```



<font color=#ff0000>结果：</font>

>board3 see something strange: 99.0  
>board2 says some different:  
>max is :100.0  
>min:89.0  
>ObserverPattern.board1:  
>89.0,99.0,100.0  
>board3 see something strange: 6.0  
>board2 says some different:  
>max is :10.0  
>min:0.0  
>ObserverPattern.board1:  
>10.0,6.0,0.0  
>board2 says some different:  
>max is :37.0  
>min:35.0  
>ObserverPattern.board1:  
>35.0,36.0,37.0



<font color = gold size = 5>顺序倒过来了?</font>



原因见**源码**：

        public void notifyObservers() {
            notifyObservers(null);
        }
        public void notifyObservers(Object arg) {
            Object[] arrLocal;
    
            synchronized (this) {
                
                if (!changed)
                    return;
                arrLocal = obs.toArray();
                clearChanged();
            }
    
    //更新是通过加入顺序的逆序而来
            for (int i = arrLocal.length-1; i>=0; i--)
                ((Observer)arrLocal[i]).update(this, arg);
---

注意：

无论用哪一种方法，结果都依赖于观察者被通知的顺序输出，无论是正序还是逆序，这样其实是错误的。

因为一旦观察者/可观察者的实现有所改变，通知次序就可能随之改变，进而可能产生错误的结果。

这样不是真正的松耦合。



特别是第二种继承，因为Java不支持多继承，会破坏类的一个重要继承位置。

再者，因为没有Observable接口，你无法建立自己的实现。

---

总之，无论用哪种方法实现，你都要学会并善用观察者模式。

