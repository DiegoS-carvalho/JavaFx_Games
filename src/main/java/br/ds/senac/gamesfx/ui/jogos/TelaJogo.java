package br.ds.senac.gamesfx.ui.jogos;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaJogo {
    public void criarTela(Stage stagePai){
        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setMaxWidth(500);
        stage.setHeight(500);
        stage.setTitle("Cadastro de Jogo");

        stage.showAndWait();







    }
}
