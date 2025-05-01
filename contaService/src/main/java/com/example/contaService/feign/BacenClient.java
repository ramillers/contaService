package com.example.contaService.feign;

import com.example.contaService.feign.dto.ChaveRequestDTO;
import com.example.contaService.feign.dto.ChaveResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        contextId = "BacenClient",
        name = "Bacen",
        url = "http://localhost:9002/api/bacen"
)
public interface BacenClient {

    @PostMapping("/chaves")
    ChaveResponseDTO criarChave(ChaveRequestDTO chaveRequestDTO);

    @GetMapping(value = "/chaves/{chave}")
    ChaveResponseDTO buscaChave(@PathVariable final String chave);

}