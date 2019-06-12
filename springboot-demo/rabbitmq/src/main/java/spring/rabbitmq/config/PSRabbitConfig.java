package spring.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发布订阅模式(publish-subscribe)
 */
@Configuration
public class PSRabbitConfig {
    public static final String FANOUT_EXCHANGE = "fanout-exchange";

    public static final String FANOUT_QUEUE_A = "fanout-queue-A";

    public static final String FANOUT_QUEUE_B = "fanout-queue-B";

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Queue fanoutQueueA(){
        return new Queue(FANOUT_QUEUE_A,true);
    }

    @Bean
    public Queue fanoutQueueB(){
        return new Queue(FANOUT_QUEUE_B,true);
    }

    @Bean
    public Binding fanoutBindingA(){
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingB(){
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

}
