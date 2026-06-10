package br.ds.senac.gamesfx.ui.plataformas;

import br.ds.senac.gamesfx.data.repository.PlataformaRepository;
import br.ds.senac.gamesfx.model.Jogo;
import br.ds.senac.gamesfx.model.Plataforma;
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



public class PainelPlataformas {


    private Stage stage;
    
    public PainelPlataformas(Stage stage){this.stage = stage;}
    
    public VBox criarPainelPlataformas(){
        VBox painelPlataformas = new VBox();
        painelPlataformas.setPadding(new Insets(15,20,20,20));
        painelPlataformas.setStyle("-fx-background-color:  #2F3336;");
        Label lblTitulo = new Label("Listagem de Plataformas");
        lblTitulo.setStyle("-fx-font-size: 36; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill:#C2B98A;");
       //===============Tabela Plataformas=======================
        TableView<Plataforma> tabelaPlataformas = new TableView<>();
        //Arrumando o espaço da tabela.
        VBox.setVgrow(tabelaPlataformas, Priority.ALWAYS);
        
        
        // Colunas da tabela

        TableColumn<Plataforma, Integer> colunaId = new TableColumn("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setStyle("-fx-text-fill:#D8C79A;");
        colunaId.setPrefWidth(50);
        colunaId.setMaxWidth(50);
        colunaId.setResizable(false);

        TableColumn<Plataforma,String> colunaTitulo = new TableColumn<>("PLATAFORMA");
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("Plataforma"));        
        colunaTitulo.setStyle("-fx-text-fill:#D8C79A;");
        colunaTitulo.setPrefWidth(400);
        colunaTitulo.setMaxWidth(400);
        colunaTitulo.setResizable(false);



        TableColumn<Plataforma, String> colunaFabricante = new TableColumn("FABRICANTE");
        colunaFabricante.setCellValueFactory(new PropertyValueFactory<>("Fabricante"));
        colunaFabricante.setStyle("-fx-text-fill:#D8C79A;");
        colunaFabricante.setPrefWidth(200);
        colunaFabricante.setMaxWidth(200);
        colunaFabricante.setResizable(false);

//        TableColumn<Plataforma, String> colunaPlataforma = new TableColumn("PLATAFORMA");
//        colunaPlataforma.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
//        colunaPlataforma.setStyle("-fx-text-fill:#D8C79A;");
//        colunaPlataforma.setPrefWidth(200);
//        colunaPlataforma.setMaxWidth(200);
//        colunaPlataforma.setResizable(false);

        PlataformaRepository repository = new PlataformaRepository();
        tabelaPlataformas.setItems(repository.getPlataformas());

        tabelaPlataformas.getColumns().addAll(colunaId,colunaTitulo,colunaFabricante);
        tabelaPlataformas.setStyle(
                "-fx-background-color: #23272A;" +
                        "-fx-control-inner-background: #23272A;" +
                        "-fx-table-cell-border-color: transparent;"

        );




        HBox botoes = new HBox(30);
        botoes.setPadding(new Insets(20,0,0,20));
        botoes.setAlignment(Pos.BASELINE_RIGHT);

        Button btnAdd =  criarBotao("Adicionar", "/imagens/save.png");

        btnAdd.setOnAction(e->{

        });

        Button btnView =  criarBotao("Ver", "/imagens/view.png");

        Button btnEdit =  criarBotao("Editar", "/imagens/edit.png");
        btnEdit.setOnAction(e ->{




        });


        Button btnApagar =  criarBotao("Deletar", "/imagens/trash1.png");
        btnApagar.setOnAction(e-> {


        });


        botoes.getChildren().addAll(btnAdd,btnView,btnEdit,btnApagar);
        painelPlataformas.getChildren().addAll(lblTitulo,new Separator(), tabelaPlataformas,botoes);
        return  painelPlataformas;
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
