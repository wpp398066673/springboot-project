package spring.rabbitmq.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.rabbitmq.config.RabbitConfig;

import java.util.UUID;

@Component
public class MsgProducerTwo implements RabbitTemplate.ConfirmCallback {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;
    @Autowired
    public MsgProducerTwo(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendMsg(String content){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_B, content, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

        if(b){
            logger.info("消费成功");
        }else {
            logger.info("消费失败");
        }

    }
}
