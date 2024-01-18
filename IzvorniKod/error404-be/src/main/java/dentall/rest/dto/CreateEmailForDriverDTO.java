package dentall.rest.dto;

import dentall.domain.Driver;
import dentall.domain.MedUser;
import dentall.domain.UserTreatmentInfo;

import java.util.Set;

public class CreateEmailForDriverDTO {

    private String recipient;
    private String msgBody;
    private String subject;
    private UserTreatmentInfo treatmentInfo;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public UserTreatmentInfo getTreatmentInfo() {
        return treatmentInfo;
    }

    public void setTreatmentInfo(UserTreatmentInfo treatmentInfo) {
        this.treatmentInfo = treatmentInfo;
    }
}
