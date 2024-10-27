package com.edu.ifpb.caprin.model;

import lombok.Data;
import jakarta.persistence.*;


@Data
@Entity
public class Parentesco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int PAI;
    private int MAE;
    private int AVO_MATERNO;
    private int AVOH_MATERNA;
    private int AVOH_PATERNO;
    private int AVOU_PATERNA;
    private int BISAVO_MATERNO;
    private int BISAVO_MATERNA;
    private int BISAVO_PATERNO;
    private int BISAVO_PATERNA;
}
