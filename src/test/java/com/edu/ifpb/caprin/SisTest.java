// package com.edu.ifpb.caprin.service.conta;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Spy;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.security.crypto.password.PasswordEncoder;


// import com.edu.ifpb.caprin.business.service.impl.conta.ContaServiceImpl;
// import com.edu.ifpb.caprin.business.service.impl.conta.EnderecoServiceImpl;
// import com.edu.ifpb.caprin.model.conta.ContaUtils;
// import com.edu.ifpb.caprin.model.entity.conta.Conta;
// import com.edu.ifpb.caprin.model.entity.conta.Endereco;
// import com.edu.ifpb.caprin.model.repository.ContaRepository;
// import com.edu.ifpb.caprin.model.utils.EmailUtils;
// import com.edu.ifpb.caprin.model.utils.UrlUtils;

// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// @SpringBootTest
// @ExtendWith(MockitoExtension.class)
// class ContaServicoImplTest {

//     @Mock
//     private ContaRepository contaRepositorio;
//     @Mock
//     private PasswordEncoder passwordEncoder;
//     @Mock
//     private EmailUtils emailUtils;

//     @Spy
//     private UrlUtils urlUtils;

//     // @Mock
//     // private ContaConfirmacaoServico confirmacaoServico;
//     @Mock
//     private EnderecoServiceImpl enderecoServico;
//     @Mock
//     // private ContaRedefinicaoServicoImpl redefinicaoServico;

//     @InjectMocks
//     private ContaServiceImpl contaServico;

//     // @Test
//     // void activateAccountSuccess() {
//     //     Conta conta = ContaUtils.criarConta();
//     //     conta.setAtiva(false);

//     //     when(contaRepositorio.save(any(Conta.class))).thenReturn(conta);

//     //     Conta resposta = contaServico.activateAccount(conta);

//     //     assertTrue(resposta.isAtiva());
//     //     verify(contaRepositorio).save(any(Conta.class));
//     // }

//     @Test
//     void registerSuccess() {

//         Conta conta = ContaUtils.criarConta();
//         Endereco endereco = EnderecoUtils.criarEndereco();
//         // ContaConfirmacao confirmacao = ContaConfirmacaoUtils.criarContaConfirmacao();
//         String senhaCriptografada = "$2a$12$5KWwqSfp3sM2Podei9Mlx.HjNStDxO.HHINWCw9ziQxylBRe5.3aK";

//         when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.empty());
//         when(contaRepositorio.findByCpf(anyString())).thenReturn(Optional.empty());
//         when(enderecoServico.create(any(Endereco.class))).thenReturn(endereco);
//         when(passwordEncoder.encode(anyString())).thenReturn(senhaCriptografada);
//         when(contaRepositorio.save(any(Conta.class))).thenReturn(conta);
        
//         // when(confirmacaoServico.register(any(Conta.class), anyString())).thenReturn(confirmacao);
//         when(emailUtils.getTentativasEnvioAtivacao(anyString(), anyString(), anyInt())).thenReturn(1);

//         // Conta contaNova = contaServico.register(conta);

//         // assertNotNull(contaNova.getId());
//         // verify(enderecoServico).register(any(Endereco.class));
//         // verify(passwordEncoder).encode(anyString());
//         // verify(contaRepositorio).save(any(Conta.class));
//         // verify(confirmacaoServico).register(any(Conta.class), anyString());
//         // verify(emailUtils).getTentativasEnvioAtivacao(anyString(), anyString(), anyInt());
//     }

//     // @Test
//     // void registerErrorEmailAlreadyExistsException() {
//     //     Conta conta = ContaUtils.criarConta();
//     //     when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.of(conta));
//     //     assertThrows(EmailAlreadyExistsException.class, () -> contaServico.register(conta));
//     // }

//     // @Test
//     // void registerErrorCpfAlreadyExistsException() {
//     //     Conta conta = ContaUtils.criarConta();

//     //     when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.empty());
//     //     when(contaRepositorio.findByCpf(anyString())).thenReturn(Optional.of(conta));

//     //     assertThrows(CpfAlreadyExistsException.class, () -> contaServico.register(conta));
//     // }

//     // @Test
//     // void registerWhenSendingAttemptsAreGreaterThanFour() {
//     //     Conta conta = ContaUtils.criarConta();
//     //     Endereco endereco = EnderecoUtils.criarEndereco();
//     //     ContaConfirmacao confirmacao = ContaConfirmacaoUtils.criarContaConfirmacao();
//     //     String senhaCriptografada = "$2a$12$5KWwqSfp3sM2Podei9Mlx.HjNStDxO.HHINWCw9ziQxylBRe5.3aK";

//     //     when(enderecoServico.register(any(Endereco.class))).thenReturn(endereco);
//     //     when(passwordEncoder.encode(anyString())).thenReturn(senhaCriptografada);
//     //     when(contaRepositorio.save(any(Conta.class))).thenReturn(conta);
//     //     when(confirmacaoServico.register(any(Conta.class), anyString())).thenReturn(confirmacao);
//     //     when(emailUtils.getTentativasEnvioAtivacao(anyString(), anyString(), anyInt())).thenReturn(5);
//     //     when(contaRepositorio.findById(anyLong())).thenReturn(Optional.of(conta));

