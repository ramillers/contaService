package com.example.contaService;

import com.example.contaService.dto.ContaRequestDTO;
import com.example.contaService.dto.ContaResponseDTO;
import com.example.contaService.exception.ContaExistenteException;
import com.example.contaService.service.ContaService;
import com.example.contaService.service.PixService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@WebMvcTest
public class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ContaService contaService;

    @MockitoBean
    private PixService pixService;

    @Test
    void deveCriarAContaComSucesso() throws Exception {
        ContaRequestDTO request = ContaRequestDTO.builder()
                .nomeTitular("Paula")
                .numeroAgencia(123)
                .numeroConta(15)
                .chavePix("paula@pix.com")
                .build();

        UUID idDaContaSalva = UUID.randomUUID();

        ContaResponseDTO response = ContaResponseDTO.builder()
                .id(idDaContaSalva)
                .nomeTitular(request.getNomeTitular())
                .build();

        when(contaService.criarConta(request)).thenReturn(response);

        mockMvc.perform(post("/api/contas") //tá perfomando uma requisição post (mesmo do postman)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("nomeTitular").value("Paula"))
                .andExpect(status().isCreated());

    }

    @Test
    void erroAoInserirConta() throws Exception {
        ContaRequestDTO request = ContaRequestDTO.builder()
                .nomeTitular("Samuel")
                .numeroAgencia(10)
                .numeroConta(20)
                .chavePix("cliente@pix.com")
                .build();

        UUID idDaContaSalva = UUID.randomUUID();

        when(contaService.criarConta(request)).thenThrow(new ContaExistenteException("Conta já existe."));

        mockMvc.perform(post("/api/contas")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

}