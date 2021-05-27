package Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/8/5
 * Time: 15:10
 * description:
 * <Observer>
 *
 *     观察者模式组成： 观察者，事件源，响应事件的执行方法
 *
 *     观察者 --通过-- 事件源 --触发-- 响应事件
 *
 *  * 有很多时候，观察者需要根据事件的具体情况来进行处理
 *  * 大多数时候，我们处理事件的时候，需要事件源对象
 *  * 事件也可以形成继承体系
 *     a lot of times ,
 */

class Child {
    private boolean cry = false ;
    private List<Observer> observerList = new ArrayList<>();

    {
        /**
         * 在observer中可以实现 callback/hook function
         */
        observerList.add(new Dad());
        observerList.add(new Dog());
        observerList.add( (e)-> {
            System.out.println("hook hook .....!");
        } );

    }

    public boolean isCry(){
        return cry ;
    }

    public void wakeUp(){
        cry = true ;
        System.out.println("baby is waking up now ....");
        System.out.println("start event now ....");
        wakeUpEvent event = new wakeUpEvent(System.currentTimeMillis() , "ddd",this);
        for(Observer o : observerList){
            o.actionOnWakeUp(event);
        }
    }

}

abstract  class Event<T> {
    abstract  T getSource();
}

/**
 * 响应事件
 */
class wakeUpEvent extends Event<Child>{

    long timestamp ;
    String loc ;
    Child source ;

    public wakeUpEvent(long timestamp , String loc , Child source){
        this.timestamp = timestamp ;
        this.loc = loc ;
        this.source = source ;
    }

    @Override
    Child getSource() {
        return source;
    }
}

/**
 * 观察者接口，用来实现观察者事件的实现
 */
interface Observer {
    void actionOnWakeUp(wakeUpEvent event);
}

class Dad implements Observer{
    public void feed(){
        System.out.println("feeding ......");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        feed();
    }
}

class Dog implements Observer{

    public void shout(){
        System.out.println("woof woof woof  ...");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        shout();
    }
}

public class MyMain {

    public static void main(String[] args) {
        Child c = new Child() ;
        c.wakeUp();
    }

}
