package spring.rabbitmq.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.rabbitmq.config.PSRabbitConfig;

@Component
public class PSMsgProducer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public PSMsgProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void psSend(String content){
        rabbitTemplate.convertAndSend(PSRabbitConfig.FANOUT_EXCHANGE,"", content);
    }
}
