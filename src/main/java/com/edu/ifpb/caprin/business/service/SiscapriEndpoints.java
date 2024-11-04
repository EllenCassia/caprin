package com.edu.ifpb.caprin.business.service;

public class SiscapriEndpoints {
    static final String URL_SISCAPRI_ARVOREGEN_START = "https://siscapri.abccaprinos.com.br/x.php?m=siscapri.genealogia&site=siscapri";
    static final String SELECTPATTERNEXTRACT_VIEWSTATE = "#viewstate";
    static final String SELECTPATTERNEXTRACT_RACAS = "#i_keyword_0 option";

    static final String URL_SISCAPRI_ARVOREGEN_BUSCARANIMAL = "https://siscapri.abccaprinos.com.br/x.php?m=siscapri.Genealogia&f=Buscar&site=siscapri&runat=client";
    static final String SELECTPATTERNEXTRACT_BUSCARANIMAL = "div#divResultado table tbody tr";

    static final String URL_SISCAPRI_ARVOREGEN_BUSCARPARENTESCO = "https://siscapri.abccaprinos.com.br/x.php?m=siscapri.genealogia&f=mostraArvoreGenealogica&site=siscapri&runat=client";
    static final String SELECTPATTERNEXTRACT_BUSCARPARENTESCO = "div#divArvore div.divArvoreInterna table tbody tr td";
}
