package br.ds.senac.gamesfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TelaPrincipal extends Application {
    private  static final  String COR_PADRAO = "#6E6A5A;"+"-fx-text-fill: black;"+
            "-fx-border-color: #C9A227;" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;"+
            "-fx-cursor: hand";

    private  static final  String COR_HOVER = "#C2B98A;"+"-fx-text-fill: black;"+
            "-fx-border-color: #C9A227;" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;"+
            "-fx-cursor: hand";


    @Override
    public void start(Stage stage) throws Exception {

        BorderPane raiz = new BorderPane();


        VBox painelLateral = new VBox();
        painelLateral.setSpacing(20);
        painelLateral.setPrefWidth(150);
        painelLateral.setPadding(new Insets(10));

        //===========estilos da pagina=================

        painelLateral.setStyle("-fx-background-color: #4A4C4F;"+  "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.3, 2, 0)");
        raiz.setStyle("-fx-background-color: #2F3336;");

        // ================Botões=========================

        Button btnJogos = criarBotaoMenu("Jogos");

        Button btnPlataformas = criarBotaoMenu("Plataformas");

        Button btnEstudios = criarBotaoMenu("Estudios");

        Button btnHome = criarBotaoMenu("Home");
        aplicarEfeitoHover(btnJogos,btnHome,btnEstudios,btnPlataformas);
        //======================Vincular botão ao painel lateral=========================

        painelLateral.getChildren().addAll(btnHome,btnJogos,btnPlataformas,btnEstudios);

        //===========vinculando o painel lateral a esquerda da "raiz"====================

        raiz.setLeft(painelLateral);

        //======================Criando a cena da apresentação e o customizando======================================

        Scene cena = new Scene(raiz,1200,800);
//        stage.setResizable(false);
        stage.setTitle("Sistema de Gestão de Jogos V1.0");
        stage.setMaximized(true);


        //=====================Atribuindo a cena ao stage(palco)=====================

        stage.setScene(cena);
        stage.show();

        //====================Enfeitando o site==================




    }
    private Button criarBotaoMenu(String textoDoBotao){
       Button button = new Button(textoDoBotao);
       button.setPadding(new Insets(5));
       button.setPrefWidth(Double.MAX_VALUE);


               return button;
   }
   private void aplicarEfeitoHover(Button... botoes){
        for (Button button : botoes){
            button.setStyle("-fx-background-Color: " + COR_PADRAO);

            //Ao entrar no botão-> muda de cor
            button.setOnMouseEntered(e -> {
                button.setStyle("-fx-background-Color: " + COR_HOVER);});

            //Saiu do botao volta a cor
            button.setOnMouseExited(e ->{
                button.setStyle("-fx-background-Color: " + COR_PADRAO);});
            };
        }
   }

