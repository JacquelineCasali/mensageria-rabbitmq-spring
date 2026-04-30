package com.florinda.pedidos.integration.rabbit;


import com.florinda.pedidos.config.AmqpConfig;
import com.florinda.pedidos.domain.enums.StatusPedido;
import com.florinda.pedidos.domain.model.Pedido;
import com.florinda.pedidos.repository.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PagamentoConfirmadoListener{

    private final PedidoRepository pedidoRepository;

    public PagamentoConfirmadoListener(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @RabbitListener(queues = AmqpConfig.PAGAMENTOS_CONFIRMADO_QUEUE)
     @Transactional
    public  void pagamantoConfirmado(PagamentoConfirmadoEvent pagamentoConfirmadoEvent){
        System.out.println("[Pedidos] Recebido novo pagamento confirmado:" + pagamentoConfirmadoEvent);
        //atualiza no banco de dados
        Pedido pedido = pedidoRepository.findById(pagamentoConfirmadoEvent.pedidoId())
                .orElseThrow();
        pedido.setStatus(StatusPedido.PAGO);
    }
}
