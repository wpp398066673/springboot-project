package spring.rabbitmq.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import spring.rabbitmq.config.PSRabbitConfig;

@Component
public class PSReseiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = PSRabbitConfig.FANOUT_QUEUE_A)
    @RabbitHandler
    public void  process(String content){
        logger.info("FANOUT_QUEUE_A处理器："+content);
    }

    @RabbitListener(queues = PSRabbitConfig.FANOUT_QUEUE_B)
    @RabbitHandler
    public void handler(String content){
        logger.info("FANOUT_QUEUE_B处理器："+content);
    }
}
