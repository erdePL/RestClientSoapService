package RestClientSoapService.soap.webService;

import RestClientSoapService.soap.model.Message;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface MessageService {
    @WebMethod
    List<Message> getAllMessages();
    @WebMethod
    Message getMessage(long id);
    @WebMethod
    Message addMessage(Message message);
    @WebMethod
    Message updateMessage(Message message);
    @WebMethod
    Message removeMessage(long id);
    @WebMethod
    String removeAllMessages();
}
