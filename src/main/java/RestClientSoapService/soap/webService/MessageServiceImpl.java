package RestClientSoapService.soap.webService;

import RestClientSoapService.rest.Client.RestClient;
import RestClientSoapService.soap.model.Message;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "RestClientSoapService.soap.webService.MessageService")
public class MessageServiceImpl implements MessageService {

    public MessageServiceImpl(){

    }
    @Override
    public List<Message> getAllMessages(){
        return RestClient.getAllMessages();
    }
    @Override
    public Message getMessage(long id){
        return RestClient.getMessage(id);
    }
    @Override
    public Message addMessage(Message message){
        if(message.getAuthor()!=null && message.getMessageContent()!=null) {
            return RestClient.addMessage(message.getMessageContent(), message.getAuthor());
        }
        else
            return null;
    }
    @Override
    public Message updateMessage(Message message){
        Message soapMessageToPut = RestClient.getMessage(message.getId());
        if(message.getAuthor()!=null && message.getMessageContent()!=null && soapMessageToPut !=null) {
            return RestClient.updateMessage(message.getId(), soapMessageToPut.getMessageContent(), soapMessageToPut.getAuthor());
        }else
            return null;
    }
    @Override
    public Message removeMessage(long id){
        return  RestClient.removeMessage(id);
    }
    @Override
    public String removeAllMessages(){
        return RestClient.removeAllMessages();
    }
}