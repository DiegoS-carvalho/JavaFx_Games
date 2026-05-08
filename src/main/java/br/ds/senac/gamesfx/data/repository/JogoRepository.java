package br.ds.senac.gamesfx.data.repository;

import br.ds.senac.gamesfx.model.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//mock fonte dados fake

public class JogoRepository {
    public ObservableList<Jogo> getJogos(){



        ObservableList<Jogo> listaJogos = FXCollections
                .observableArrayList(
                        new Jogo(1,"Lego Batman", "Xbox 360, Ps3"),
                        new Jogo(2,"Lego Marvel Super-Heros", "Xbox 360, Ps3"),
                        new Jogo(3,"Lego Jurassic World", "Xbox 360, Ps3")
                        );



        return listaJogos;
    }
}
