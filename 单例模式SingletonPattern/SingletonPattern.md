# 单例模式

1.  应用条件:实例化一个**唯一的**对象。

    例如：线程池、缓存、注册表等对象，如果制造出多个实例对象，可能产生许多问题。

2.  为什么不用全局变量？

    1.  全局变量缺点：
        1.  有的JVM在用到时才创建对象，那么就有可能这个全局变量根本没有被创建。
        2.  万一这个对象非常耗费资源，而程序在执行过程中又一直没有用到它，就很浪费资源。
        3.  用许多全局变量可能会造成命名空间的污染。

3.  实现方法：

    1.  在public 类中将构造器置为私有，但是这样就没有类能够实例化它……

    2.  #### 方法<一>：延迟实例化的方式创造单例对象。【懒汉模式】

        **在类加载时，不创建实例，因此类加载速度快，但运行时获取对象速度慢。**

        不仅将该构造器置为私有，并且设置一个静态的public方法 .getInstance() 来调用这个构造方法，并返回生成的类。

        此时不需要实例对象来调用.getInstance（）方法，可以直接用类名.getInstance（）方法调用。

    3.  ```java
        public class ChocolateBoiler{
            private boolean empty;
            private boolean boiled;
            private ChocolateBoiler UniqueInstance ;
        
            public static ChocolateBoiler getInstance(){
                if(UniqueInstance == null){
                    UniqueInstance = new ChocolateBoiler();
                }
                return UqniqueInstance;
            }
        
            private ChocolateBoiler(){
                empty = true;
                boiled = false;
            }
            
            public void fill(){
                if(isEmpty()){
                    empty = false;
                    boiled = false;
                }
            }
        
            public void drain(){
                if(!isEmpty()&&!isBoiled()){
                    empty = true;
                }
            }
            
            public void boil(){
                if(!isEmpty()%%!isBoiled()){
                    boiled = true;
                }
            }
            
            public boolean isEmpty(){
                return empty;
            }
            
            public boolean isBoiled(){
                return boiled;
            }
        }
        ```

        

-   单例模式：
    -   定义：确保一个类只有一个实例，并提供一个全局访问点。

4.  上个方法出现的问题：

    -   当在多线程里进行实例化时，会出现因为变量不同步而导致的实例化多个对象。

    -   解决方案：加synchronized同步

    ```java
    public static synchronized Singleton getInstance(){
        if(UniqueInstance == null){
            UniqueInstance = new Singleton();
        }
        return UniqueInstance;
    }
    ```

    >   缺点：synchronized负担严重，执行效率可能下降100倍。因此不能将getInstance（）使用在频繁运行的地方。

5.  #### 方法<二>：使用“急切”创建实例方法。【饿汉模式】

    >   **在类加载时就完成了初始化，所以类加载慢，但是获取对象速度快。**

    即直接在Singleton类里面在静态初始化类中创建单例。这样保证了线程安全。

    ```java
    public class Singleton{
        private static Singleton uniqueInstance = new Singleton();
        
        public static Singleton getInstance(){
            return uniqueInstance;
        }
    }
    ```

6.  #### 方法<三>：双重加锁：

    首先用volatile关键字修饰，检查实例是否已经创建，如果尚未创建，才进行”同步“，这样，同步只有**一次机会**。

    ```java
    public class Singleton{
        private volatile static Singleton uniqueInstance;
        private Singleton() {}
        public static Singleton getInstance(){
            //if是第一关判断是否初始化
            if(uniqueInstance == null){
                //如果没有初始化过，才在下面synchronized修饰的代码块中进行单例对象的创建。
                synchronized(Singleton.class){
                    uniqueInstance = new Singleton();
                }
    		}      
            return uniqueInstance;
        }
    ```

    不用volatile关键字修饰,**可能使某个线程获得一个未完全初始化的实例**。并且可能会有**指令重排&随机读写**问题。

    >   但是，这样一是**代码臃肿**，二是无法解决**反序列化会破坏单例**的问题。

7.  #### 方法<四>：枚举单例模式。

    ```java
    public enum Singleton{
        INSTANCE;
        public void whateverMethod(){
            
        }
        
        public static void maina(String [] args){
            Singleton.INSTANCE.whateverMethod();
        }
    }
    ```

    避免线程同步问题，防止反序列化重新创建对象。并且，代码简单😂

    >   **单元素的枚举类型已经成为实现Singleton的最佳方法。**

8.  **注意:**

    1.  单例模式由于是私有构造器,子类继承之后无法调用super()，继承失败。
    2.  其实单例模式用到的不多，不要在程序中大量应用单例模式