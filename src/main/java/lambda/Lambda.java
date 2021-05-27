package lambda;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @Author: hataki
 * Time: 10:31
 * description:
 *
 * lambda表达式其实质就是匿名类的一种简写方式，但是要求该类必须有且仅有一个抽象方法
 * 且可以由 @FunctionalInterface 注解来强制声明
 *
 * lambda practice
 *
 * jdk提供的函数式接口：
 *
 * Supplier 代表一个输出
 *
 * Consumer 代表一个输入  (x) -> {}
 * BiConsumer 代表两个输入 (x,y ) -> {}
 *
 * Function 代表一个输入，一个输出 *输入输出类型不同* (x) -> {return x }
 * UnaryOperator 代表一个输入，一个输出 *输入输出类型相同*
 *
 * BiFunction 代表两个输入，一个输出 *输入输出类型不同*
 * BinaryOperator 代表两个输入，一个输出 *输入输出类型相同*
 */

public class Lambda {

    public static void main(String[] args) {

        /**
         * 为什么接口方法可以直接运行？？
         *
         */
        Runnable run1 = ()-> System.out.println("running Runnable interface !");


        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("running Runnable interface !");
            }
        } ;


        Function function = (o) -> {return o;} ;
        Function function2 = new Function() {
            @Override
            public Object apply(Object o) {

                return o;
            }
        } ;
        run1.run();

        Thread t = new Thread( );

        /**
         * 创建对象的几种方式：
         *  new
         *  反射
         *  序列化
         *  clone，调用clone方法需要实现Cloneable接口
         *  Supplier
         *
         * 为什么会出现supplier这种方式？创建对象的时候直接new一个实例不是更省力。？
         *
         * 自己的理解：
         * 基于接口式/流式编程的需求；类似于单例模式中延迟创建，对象的实例化取决与方法体实现；
         * 这样一来，能够实现在接口中声明的任意方法里面，定义所指定的泛型对象；
         * supplier是一个能够创建对象实例的对象，这样就可以控制在需要的创建对象的时候创建对象
         *  StreamSupport --- Stream stream()
         *
         */
        Supplier<Object> o1 = Object::new;
        Object o = o1.get();


        /**
         * 定义一个消费者函数接口
         * accept一个生产对象，经过流程
         * andThen返回固定流程的处理结果
         * 内部是一个递归实现，当传入对象为空时，结束递归
         */
        Consumer<Object> c1 = (x) -> {
            System.out.println(x);
        };
        c1.accept("this is a consumer function.");
        c1.accept("2");
        c1.accept(3);
        c1.accept(60L);
        System.err.println("consumer accept x is : " + c1.andThen(c1).toString());

        /**
         * 函数接口 Function(t,r) -> t是输入类型，r是输出类型
         * 等同于  f(x) = y ;
         */
        Function<String,Integer> f1 = (str)-> str.length();
        System.out.println("f1.apply(\"1234567\") = " + f1.apply("1234567"));
        System.err.println("-----------");
        /**
         * 函数接口 BiFunction(t1,t2,r) -> t1,t2是输入，r是输出
         * lambda表达式的入参不需要写输出参数，入参为 (a,b) 而不是(a,b,c)
         */
        BiFunction<String,String,Integer> bi = (a,b) -> a.length() + b.length() ;
        System.out.println("bi.apply(\"1\",\"22\") = " + bi.apply("1", "22"));

        /**
         * 方法的引用
         */
        List<String> list = Arrays.asList("h","e","r","o");
        list.forEach(System.out::println);

    }
}
