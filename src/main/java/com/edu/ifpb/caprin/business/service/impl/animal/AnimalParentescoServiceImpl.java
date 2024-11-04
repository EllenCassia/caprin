package com.edu.ifpb.caprin.business.service.impl.animal;

import com.edu.ifpb.caprin.business.service.animal.AnimalParentescoService;
import com.edu.ifpb.caprin.model.entity.Parentesco;
import com.edu.ifpb.caprin.model.entity.animal.Animal;
import com.edu.ifpb.caprin.model.entity.animal.AnimalParentesco;
import com.edu.ifpb.caprin.model.repository.animal.AnimalParentescoRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class AnimalParentescoServiceImpl implements AnimalParentescoService {

    @Autowired
    private AnimalParentescoRepository animalParentescoRepository;


    @Autowired
    public AnimalParentescoServiceImpl(AnimalParentescoRepository animalParentescoRepository) {
        this.animalParentescoRepository = animalParentescoRepository;
    }

    @Override
    public List<AnimalParentesco> findAll() {
        return animalParentescoRepository.findAll();
    }

    @Override
    public Optional<AnimalParentesco> findById(String registro) {
        return animalParentescoRepository.findById(Long.valueOf(registro));
    }

    @Override
    @Transactional
    public AnimalParentesco save(AnimalParentesco animalParentesco) {
        return animalParentescoRepository.save(animalParentesco);
    }

    @Override
    @Transactional
    public void deleteById(String registro) {
        animalParentescoRepository.deleteById(Long.valueOf(registro));
    }

    @Override
    public Animal buscarParentesco(Animal animal) {
        return null;
    }
   
    public AnimalParentesco createAnimalParentescoFromSiscapri(String registro, String url) throws Exception {
      
        url = "https://siscapri.abccaprinos.com.br/x.php?m=siscapri.genealogia&site=siscapri#";

        Document doc = Jsoup.connect(url).get();

        Parentesco parentesco = new Parentesco();

        // Extrair os dados do HTML
        parentesco.setPAI(getRegistroParente(doc, "Pai"));
        parentesco.setMAE(getRegistroParente(doc, "Mãe"));
        parentesco.setAVO_MATERNO(getRegistroParente(doc, "Avó Materna"));
        parentesco.setAVOH_MATERNA(getRegistroParente(doc, "Avó Materno"));
        parentesco.setAVOH_PATERNO(getRegistroParente(doc, "Avó Paterno"));
        parentesco.setAVOU_PATERNA(getRegistroParente(doc, "Avó Paterna"));
        parentesco.setBISAVO_MATERNO(getRegistroParente(doc, "Bisavó Materna"));
        parentesco.setBISAVO_MATERNA(getRegistroParente(doc, "Bisavó Materno"));
        parentesco.setBISAVO_PATERNO(getRegistroParente(doc, "Bisavô Paterno"));
        parentesco.setBISAVO_PATERNA(getRegistroParente(doc, "Bisavô Paterna"));

        // Criar AnimalParentesco com o Parentesco preenchido
        AnimalParentesco animalParentesco = new AnimalParentesco();
        animalParentesco.setRegistro(registro);
        animalParentesco.setParentesco(parentesco);

        // Salvar e retornar o objeto
        return animalParentescoRepository.save(animalParentesco);
    }

    private int getRegistroParente(Document doc, String parentesco) {
        Element element = doc.select("div:contains(" + parentesco + ")").first();
        if (element != null) {
            String registro = element.select("Registro").text();
            return Integer.parseInt(registro);
        }
        return 0; // ou algum valor padrão caso não encontre
    }
    
}
