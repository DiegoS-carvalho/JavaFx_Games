package br.ds.senac.gamesfx.ui.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PainelHome {

    public VBox criarPainelHome() {

        VBox painelPrincipal = new VBox();
        painelPrincipal.setAlignment(Pos.TOP_CENTER);
        painelPrincipal.setPadding(new Insets(15, 20, 20, 20));
        painelPrincipal.prefWidth(300);
        painelPrincipal.setStyle("-fx-background-color: #2F3336;");

        VBox painelTitulo = new VBox();
        painelTitulo.setAlignment(Pos.TOP_LEFT);
//        painelTitulo.setStyle("-fx-background-Color: white");

        Label lblTitulo = new Label("Seja Bem-Vindo!");
        lblTitulo.setAlignment(Pos.TOP_LEFT);
        lblTitulo.setStyle(
                "-fx-font-size: 36; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill:#C2B98A;"
        );

        painelTitulo.getChildren().addAll(
                lblTitulo,
                new Separator()
        );
        painelTitulo.setAlignment(Pos.CENTER_LEFT);

        VBox painelCentral = new VBox();
        // Imagem da aplicação

        Image imgLogo = new Image(
                getClass().getResourceAsStream("/Imagens/dino1.png")
        );
        ImageView ivLogo = new ImageView(imgLogo);

        VBox.setVgrow(painelCentral, Priority.ALWAYS);

        Label lblNomeApp = new Label("GameAdmin Pro");
        lblNomeApp.setStyle(
                "-fx-font-weight: bold; " +
                        "-fx-font-size: 36; " +
                        "-fx-text-fill: #C2B98A"
        );

        Label lblDisc = new Label("Software para gestão");
        lblDisc.setStyle(
                "-fx-font-weight: regular; " +
                        "-fx-font-size: 30; " +
                        "-fx-text-fill: #C2B98A"
        );

        painelCentral.setAlignment(Pos.CENTER);

//        painelCentral.setStyle("-fx-background-color: #C2B98A;");
//        painelCentral.setPrefHeight(165);
//        painelCentral.setPrefWidth(300);


        VBox painelContatos = new VBox(5);

        Label lblTituloEmail = new Label("E-mail para suporte: ");
        lblTituloEmail.setStyle("-fx-text-fill: #C2B98A;-fx-font-weight: bold; -fx-font-size: 18");

        Label lblEmail = new Label("RonaldoSauro@gmail.com ");
        lblEmail.setStyle("-fx-text-fill: #C2B98A;");

        Label lblTituloTelefone = new Label("Telefone para suporte: ");
        lblTituloTelefone.setStyle("-fx-text-fill: #C2B98A; -fx-font-weight: bold; -fx-font-size: 18");

        Label lblTelefone = new Label("65000-000 ");
        lblTelefone.setStyle("-fx-text-fill: #C2B98A;");

        painelContatos.setStyle("-fx-background-color: #4A4C4F;-fx-border-width: 2; -fx-border-color: #C2B98A; -fx-border-radius: 26; -fx-background-radius: 36; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0.2, 0, 2);");
        painelContatos.setMaxWidth(600);

        painelContatos.setPadding(new Insets(20));
        VBox.setMargin(painelContatos,new Insets(30,10,30,10));
        Label lblCreditos = new Label("Desenvolvido por Diego Carvalho - 2026");
        lblCreditos.setStyle("-fx-text-fill: #C2B98A; -fx-font-size: 18");


        painelContatos.getChildren().addAll(lblTituloEmail,lblEmail,lblTituloTelefone,lblTelefone);

        painelCentral.getChildren().addAll(ivLogo, lblNomeApp,lblDisc,painelContatos,lblCreditos);
        painelPrincipal.getChildren().addAll(painelTitulo, painelCentral);

        return painelPrincipal;
    }
}