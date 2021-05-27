### 方法的引用
* **静态方法引用：** 如果函数式接口的实现恰好可以通过调用一个静态方法来实现，那么就可以使用静态方法引用。
* **实例方法引用：** 如果函数式接口的实现恰好可以通过调用一个实例的实例方法来实现，那么就可以使用实例方法引用；
* **对象方法引用：** 抽象方法的第一个参数类型刚好是实例方法的类型，抽象方法剩余的参数恰好可以当作实例方法的参数。如果函数式接口的实现
能由上面说的实例方法调用来实现的话，那么就可以使用对象引用的方法；
* **构造方法引用：** 如果函数式接口的实现恰好可以通过调用一个类的构造方法来实现，那么就可以使用构造方法引用。



### Stream特性
- 不是数据结构，没有内部存储
- 不支持索引访问
- 延迟计算（执行之后，只有不返回stream的时候才会执行，否则不会执行）
- 支持并行
- 很容易生成数组或集合（List,Set）
- 支持过滤，查找，转换，汇总，聚合