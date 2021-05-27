# 网络nio

nio

对于jdk  new io 

对于os non-block

```
Bytefuffer  //可以在堆内分配 也可以在堆外分配
```

ulimit -a

ulimit -n

ulimit -SHn 50000

**bio （blocked io ）** 

产生阻塞原因: 每次过来的链接都会产生新的一个线程

nio设计到的底层linux知识：

nio并不能提升访问的效率，依然很慢，打开多个线程以及多个linux文件描述符



提升效率的关键是 epoll （多路复用器，底层实现）

## 多路复用

内核态/用户态

***全量遍历，需要用户态切换到内核态才能实现***

nio需要多次切换 用户态内核态 才可以

app  ----用户态

  ↓

kernel ---内核态

（旧）

一次系统调用，访问一个io，每条路消耗一个系统调用

（新）

一次系统调用，访问多路io，就是多路复用；获得的是io的状态，有程序自己对有状态的io进行rw；只要程序自己读写，那么，它的io模型就是同步的

**单关注io来讲：**不关注从io读写之后的事情

**同步：**application 自己进行 rw

**异步：**kernel 完成rw ， 没有io ，采用了buffer

**阻塞：**blocking

**非阻塞：**non-blocking

linux以及成熟的netty中，

同步阻塞：（bio模型）

同步非阻塞 

多路复用器可以是： linux中的select （posix标准），POLL ，epoll 都是多路复用器，注意，这是在同步模型下的非阻塞使用

通俗讲，

同步阻塞 ： 程序自己读取，调用了方法一直等待有效的返回结果

同步非阻塞： 程序自己读取，调用方法一瞬间，给出是否读取到结果（自己下一次要解决下一个读取的时间）

异步：尽量不要去讨论，因为在目前讨论的io模型下，linux，目前没有通用的内核的异步处理方案 ； 异步不存在阻塞，只有非阻塞！

其实，无论NIO，SELECT,POLL都是要遍历访问所有的io，询问状态

但是，

NIO--这个遍历需要多次在用户态和内核态之间切换

SELECT，POLL --这个遍历的过程触发了一次系统调用，用户态内核态的切换，过程中，把fds传递给内核，内核重新根据用户这次调用传递过来的fds，遍历修改状态

### select

synchronous I/O multiplexing 

各个系统都有select，遵循posix规范，

在linux当中受到 FD_SETSIZE （1024）的大小限制，所以目前基本不怎么使用

int select(fds , ...) 

### POLL

poll里面没有1024的限制，传入的其实是select中的fds数组，返回一个链表

select 的简单升级版

### 多路复用器： select poll的弊端，问题：

1.每次都需要重复，重新传递fds

2.每次，内核被调用之后，针对这次调用，触发一个遍历fds全量的复杂度

## c10k问题

client ten thousand problem 亦即单机一万并发问题