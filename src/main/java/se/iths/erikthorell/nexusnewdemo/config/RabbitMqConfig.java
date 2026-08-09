package se.iths.erikthorell.nexusnewdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMqConfig {
    public static final String SHIFT_BOOKED_QUEUE = "shift-booked-queue";

    @Bean
    public Queue rabbitMqQueue(){
        return new Queue(SHIFT_BOOKED_QUEUE);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new JacksonJsonMessageConverter();
    }
}
