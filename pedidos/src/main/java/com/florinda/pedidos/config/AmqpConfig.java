package com.florinda.pedidos.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    public static final String PAGAMENTOS_CONFIRMADO_QUEUE = "pedidos.pagamentos-confirmado";


    // public static final String PAGAMENTOS_EXCHANGE = "pagamentos";


    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange pagamanetosExchange(){
        return new TopicExchange("pagamentos");
    }

    @Bean
        public Queue filaPagamentosConfirmadosPedido(){
            return new Queue(PAGAMENTOS_CONFIRMADO_QUEUE);
    }
    @Bean
    public Binding bindingPagamentosConfirmados(Queue filaPagamentosConfirmadosPedido,
                                                TopicExchange pagamentosExchange) {
        return BindingBuilder.bind(filaPagamentosConfirmadosPedido)
                .to(pagamentosExchange)
                .with("pagamentos.pagamento.confirmado");
    }
}
