package com.florinda.pagamentos.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {


    public static final String PAGAMENTOS_EXCHANGE = "pagamentos";


    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange pagamanetosExchange(){
        return new TopicExchange(PAGAMENTOS_EXCHANGE);
    }


}