//     //     contaServico.register(conta);

//     //     verify(contaRepositorio).delete(any(Conta.class));
//     // }

//     // @Test
//     // void updatePasswordSuccess() {
//     //     Conta conta = ContaUtils.criarConta();
//     //     String novaSenha = "novasenha123";
//     //     String confirmacaoSenha = "novasenha123";
//     //     String senhaCriptografada = "$2a$12$io14/a21TVmBB/vHHEXxMenvo4/BvqPcTmgASHfqXD94HFDf3U0KC";

//     //     when(passwordEncoder.encode(anyString())).thenReturn(senhaCriptografada);
//     //     when(contaRepositorio.save(any(Conta.class))).thenReturn(conta);

//     //     Conta contaAtualizada = contaServico.updatePassword(conta, novaSenha, confirmacaoSenha);

//     //     assertEquals(senhaCriptografada, contaAtualizada.getSenha());
//     // }

//     // @Test
//     // void updatePasswordErrorPasswordNotMatchingException() {
//     //     Conta conta = ContaUtils.criarConta();
//     //     String novaSenha = "novasenha123";
//     //     String confirmacaoSenha = "novasenha12";

//     //     assertThrows(PasswordNotMatchingException.class, () -> contaServico.updatePassword(conta, novaSenha, confirmacaoSenha));
//     // }

//     // @Test
//     // void updateSuccess() {
//     //     String novoEmail = "john.doe2@gmail.com";
//     //     Conta conta = ContaUtils.criarConta();
//     //     ContaRequisicao requisicao = ContaUtils.criarContaRequisicao();
//     //     requisicao.setEmail(novoEmail);

//     //     when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.empty());
//     //     when(contaRepositorio.findByCpf(anyString())).thenReturn(Optional.empty());
//     //     when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.empty());
//     //     when(contaRepositorio.findById(anyLong())).thenReturn(Optional.of(conta));
//     //     when(contaRepositorio.save(any(Conta.class))).thenReturn(conta);

//     //     Conta contaAtualizada = contaServico.update(1L, requisicao);

//     //     assertEquals(novoEmail, contaAtualizada.getEmail());
//     // }

//     // @Test
//     // void updateErrorEmailAlreadyExistsException() {
//     //     String novoEmail = "john.doe2@gmail.com";
//     //     Conta conta = ContaUtils.criarConta();
//     //     ContaRequisicao requisicao = ContaUtils.criarContaRequisicao();
//     //     requisicao.setEmail(novoEmail);

//     //     when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.of(conta));

//     //     assertThrows(EmailAlreadyExistsException.class, () -> contaServico.update(1L, requisicao));
//     //     verify(contaRepositorio).findByEmail(anyString());
//     // }

//     // @Test
//     // void updateErrorCpfAlreadyExistsException() {
//     //     String novoEmail = "john.doe2@gmail.com";
//     //     Conta conta = ContaUtils.criarConta();
//     //     ContaRequisicao requisicao = ContaUtils.criarContaRequisicao();
//     //     requisicao.setEmail(novoEmail);

//     //     when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.empty());
//     //     when(contaRepositorio.findByCpf(anyString())).thenReturn(Optional.of(conta));

//     //     assertThrows(CpfAlreadyExistsException.class, () -> contaServico.update(1L, requisicao));
//     //     verify(contaRepositorio).findByCpf(anyString());
//     // }

//     // @Test
//     // void findByEmailSuccess() {
//     //     Conta conta = ContaUtils.criarConta();

//     //     when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.of(conta));

//     //     Conta resposta = contaServico.findByEmail(conta.getEmail());

//     //     assertConta(resposta);
//     //     verify(contaRepositorio).findByEmail(anyString());
//     // }

//     // @Test
//     // void findByEmailErrorNoSuchElementFoundException() {
//     //     when(contaRepositorio.findByEmail(anyString())).thenReturn(Optional.empty());

//     //     assertThrows(NoSuchElementFoundException.class, () -> contaServico.findByEmail(anyString()));

//     //     verify(contaRepositorio).findByEmail(anyString());
//     // }

//     // @Test
//     // void findByIdSuccess() {
//     //     Conta conta = ContaUtils.criarConta();

//     //     when(contaRepositorio.findById(anyLong())).thenReturn(Optional.of(conta));

//     //     Conta resposta = contaServico.buscarPorID(anyLong());

//     //     assertConta(resposta);
//     //     verify(contaRepositorio).findById(anyLong());
//     // }

//     // @Test
//     // void findByIdErrorNoSuchElementFoundException() {
//     //     when(contaRepositorio.findById(anyLong())).thenReturn(Optional.empty());

//     //     assertThrows(NoSuchElementFoundException.class, () -> contaServico.buscarPorID(anyLong()));

//     //     verify(contaRepositorio).findById(anyLong());
//     // }

//     // private void assertConta(Conta conta) {
//     //     assertEquals(1L, conta.getId());
//     //     assertEquals("john.doe@gmail.com", conta.getEmail());
//     //     assertEquals("john123", conta.getSenha());
//     //     assertEquals("69001945082", conta.getCpf());
//     //     assertEquals("100", conta.getContaTipo());
//     // }

// }