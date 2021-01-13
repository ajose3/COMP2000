package Model;

public interface IPay {
    default void payment(){
    }

    default void getReceipt() throws InterruptedException {

    }

    default void run(){
        System.out.println(Thread.currentThread().getName() + "is running");
    }
}
