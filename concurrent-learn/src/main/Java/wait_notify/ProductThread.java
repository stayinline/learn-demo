package wait_notify;

public class ProductThread extends Thread {

    private Product product;

    public ProductThread(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            product.pushValue();
        }
    }
}