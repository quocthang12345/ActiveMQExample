package com.example.Example.listener;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ListenerAndProducer {
	@JmsListener(destination = "inbound.queue")
    @SendTo("outbound.queue")
    public String receiveAndSendMessage(final Message jsonMessage) throws JMSException, JsonMappingException, JsonProcessingException {
        String messageData = null;
        System.out.println("Tin nhắn nhận được: " + jsonMessage);
        String response = null;
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            
//            Map map = new ObjectMapper().readValue(messageData, Map.class);
//            response  = "Chào " + map.get("name");
            response = "Chào " + messageData;
        }
        return response;
    }
}
