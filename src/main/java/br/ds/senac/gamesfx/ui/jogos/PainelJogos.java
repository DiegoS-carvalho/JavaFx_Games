package br.ds.senac.gamesfx.ui.jogos;

import br.ds.senac.gamesfx.data.repository.JogoRepository;
import br.ds.senac.gamesfx.model.Jogo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import javax.swing.*;

public class PainelJogos {
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




    private Stage stage;

    public PainelJogos(Stage stage){
        this.stage = stage;
    }
    public VBox criarPainelJogos(){
        VBox painelJogos = new VBox();
        painelJogos.setPadding(new Insets(15,20,20,20));
        painelJogos.setStyle("-fx-background-color:  #2F3336;");
        //cabeçalho
        Label lblTitulo = new Label("Listagem de jogos");
        lblTitulo.setStyle( "-fx-font-size: 36; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill:#C2B98A;");


        //Tabela com a lista de jogos

        TableView<Jogo> tabelaJogos = new TableView<Jogo>();
        //ajustar espaço da tabela
        VBox.setVgrow(tabelaJogos, Priority.ALWAYS);


        //Colunas da Tabela (id, Titulo, Plataforma)

        TableColumn<Jogo, Integer> colunaId = new TableColumn("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setStyle("-fx-text-fill:#D8C79A;");
        colunaId.setPrefWidth(50);
        colunaId.setMaxWidth(50);
        colunaId.setResizable(false);

        TableColumn<Jogo, String> colunaTitulo = new TableColumn("TÍTULO");
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaTitulo.setStyle("-fx-text-fill:#D8C79A;");
        colunaTitulo.setPrefWidth(400);
        colunaTitulo.setMaxWidth(400);
        colunaTitulo.setResizable(false);

        TableColumn<Jogo, String> colunaPlataforma = new TableColumn("PLATAFORMA");
        colunaPlataforma.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
        colunaPlataforma.setStyle("-fx-text-fill:#D8C79A;");
        colunaPlataforma.setPrefWidth(200);
        colunaPlataforma.setMaxWidth(200);
        colunaPlataforma.setResizable(false);



    //import dos dados da tabela================================================================\\
        JogoRepository repository = new JogoRepository();
        tabelaJogos.setItems(repository.getJogos());

    //========================================================================================\\


        tabelaJogos.getColumns().addAll(colunaId,colunaTitulo, colunaPlataforma);
        tabelaJogos.setStyle(

                        "-fx-background-color: #23272A;" +
                                "-fx-control-inner-background: #23272A;" +
                                "-fx-table-cell-border-color: transparent;"

);


        HBox botoes = new HBox(30);
        botoes.setPadding(new Insets(20,0,0,20));
        botoes.setAlignment(Pos.BASELINE_RIGHT);

        Button btnAdd =  criarBotao("Adicionar", "/imagens/save.png");

        btnAdd.setOnAction(e->{
            TelaJogo telaJogo = new TelaJogo();
            telaJogo.criarTela(stage);
            tabelaJogos.setItems(repository.getJogos());
        });



        Button btnView =  criarBotao("Ver", "/imagens/view.png");



        Button btnEdit =  criarBotao("Editar", "/imagens/edit.png");



        Button btnApagar =  criarBotao("Deletar", "/imagens/trash1.png");
        btnApagar.setOnAction(e-> {
                    Jogo jogoExcluir = tabelaJogos.getSelectionModel().getSelectedItem();
                    int resultado = repository.excluir(jogoExcluir.getId());
                    if (resultado > 0) {
            JOptionPane.showMessageDialog(null,"Jogo excluido com sucesso");




                }
            });


    botoes.getChildren().addAll(btnAdd,btnView,btnEdit,btnApagar);
        //========================================================================================\\
        painelJogos.getChildren().addAll(lblTitulo,new Separator(), tabelaJogos,botoes);
        return painelJogos;
    }
        private Button criarBotao(String textoBotao, String urlImg){
            Image image = new Image(getClass().getResourceAsStream(urlImg));
            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(40);
            imageView.setFitHeight(40);

            Button botao = new Button();
            botao.setText(textoBotao);
            botao.setGraphic(imageView);
            botao.setPrefHeight(80);
            botao.setPrefWidth(140);
            botao.setStyle(" -fx-background-color: #3B4045; -fx-text-fill: #D8C79A;-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #D8C79A; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 14;");
            botao.setContentDisplay(ContentDisplay.TOP);

            return botao;
        }

}
