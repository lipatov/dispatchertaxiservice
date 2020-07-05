package types;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "messageType", propOrder = {
    "dispatched",
    "target",
    "sometags"
})
public class MessageType {
    @XmlElement(required = true)
    private DispatchedType dispatched;
    @XmlElement(required = true)
    private TargetType target;
    @XmlElement(required = true)
    private SometagsType sometags;


    public DispatchedType getDispatched() {
        return dispatched;
    }

    public void setDispatched(DispatchedType value) {
        this.dispatched = value;
    }

    public TargetType getTarget() {
        return target;
    }

    public void setTarget(TargetType value) {
        this.target = value;
    }

    public SometagsType getSometags() {
        return sometags;
    }

    public void setSometags(SometagsType value) {
        this.sometags = value;
    }

    public static void writeOrder(MessageType msg) throws JAXBException {
        File dir = new File("C://Performer_" + msg.getDispatched().getId());
        File newFile = new File(dir + "/Order_" + msg.getTarget().getId() + ".xml");
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        JAXBContext context = JAXBContext.newInstance(MessageType.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(msg, newFile);
    }

    public static MessageType createMessage(int i) {
        MessageType msg = new MessageType();
        msg.setSometags(new SometagsType());
        msg.setTarget(new TargetType());
        msg.getTarget().setId(i);
        List<String> list = Arrays.asList("", "", "");
        msg.getSometags().setData(list);
        return msg;
    }

}
