import types.MessageType;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static types.MessageType.createMessage;

public class TaxiService {
    private static ArrayBlockingQueue<MessageType> clients = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(TaxiService::generateUserRequests);
        Thread t2 = new Thread(TaxiService::distributionRequests);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    /**
     * Генерирация сообщений от "клиентов"
     */
    private static void generateUserRequests() {
        int i = 1;
        while (i < 1000) {
            try {
                clients.put(createMessage(i++));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Передача сообщений "исполнителям"
     */
    private static void distributionRequests() {
        ExecutorService performers = Executors.newFixedThreadPool(10);
        int i = 1;
        while (true) {
            i++;
            if (i > 10) {
                i = 1;
            }
            try {
                performers.execute(new Performer(clients.take(), i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}