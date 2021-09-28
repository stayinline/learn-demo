package concurrency_in_pritace.part14.demo1;


public class DemoApp {

    private final static Long SLEEP_TIME = 1000L;

    /**
     * 注意：
     * 这使得调用者必须自己处理异常情况，然后自旋等待，再重试
     *
     * @param buffer
     * @throws InterruptedException
     */
    public void execute(GrumpyBoundedBuffer buffer) throws InterruptedException {
        while (true) {
            try {
                Object item = buffer.take();
                System.out.println("获取到了一个元素item：" + item);
                break;
            } catch (BufferEmptyException e) {
                Thread.sleep(SLEEP_TIME);
            }
        }
    }

}
