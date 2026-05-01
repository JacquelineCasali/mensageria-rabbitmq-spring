package com.florinda.notasfiscais.integration.rabbit;


import com.florinda.notasfiscais.config.AmqpConfig;
import com.florinda.notasfiscais.service.NotaFiscalService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PagamentoConfirmadoListener {

 private final NotaFiscalService notaFiscalService;
 private final long delayEmMs;
    public PagamentoConfirmadoListener(NotaFiscalService notaFiscalService, @Value("${app.simulacao.delay:0}") long delayEmMs) {
        this.notaFiscalService = notaFiscalService;
        this.delayEmMs=delayEmMs;
    }

    @RabbitListener(queues = AmqpConfig.PAGAMENTO_CONFIRMADO_QUEUE)

    public  void pagamantoConfirmado(PagamentoConfirmadoEvent pagamentoConfirmadoEvent){
        System.out.println("[Notas Fiscais] Recebido novo pagamento confirmado:" + pagamentoConfirmadoEvent);
       if(delayEmMs>0){
           System.out.println("Simulando delay(ms):" + delayEmMs);
           try{
               Thread.sleep(delayEmMs);
           }catch (InterruptedException e){
               Thread.currentThread().interrupt();
               System.out.println("Thread interrompida");
           }
       }

        Long pedidoId = pagamentoConfirmadoEvent.pedidoId();
        BigDecimal valorPagamento = pagamentoConfirmadoEvent.valor();
        String notaFiscalXML = notaFiscalService.geraNotaFiscal(pedidoId,valorPagamento);
        System.out.println(notaFiscalXML);

         }
}
