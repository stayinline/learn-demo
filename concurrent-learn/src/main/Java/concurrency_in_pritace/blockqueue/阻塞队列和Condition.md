 ### 1、阻塞队列概念
 
 - 队列为空的时候，get线程会被阻塞，直到队列有数据；
 - 队列装满的时候，put线程会被阻塞，直到队列有空位；
 
 
 ### 2、Condition
 
 - Condition 接口的实现类是同步器 AQS 的内部类 ConditionObject
  - 每个 Condition 对象都包含着一个 FIFO 队列，拥有首节点(firstWaiter)和尾节点(lastWaiter)
  - 队列中的每个节点都包含了一个在 Condition 对象上等待的线程引用
  - await() 方法相当于同步队列的首节点(已获取锁)移动到 Condition 的等待队列中
  - signal() 方法将会唤醒在等待队列中的节点，移到同步队列中
  
  https://blog.csdn.net/qq_38293564/article/details/80554516
  
  