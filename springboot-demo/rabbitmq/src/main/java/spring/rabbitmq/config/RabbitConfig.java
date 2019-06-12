package spring.rabbitmq.config;


import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    public static final String EXCHANGE_A = "my-mq-exchange-A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";
    public static final String EXCHANGE_C = "my-mq-exchange_C";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";

    /**
     * 另外一种消息处理机制的写法如下，在RabbitMQConfig类里面增加bean：
     * 类似MsgReseiver类处理
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer(){
        //加载处理消息A的队列
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        //设置接收多个队列里面的消息，这里设置接收队列A
        //假如想一个消费者处理多个队列里面的信息可以如下设置：
        //container.setQueues(queueA(),queueB(),queueC())
        container.setQueues(queueA(),queueB());
        container.setExposeListenerChannel(true);
        //设置最大并发消费者数量
        container.setMaxConcurrentConsumers(10);
        //设置最小并发消费者数量
        container.setConcurrentConsumers(1);
        //设置确认模式手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                /**通过basic.qos方法设置prefetch_count=1，这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message，
                 换句话说,在接收到该Consumer的ack前,它不会将新的Message分发给它 */
                channel.basicQos(1);
                byte[] body = message.getBody();
                logger.info("SimpleMessageListenerContainer接收处理队列A或者B当中的消息:" + new String(body));
                /**为了保证永远不会丢失消息，RabbitMQ支持消息应答机制。
                 当消费者接收到消息并完成任务后会往RabbitMQ服务器发送一条确认的命令，然后RabbitMQ才会将消息删除。*/
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            }
        });
        return container;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);//这里需要显示调用，才能进行消息回调
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    /**
     * 针对消费者配置
     *      * 1. 设置交换机类型
     *      * 2. 将队列绑定到交换机
     *      FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     *      HeadersExchange ：通过添加属性key-value匹配
     *      DirectExchange:按照routingkey分发到指定队列
     *      TopicExchange:多关键字匹配

     * @return
     */
    @Bean
    public DirectExchange defaultExchange(){
        return new DirectExchange(EXCHANGE_A);
    }

    /**
     * 获取队列
     * @return
     */
    @Bean
    public Queue queueA(){
        return new Queue(QUEUE_A, true);//队列持久
    }

    @Bean
    public Queue queueB(){
        return new Queue(QUEUE_B, true);//队列持久
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }

    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_B);
    }
}
