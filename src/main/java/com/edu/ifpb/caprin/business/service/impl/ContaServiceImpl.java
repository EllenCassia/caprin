// package com.edu.ifpb.caprin.business.service.impl;

// import com.edu.ifpb.caprin.business.service.ContaService;
// // import com.edu.ifpb.caprin.business.service.exception.CpfAlreadyExistsException;
// // import com.edu.ifpb.caprin.business.service.exception.EmailAlreadyExistsException;
// import com.edu.ifpb.caprin.business.service.exception.NoSuchElementFoundException;
// import com.edu.ifpb.caprin.model.entity.Conta;
// import com.edu.ifpb.caprin.model.repository.ContaRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;


// @Service
// public class ContaServiceImpl implements ContaService {

//     @Autowired
//     private ContaRepository contaRepository; // Repositório para manipulação de dados

//     @Autowired
//     // private PasswordEncoder passwordEncoder; // Para criptografar senhas

//     // @Override
//     // public Conta create(Conta conta) {
//     //     // // Valida se o e-mail já está cadastrado
//     //     // if (contaRepository.findByEmail(conta.getEmail()).isPresent()) {
//     //     //     throw new EmailAlreadyExistsException("E-mail já cadastrado: " + conta.getEmail());
//     //     // }

//     //     // // Valida se o CPF já está cadastrado
//     //     // if (contaRepository.findByCpf(conta.getCpf()).isPresent()) {
//     //     //     throw new CpfAlreadyExistsException("CPF já cadastrado: " + conta.getCpf());
//     //     // }

//     //     // // Criptografa a senha antes de salvar
//     //     // conta.setSenha(passwordEncoder.encode(conta.getSenha()));

//     //     // Salva a nova conta
//     //     return contaRepository.save(conta);
//     // }

//     @Override
//     public Conta update(Long id, Conta novosDados) {
//         // Verifica se a conta existe
//         Conta contaExistente = findById(id);

//         // // Valida se o novo e-mail já está cadastrado (exceto para o próprio e-mail da conta existente)
//         // if (!novosDados.getEmail().equals(contaExistente.getEmail()) &&
//         //     contaRepository.findByEmail(novosDados.getEmail()).isPresent()) {
//         //     throw new EmailAlreadyExistsException("E-mail já cadastrado: " + novosDados.getEmail());
//         // }

//         // // Valida se o novo CPF já está cadastrado (exceto para o próprio CPF da conta existente)
//         // if (!novosDados.getCpf().equals(contaExistente.getCpf()) &&
//         //     contaRepository.findByCpf(novosDados.getCpf()).isPresent()) {
//         //     throw new CpfAlreadyExistsException("CPF já cadastrado: " + novosDados.getCpf());
//         // }

//         // // Atualiza os dados da conta existente
//         // contaExistente.setNome(novosDados.getNome());
//         // contaExistente.setEmail(novosDados.getEmail());
//         // contaExistente.setCpf(novosDados.getCpf());
        
//         // // Se a senha foi alterada, criptografa a nova senha
//         // if (novosDados.getSenha() != null && !novosDados.getSenha().isEmpty()) {
//         //     contaExistente.setSenha(passwordEncoder.encode(novosDados.getSenha()));
//         // }

//         // Salva as alterações
//         return contaRepository.save(contaExistente);
//     }

//     @Override
//     public Conta findById(Long id) {
//         // Busca a conta pelo ID e verifica se existe
//         return contaRepository.findById(id).orElseThrow(() ->
//                 new NoSuchElementFoundException("Conta não encontrada com ID: " + id));
//     }

//     @Override
//     public void delete(Long id) {
//         // Verifica se a conta existe antes de excluir
//         Conta conta = findById(id);
//         contaRepository.delete(conta);
//     }

// }

