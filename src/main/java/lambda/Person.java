package lambda;

import java.util.Date;

/**
 * @Author: hataki
 * Time: 10:15
 * description:
 */
public class Person {
    private String name;

    public Person() {
    }

    /**
     *
     * @param name
     */
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param name
     * @return
     */
    public static Person build(String name){
        Person p = new Person();
        p.setName(name);
        return p;
    }

    @Deprecated
    public static void show(){
        System.out.println("show");
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Person p = new Person();
        show();
        Date date = new Date();
        System.out.println(date.getMinutes());
    }

}
