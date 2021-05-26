package pojo;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;

/**
 * @Author: hataki
 * @Date: 2021/5/26
 * Time: 18:02
 * description:
 */
public class Person {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    private String name ;
    private String alias ;

}
