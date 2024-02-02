package lambda.task2;

import lambda.task2.Worker.OnTaskDoneListener;
import lambda.task2.Worker.OnTaskErrorListener;

public class Main {
    public static void main(String[] args) {
        OnTaskDoneListener listener = System.out::println;
        OnTaskErrorListener errorListener = System.err::println;

        Worker worker = new Worker(listener, errorListener);
        worker.start();
    }
}