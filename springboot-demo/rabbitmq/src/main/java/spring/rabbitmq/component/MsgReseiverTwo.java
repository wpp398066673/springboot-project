package spring.rabbitmq.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import spring.rabbitmq.config.RabbitConfig;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReseiverTwo {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @RabbitHandler
    public void process(String content){
        logger.info("处理器two接受处理队里A中的消息: "+content);
    }
}
