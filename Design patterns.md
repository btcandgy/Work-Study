1.单列模式
单例模式的意思就是一个类只有一个实例,并提供一个访问它的全局访问点
可以分为4种
1.饿汉模式
2.懒汉模式
3.静态内部类单例模式
4.枚举单例

static
如果一个变量被static修饰，那么它就属于类的静态变量，也称为类变量。类变量在类加载时被初始化，它存储在方法区中，是所有实例共享的，因此多个线程调用这个变量时，调用的都是同一个变量。

//饿汉模式
public class Singleton {
private static Singleton instance = new Singleton();
private Singleton() {}

public static Singleton getInstance() {
    return instance;
}
}
这个点的不同
首先一个重点就是类的初始化得在一开始就弄,并且是由static修饰

使用static关键字创建类的静态变量时，这个变量会在类加载时被初始化。在java中,类加载只会发生1次。因此，如果使用static关键字创建单例对象，那么在类加载时就会创建单例对象。

但是饿汉模式是有一个缺点,就是这个变量会在类加载时被初始化,如果没用过这个实例的话,就容易造成内存的浪费。


