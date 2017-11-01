package com.lanou.activemq.consumer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * Created by dllo on 17/11/1.
 */

@Service
public class ConsumerService {

    @Resource
    private JmsTemplate jmsTemplate;

    public TextMessage receiveMsg(Destination destination){

        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);

        if (textMessage != null){

        }
        return textMessage;
    }


}
