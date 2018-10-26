package com.dingcheng.confirms.consumer.thread;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import java.io.IOException;
import java.util.Map;

/**
 * @author zph  on 2018/10/26
 */
public class MessageRecvInitializeTask implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRecvInitializeTask.class);


    private Message message;
    private Channel channel;

    public MessageRecvInitializeTask(Message message,Channel channel){
        this.message = message;
        this.channel = channel;
    }

    @Override
    public void run() {
        try{
            System.out.println("consumer--:"+message.getMessageProperties()+":"+new String(message.getBody()));
            Thread.sleep(1000);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch(Exception e){
            e.printStackTrace();//TODO 业务处理
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
