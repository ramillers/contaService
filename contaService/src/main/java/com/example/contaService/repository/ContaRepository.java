package com.example.contaService.repository;

import java.util.Optional;
import java.util.UUID;
    import com.example.contaService.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, UUID> {
    Optional<Conta> findByChavePix(String chavePixPagador);

    Optional<Conta> findByNomeTitularAndNumeroContaAndChavePix(String nomeTitular, Integer numeroConta, String chavePix);

}