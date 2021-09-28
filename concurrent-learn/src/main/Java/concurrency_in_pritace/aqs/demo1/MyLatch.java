package concurrency_in_pritace.aqs.demo1;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 实现一个简单地二元闭锁
 * 0：关闭
 * 1：打开
 * 理解为一个互斥量
 *
 * @author hemaoling
 */
public class MyLatch {

    private final Sync sync = new Sync();

    public final int getSyncState() {
        return sync.getSyncState();
    }

    public void signal() {
        sync.tryReleaseShared(0);
    }

    public void await() {
        sync.tryAcquireShared(0);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int ignore) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }

        protected final int getSyncState() {
            return getState();
        }
    }
}
