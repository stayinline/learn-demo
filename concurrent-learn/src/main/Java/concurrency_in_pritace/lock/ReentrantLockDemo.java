package concurrency_in_pritace.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hemaoling
 */
public class ReentrantLockDemo {

    private ReentrantLock reentrantLock = new ReentrantLock();

    public boolean tryLockWithTimeout(String msg, long timeout, TimeUnit timeUnit) throws InterruptedException {

        // 在参数指定的时间内尝试获取锁，成功则执行业务，
        // 不成功则返回一个失败，交由调用方处理
        if (!reentrantLock.tryLock(timeout, timeUnit)) {
            return false;
        }
        try {
            return dealMsg(msg);
        } finally {
            reentrantLock.unlock();
        }
    }

    private boolean dealMsg(String msg) {
        // 处理业务
        return true;
    }
}
