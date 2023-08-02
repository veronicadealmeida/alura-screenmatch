package edu.alura.screenmatch.controller;

import edu.alura.screenmatch.domain.filme.DadosCadastroFilme;
import edu.alura.screenmatch.domain.filme.Filme;
import edu.alura.screenmatch.domain.filme.FilmeRepsitory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepsitory repository;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(){
        return "filmes/formulario";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model){
        model.addAttribute("lista", repository.findAll());
        return "filmes/listagem";
    }

    @PostMapping
    public String cadastraFilme(DadosCadastroFilme dados) {
        var filme = new Filme(dados);
        repository.save(filme);

        return "redirect:/filmes";
    }

    @DeleteMapping
    public String removeFilme(Long id){
        repository.deleteById(id);
        return "redirect:/filmes";
    }
}
