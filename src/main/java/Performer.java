import types.DispatchedType;
import types.MessageType;

import javax.xml.bind.JAXBException;

import static types.MessageType.writeMessage;

public class Performer extends Thread {
    private MessageType msg;
    private int event;

    public Performer(MessageType msg, int event) {
        this.msg = msg;
        this.event = event;
    }

    @Override
    public void run() {
        try {
            msg.setDispatched(new DispatchedType());
            msg.getDispatched().setId(event);
            writeMessage(msg);
            sleep(3000);
        } catch (InterruptedException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
