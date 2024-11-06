package com.edu.ifpb.caprin.model.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {

    public static LocalDate converterParaLocalDate(String dataString) {
        DateTimeFormatter conversor = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataString, conversor);
        return data;
    }

    public static boolean isDateExpired(LocalDateTime dataExpiracao) {
        return dataExpiracao.isBefore(LocalDateTime.now());
    }

}
