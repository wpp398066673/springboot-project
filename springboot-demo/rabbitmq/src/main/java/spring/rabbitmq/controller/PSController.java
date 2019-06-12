package spring.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.rabbitmq.component.PSMsgProducer;

@RestController
@RequestMapping("/ps")
public class PSController {

    @Autowired
    private PSMsgProducer psMsgProducer;
    @RequestMapping("/send")
    public void psSend(){
        for (int i=0; i<5; i++){
            psMsgProducer.psSend("PS发布的第"+i+"个消息");
        }
    }
}
