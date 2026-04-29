package br.ds.senac.gamesfx.ui.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PainelHome {
    public VBox criarPainelHome(){
        VBox painelPrincipal = new VBox();
        painelPrincipal.setAlignment(Pos.TOP_CENTER);
        painelPrincipal.setPadding(new Insets(15,20,20,20));
        painelPrincipal.prefWidth(300);
        painelPrincipal.setStyle("-fx-background-color: #2F3336;");

        VBox painelTitulo = new VBox();
        painelTitulo.setAlignment(Pos.TOP_LEFT);
        painelTitulo.setStyle("-fx-background-Color: white");

        Label lblTitulo = new Label("Seja Bem-Vindo!");
        lblTitulo.setAlignment(Pos.TOP_LEFT);
        lblTitulo.setStyle("-fx-font-size: 26;-fx-font-weight: bold; -fx-text-fill:#C2B98A;" );

        painelTitulo.getChildren().addAll(lblTitulo, new Separator());
        //Imagem da aplicação

        Image imgLogo = new Image(getClass().getResourceAsStream("/Imagens/dino1.png"));
        ImageView ivLogo = new ImageView(imgLogo);
        Label lblNomeApp = new Label("GameAdmin Pro");
        lblNomeApp.setStyle("-fx-font-wieght: bold; -fx-font-size: 36; -fx-text-fill: #C2B98A ");
        Label lblDisc = new Label("GameAdmin Pro");
        lblDisc.setStyle("-fx-font-wieght: regular; -fx-font-size: 36; -fx-text-fill: #C2B98A ");
        VBox painelCentral = new VBox();

        painelCentral.setStyle("-fx-background-color: #C2B98A;");
        painelTitulo.setAlignment(Pos.CENTER);
        painelCentral.setPrefHeight(165);
        painelCentral.setPrefWidth(300);

        painelPrincipal.getChildren().addAll(painelTitulo,ivLogo,lblNomeApp,lblDisc,painelCentral);

        return painelPrincipal;
    }
}
