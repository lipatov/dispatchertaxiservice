
package types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "targetType", propOrder = {
    "value"
})
public class TargetType {
    @XmlValue
    private String value;

    @XmlAttribute(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

}
