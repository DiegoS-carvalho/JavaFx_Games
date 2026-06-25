package br.ds.senac.gamesfx;

import br.ds.senac.gamesfx.data.repository.JogoRepository;
import br.ds.senac.gamesfx.model.Jogo;
import javafx.application.Application;

import java.time.LocalDate;

public class Launcher {
    public static void main(String[] args) {
//        Application.launch(TelaPrincipal.class, args);
        Jogo jogo = new Jogo(0,"teste",1);

        jogo.setEstudio(1);
        jogo.setPreco(100.00);
        jogo.setDataLancamento(LocalDate.now());
        jogo.setFinalizado(true);

        JogoRepository repository = new JogoRepository();
        repository.salvar(jogo);
    }
}
