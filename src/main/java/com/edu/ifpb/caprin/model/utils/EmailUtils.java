package com.edu.ifpb.caprin.model.utils;


import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.*;
import jakarta.mail.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Service
@Data
@Slf4j
public class EmailUtils {

    private final Environment env;
    private final Executor executor = Executors.newFixedThreadPool(3);

    public static void enviarEmail(String destinatario, String assunto, String corpo) {
        try {
            // Configurações do servidor de e-mail
            Properties propriedades = new Properties();
            propriedades.put("mail.smtp.auth", "true");
            propriedades.put("mail.smtp.starttls.enable", "true");
            propriedades.put("mail.smtp.host", "smtp.gmail.com"); // Use o host do Gmail
            propriedades.put("mail.smtp.port", "587");

            // Autenticação
            Session sessao = Session.getInstance(propriedades, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("caprin@ifpb.edu.br", "yspzpgqgfvpmrxgo"); // Coloque seu e-mail e senha aqui
                }
            });

            // Mensagem
            Message mensagem = new MimeMessage(sessao);
            mensagem.setFrom(new InternetAddress("caprin@ifpb.edu.br")); // De
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario)); // Para
            mensagem.setSubject(assunto);
            mensagem.setText(corpo);

            // Enviar
            Transport.send(mensagem);
            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailAtivacaoConta(String emailDestinatario, String url) throws MessagingException {
        try {
            String titulo = "Ativação de Conta";
            String corpo = criarMensagemAtivacao(url);
            enviarEmail(emailDestinatario, titulo, corpo);
        } catch (Exception e) {
            throw new MessagingException();
        }
    }

    public void enviarEmailRedefinicaoSenha(String emailDestinatario, String codigo) throws MessagingException {
        try {
            String titulo = "Código para Redefinição de Senha";
            String corpo = criarMensagemRedefinicao(codigo);
            enviarEmail(titulo, corpo, emailDestinatario);
        } catch (Exception e) {
            throw new MessagingException();
        }
    }

    public int getTentativasEnvioAtivacao(String email, String url, int numeroTentativas) {
        int maxTentativas = 5;

        try {
            enviarEmailAtivacaoConta(email, url);
        } catch (MessagingException e) {
            if (!(numeroTentativas >= maxTentativas)) {
                ++numeroTentativas;
                getTentativasEnvioAtivacao(email, url, numeroTentativas);
            }
        }
        return numeroTentativas;
    }

    public int getTentativasEnvioRedefinicao(String email, String codigo, int numeroTentativas) {
        int maxTentativas = 5;

        try {
            enviarEmailRedefinicaoSenha(email, codigo);
        } catch (MessagingException e) {
            if (!(numeroTentativas >= maxTentativas)) {
                ++numeroTentativas;
                getTentativasEnvioRedefinicao(email, codigo, numeroTentativas);
            }
        }
        return numeroTentativas;
    }

    public String criarMensagemAtivacao(String url) {
        return
                "<html>\n" +
                        "<body style=\"font-family: Arial, sans-serif;\">\n" +
                        "<p>Olá!</p>\n" +
                        "<p>Viemos para ajudá-lo a concluir seu cadastro no <strong>CAPRIN</strong>.</p>\n" +
                        "<p>Você só precisa clicar no botão abaixo para confirmarmos que este e-mail pertence a você:</p>\n" +
                        "<a href="+url+" style=\"display: inline-block; background-color: #4CAF50; color: white; padding: 14px 20px; text-align: center; text-decoration: none; border-radius: 4px;\">Botão de Ativação</a>\n" +
                        "<p>Se você não conseguir clicar, copie o link abaixo e cole-o em seu navegador:</p>\n" +
                        "<p><strong>"+url+"</strong></p>\n" +
                        "</body>\n" +
                        "</html>";
    }

    public String criarMensagemRedefinicao(String codigo) {
        return
                "<html>\n" +
                        "<body style=\"font-family: Arial, sans-serif;\">\n" +
                        "<p>Olá!</p>\n" +
                        "<p>Viemos para ajudá-lo no processo de redefinição de sua senha no <strong>CAPRIN</strong>.</p>\n" +
                        "<p>Esse é seu código de verificação: <strong>"+codigo+"</strong></p>\n" +
                        "</body>\n" +
                        "</html>";
    }

    private String getEmailCaprin() {
        return env.getProperty("caprin.email");
    }
}