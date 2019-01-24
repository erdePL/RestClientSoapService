package RestClientSoapService.rest.Client;

import RestClientSoapService.soap.model.Message;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RestClient {
    private static javax.ws.rs.client.Client client = ClientBuilder.newClient();
    private static WebTarget baseTarget = client.target("http://localhost:10080/RestfulMessageService/webapi/");
    private static WebTarget messagesTarget = baseTarget.path("messages");
    private static WebTarget singleMessageTarget = messagesTarget.path("{messageId}");

    public static Message getMessage(long id){
        Message message = singleMessageTarget
                .resolveTemplate("messageId", id)
                .request(MediaType.APPLICATION_JSON)
                .get(Message.class);
        return message;
    }
    public static List<Message> getAllMessages(){
        Response response = messagesTarget
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);
        List<Message> messages = response.readEntity(new GenericType<List<Message>>(){});
        return messages;
    }
    public static Message addMessage (String messageContent, String author){
        Message message = new Message();
        message.setMessageContent(messageContent);
        message.setAuthor(author);
        Response postResponse = messagesTarget
                .request()
                .post(Entity.json(message));
        return postResponse.readEntity(Message.class);
    }
    public static Message updateMessage(long id, String messageContent, String author){
        Message message = new Message();
        message.setMessageContent(messageContent);
        message.setAuthor(author);
        Response putResponse = singleMessageTarget
                .resolveTemplate("messageId", id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(message));
        return putResponse.readEntity(Message.class);
    }
    public static Message removeMessage(long id){
        Response deleteResponse = singleMessageTarget
                .resolveTemplate("messageId", id)
                .request().delete();
        return deleteResponse.readEntity(Message.class);
    }
    public static String removeAllMessages(){
        Response deleteResponse = messagesTarget
                .request().delete();
        return deleteResponse.readEntity(String.class);
    }
}