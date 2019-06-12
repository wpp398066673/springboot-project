package spring.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.rabbitmq.component.MsgProducer;
import spring.rabbitmq.component.MsgProducerTwo;

@RestController
@RequestMapping("/rabbit")
public class MsgController {
    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private MsgProducerTwo msgProducerTwo;

    @RequestMapping("/send")
    public void sendMsg() {
        msgProducer.sendMsg("这是我发送的第1个消息A");
    }

    @RequestMapping("/oneToMany")
    public void sendMsgOneToMany(){
        for (int i=1; i<10; i++){
            msgProducer.sendMsg("这是我发送的第"+i+"消息A");
        }
    }
    @RequestMapping("/manyToMany")
    public void sendManyToMany(){
        for(int i=0; i<10; i++){
            msgProducer.sendMsg("msgProducer这是我发送的第"+i+"消息A");
            msgProducerTwo.sendMsg("msgProducerTwo这是我发送的第"+i+"消息B");
        }
    }

}
