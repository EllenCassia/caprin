package com.edu.ifpb.caprin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parentesco {

    public int pai;
    public int bisavoPaterna;
    public int bisavoPaterno;
    public int bisavoMaterna;
    public int bisavoMaterno;
    public int avouPaterna;
    public int avohPaterno;
    public int avohMaterna;
    public int avouMaterno;
    public int mae;

}
