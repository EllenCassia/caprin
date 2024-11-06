package com.edu.ifpb.caprin.model.dto.animal;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class AnimalResposta {
 
    private String nome;
    private String registro;
    private String dataNascimento;
    private String sexo;  // Pode ser mantido como String para mais flexibilidade
    private String raca;
    private String criador;
    private String proprietario;

    // @Schema(description = "IDs dos filhos do pai", example = "[1, 2, 3]")
    // private List<Long> filhospaiIds; 

    // @Schema(description = "IDs dos filhos da mãe", example = "[4, 5, 6]")
    // private List<Long> filhosmaeIds; 

    // @Schema(description = "IDs das inscrições", example = "[7, 8, 9]")
    // private List<Long> inscricoesIds; 
}