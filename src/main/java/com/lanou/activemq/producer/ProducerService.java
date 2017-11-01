package com.lanou.activemq.producer;


import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by dllo on 17/11/1.
 */

@Service
public class ProducerService {

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination,String msg){
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        };
        jmsTemplate.send(destination,messageCreator);
    }

    public void sendMessage(final String msg){

       String destinationName = jmsTemplate.getDefaultDestinationName();
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        };
        jmsTemplate.send(destinationName,messageCreator);
    }
}
