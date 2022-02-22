package concurrency_in_pritace.semaphore;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 简易版模拟JDK源码实现
 * 源码中还包含公平和非公平，共享和非共享模式的设计，更为复杂
 */
class MySemaphore3 {

    private Sync sync;

    public MySemaphore3(int permits) {
        sync = new Sync(permits);
    }

    public void acquire() {
        sync.tryAcquire(1);
    }

    public void release() {
        sync.tryRelease(1);
    }

    static final class Sync extends AbstractQueuedSynchronizer {

        private int permits;

        public Sync(int permits) {
            setState(permits);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            int exp = getState() - arg;
            if (compareAndSetState(getState(), exp)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int exp = getState() + arg;
            if (compareAndSetState(getState(), exp)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
    }
}
