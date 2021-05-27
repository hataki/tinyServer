package lambda;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @Author: hataki
 * Time: 15:39
 * description:
 * 演示对象方法引用
 * lambda (*)-> 入参不推荐省略
 */
public class ObjectFunctionRef {

    public static void main(String[] args) {

        /**
         * 这种形式的调用很好理解
         */
        Consumer<Apple> ap = (Apple apple)-> new Apple().too();
        ap.accept(new Apple());

        /**
         * 传入的对象是Apple，但实际调用的是Banana的acid方法；
         * 怎么理解：前面入参对象其实是一个泛型对象，因此入参类型必须要一致；
         * 但是后面实际传入的new对象不一定是什么对象
         */
        Consumer<Apple> ap2 = (Apple apple)-> new Banana().acid();
        ap2.accept(new Apple());

        Consumer<Apple> ap3 = Apple::too;
        ap3.accept(new Apple());

        /**
         * 入参一定要传入对应的类型
         */
        BiConsumer<Banana,String> bi = (banana,str) -> new Banana().cid(str);
        BiConsumer<Banana,String> bi2 = Banana::cid;
        bi.accept(new Banana(),"1111");
        bi2.accept(new Banana(),"1111");



    }
}

class Apple{
    public int too(){
        System.out.println("too = " + 1);
        return 1 ;
    }
    public String foo(){
        System.out.println("foo = " + 2);
        return "2";
    }
}

class Banana{
    public int too1(){
        System.out.println("too1 = " + 1);
        return 1 ;
    }
    public String foo2(){
        System.out.println("foo2 = " );
        System.err.println();
        return "2";
    }
    public String cid(String str ){
        System.out.println("cid = 3 : "  + str );
        return "3";
    }
    public int acid(){
        System.out.println("acid = " + 4);
        return 4 ;
    }
}
