package com.florinda.pagamentos.service;


import com.florinda.pagamentos.config.AmqpConfig;
import com.florinda.pagamentos.domain.dto.PagamentoDTO;
import com.florinda.pagamentos.domain.model.Pagamento;
import com.florinda.pagamentos.exception.NotFoundException;
import com.florinda.pagamentos.integration.rabbit.PagamentoConfirmadoEvent;
import com.florinda.pagamentos.repository.PagamentoRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PagamentoServiceImpl implements PagamentoService {

private final PagamentoRepository pagamentoRepository;
private final AmqpTemplate amqpTemplate;
    public PagamentoServiceImpl(PagamentoRepository pagamentoRepository, AmqpTemplate amqpTemplate) {
        this.pagamentoRepository = pagamentoRepository;
        this.amqpTemplate=amqpTemplate;
    }



    @Override
    public List<PagamentoDTO> lista() {
        return pagamentoRepository.findAll().stream().map(PagamentoDTO::new).toList();

    }

    @Override
    public PagamentoDTO buscarPorId(Long id) {
        return pagamentoRepository.findById(id).map(PagamentoDTO::new)
                .orElseThrow(()-> new NotFoundException("Pagamento não encontrado: " + id ));
    }

    @Override
    @Transactional
    public PagamentoDTO confirma(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pagamento não encontrado com ID: " + id));
        pagamento.confirma();

        var evento =new PagamentoConfirmadoEvent(pagamento.getId(), pagamento.getValor(), pagamento.getPedidoId());

        // mandar uma mensagem para o rabbit mg de pagamento confirmado
        amqpTemplate.convertAndSend(AmqpConfig.PAGAMENTOS_EXCHANGE, "pagamentos.pagamento.confirmado",evento);

        log.info("Pagamento {} confirmado com sucesso para o pedido {}", id, pagamento.getPedidoId());
        return new PagamentoDTO(pagamentoRepository.save(pagamento));
    }


}
