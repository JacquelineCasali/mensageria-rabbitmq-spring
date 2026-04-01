package com.florinda.pagamentos.repository;

import com.florinda.pagamentos.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
