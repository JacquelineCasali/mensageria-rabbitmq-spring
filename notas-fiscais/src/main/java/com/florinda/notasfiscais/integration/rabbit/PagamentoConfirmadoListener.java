package com.florinda.notasfiscais.integration.rabbit;


import com.florinda.notasfiscais.config.AmqpConfig;
import com.florinda.notasfiscais.service.NotaFiscalService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PagamentoConfirmadoListener {

 private final NotaFiscalService notaFiscalService;

    public PagamentoConfirmadoListener(NotaFiscalService notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }

    @RabbitListener(queues = AmqpConfig.PAGAMENTO_CONFIRMADO_QUEUE)

    public  void pagamantoConfirmado(PagamentoConfirmadoEvent pagamentoConfirmadoEvent){
        System.out.println("[Notas Fiscais] Recebido novo pagamento confirmado:" + pagamentoConfirmadoEvent);
       Long pedidoId = pagamentoConfirmadoEvent.pedidoId();
        BigDecimal valorPagamento = pagamentoConfirmadoEvent.valor();
        String notaFiscalXML = notaFiscalService.geraNotaFiscal(pedidoId,valorPagamento);
        System.out.println(notaFiscalXML);

         }
}
