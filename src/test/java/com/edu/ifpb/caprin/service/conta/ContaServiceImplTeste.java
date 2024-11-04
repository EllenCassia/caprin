package com.edu.ifpb.caprin.service.conta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.edu.ifpb.caprin.business.service.impl.conta.ContaServiceImpl;
import com.edu.ifpb.caprin.business.service.impl.conta.EnderecoServiceImpl;
import com.edu.ifpb.caprin.model.conta.ContaUtils;
import com.edu.ifpb.caprin.model.conta.EnderecoUtils;
import com.edu.ifpb.caprin.model.entity.conta.Conta;
import com.edu.ifpb.caprin.model.entity.conta.Endereco;
import com.edu.ifpb.caprin.model.repository.conta.ContaRepository;
import com.edu.ifpb.caprin.model.utils.EmailUtils;
import com.edu.ifpb.caprin.model.utils.UrlUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ContaServiceImplTeste {

    @Mock
    private ContaRepository contaRepositorio;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailUtils emailUtils;
    @Spy
    private UrlUtils urlUtils;

    // @Mock
    // private ContaConfirmacaoServico confirmacaoServico;

    @Mock
    private EnderecoServiceImpl enderecoServico;

    // @Mock
    // private ContaRedefinicaoServicoImpl redefinicaoServico;

    @InjectMocks
    private ContaServiceImpl contaServico;

    @Test
    void registerSuccess() {

        // Criação dos objetos de teste
        Conta conta = ContaUtils.criarConta();
        Endereco endereco = EnderecoUtils.criarEndereco();
        String senhaCriptografada = "$2a$12$5KWwqSfp3sM2Podei9Mlx.HjNStDxO.HHINWCw9ziQxylBRe5.3aK";

        // Configuração dos mocks
        when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.empty());
        when(contaRepositorio.findByCpf(anyString())).thenReturn(Optional.empty());
        when(enderecoServico.create(any(Endereco.class))).thenReturn(endereco);
        when(passwordEncoder.encode(anyString())).thenReturn(senhaCriptografada);
        when(contaRepositorio.save(any(Conta.class))).thenReturn(conta); 

        // Execução do método de teste
        Conta contaNova = contaServico.register(conta);

        // Verificações
        assertNotNull(contaNova);
        assertEquals("john.doe@gmail.com", contaNova.getEmail());
        verify(contaRepositorio).save(any(Conta.class));
        verify(passwordEncoder).encode(anyString());
        verify(enderecoServico).create(any(Endereco.class));
    }
}
