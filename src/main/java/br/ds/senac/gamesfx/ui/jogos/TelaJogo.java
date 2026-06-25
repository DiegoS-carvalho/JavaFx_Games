package br.ds.senac.gamesfx.ui.jogos;

import br.ds.senac.gamesfx.data.repository.EstudioRepository;
import br.ds.senac.gamesfx.data.repository.JogoRepository;
import br.ds.senac.gamesfx.data.repository.PlataformaRepository;
import br.ds.senac.gamesfx.model.Estudio;
import br.ds.senac.gamesfx.model.Jogo;
import br.ds.senac.gamesfx.model.Plataforma;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Optional;

public class TelaJogo {

    private TextField tfId = new TextField();
    private TextField tfTitulo = new TextField();
    private TextField tfValor = new TextField();
    private ComboBox<Plataforma> comboPlataforma = new ComboBox<>();
    private ComboBox<Estudio> comboEstudio = new ComboBox<>();
    private DatePicker dpDataLancamento = new DatePicker();
    private CheckBox cbFinalizado = new CheckBox("Finalizado");
    EstudioRepository repoEstudio = new EstudioRepository();
    PlataformaRepository repoPlataforma = new PlataformaRepository();
    private String operacao;


public  TelaJogo(){}

    public TelaJogo(Jogo jogo, String operacao){
        this.operacao = operacao;
    tfId.setText(String.valueOf(jogo.getId()));
    tfTitulo.setText(jogo.getTitulo());
    tfValor.setText(String.valueOf(jogo.getPreco()));
    comboPlataforma.setItems(repoPlataforma.getPlataformas());
    comboEstudio.setItems(repoEstudio.getEstudios());
    dpDataLancamento.setValue(jogo.getDataLancamento());
    cbFinalizado.setSelected(jogo.isFinalizado());
    }
    public TelaJogo(String operacao){this.operacao = operacao;}
    public void criarTela(Stage stagePai){
        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setMaxWidth(500);
        stage.setHeight(500);
        if(operacao.equals("cadastrar")){
            stage.setTitle("Cadastro de Jogo");}
        else if (operacao.equals("editar")) {
            stage.setTitle("Edição de Jogo");
        } else if (operacao.equals("visualizar")) {
            stage.setTitle("Visualização de Jogo");
        }

        BorderPane raiz = new BorderPane();
        raiz.setTop(criarPainelTitulo());
        raiz.setCenter(criarFormulario());
        raiz.setBottom(criarRodape(stage));
        Scene cena = new Scene(raiz,500,550);

        stage.setHeight(550);
        stage.setWidth(500);
        stage.setResizable(false);
        stage.setScene(cena);
        stage.showAndWait();

    }
    public HBox criarPainelTitulo(){

        HBox painelTitulo = new HBox();

        painelTitulo.setPadding(new Insets (20,0,20,20));
        painelTitulo.setStyle("-fx-background-color:#2F3336; ");
//        painelTitulo.set

        painelTitulo.setAlignment(Pos.CENTER_LEFT);
        ImageView imageView = null;
        Label lblTitulo = null;
        switch (operacao) {
            case "cadastrar":
                imageView = new ImageView(new Image(getClass().getResourceAsStream("/imagens/save.png")));
                lblTitulo = new Label("Cadastro de Jogos");
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                lblTitulo.setStyle( "-fx-font-size: 28; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill:#C2B98A;");
                break;

            case "editar":
                imageView = new ImageView(new Image(getClass().getResourceAsStream("/imagens/edit.png")));
                lblTitulo = new Label("Edição de Jogo");
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                lblTitulo.setStyle( "-fx-font-size: 28; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill:#C2B98A;");
                break;

            case "visualizar":
                imageView = new ImageView(new Image(getClass().getResourceAsStream("/imagens/view.png")));
                lblTitulo = new Label("Visualização de Jogos");
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                lblTitulo.setStyle( "-fx-font-size: 28; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill:#C2B98A;");
                break;
        }


        painelTitulo.getChildren().addAll(imageView,lblTitulo);

        return painelTitulo;
    }
    private VBox criarFormulario(){




        VBox formulario = new VBox();
        formulario.setPadding(new Insets(10));
//====================================================================================================

        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(7);


        //Criar os componentes para a grid

        Label lblid = new Label("ID: ");
//        tfId = new TextField();
        tfId.setEditable(false);
        tfId.setDisable(true);

        Label lblTitulo = new Label("Título: ");
//        tfTitulo = new TextField();
        tfTitulo.setPromptText("Ex. Lego Batman II");

        Label lblPlataformas = new Label("Plataforma: ");
        comboPlataforma.setItems(repoPlataforma.getPlataformas());

        Label lblEstudios = new Label("Estudio: ");
        comboEstudio.setItems(repoEstudio.getEstudios());

        Label lblValor = new Label("Valor: ");
//        tfValor = new TextField();
        tfValor.setPromptText("Ex. 9,99");

        Label lblLancamento = new Label("Data de Lançamento: ");
//        dpDataLancamento = new DatePicker(LocalDate.now());



        //adicionar na grid
        gridFormulario.add(lblid,0,0);
        gridFormulario.add(tfId,1,0);
        gridFormulario.add(lblTitulo,0,1);
        gridFormulario.add(tfTitulo,1,1);
        gridFormulario.add(lblPlataformas,0,2);
        gridFormulario.add(comboPlataforma,1,2);
        gridFormulario.add(lblEstudios,0,3);
        gridFormulario.add(comboEstudio,1,3);
        gridFormulario.add(lblValor,0,4);
        gridFormulario.add(tfValor,1,4);
        gridFormulario.add(lblLancamento,0,5);
        gridFormulario.add(dpDataLancamento,1,5);
        gridFormulario.add(cbFinalizado,1,6);

        if (operacao.equals("visualizar")) {
            tfId.setDisable(true);
            tfTitulo.setDisable(true);
            tfValor.setDisable(true);
            comboPlataforma.setDisable(true);
            comboEstudio.setDisable(true);
            dpDataLancamento.setDisable(true);
            cbFinalizado.setDisable(true);

            formulario.getChildren().addAll(gridFormulario);
            return  formulario;
        } else if (operacao.equals("") || operacao == null) {
            formulario.getChildren().addAll(gridFormulario);

            formulario.getChildren().addAll(gridFormulario);
            return  formulario;
        }

//====================================================================================================


        formulario.getChildren().addAll(gridFormulario);


        return  formulario;
    }
    private Button criarBotao(String textoBotao, String urlImg) {


        Image image = new Image(getClass().getResourceAsStream(urlImg));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(40);
        imageView.setFitHeight(40);


        Button botao = new Button();
        botao.setText(textoBotao);
        botao.setGraphic(imageView);
        botao.setPrefHeight(40);
        botao.setPrefWidth(120);
        botao.setStyle(" -fx-background-color: #3B4045; -fx-text-fill: #D8C79A;-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #D8C79A; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 14;");


        return botao;
    }
    private HBox criarRodape(Stage stage) {

        HBox rodape = new HBox();
        rodape.setPadding(new Insets(10, 5, 10, 10));
        rodape.setStyle("-fx-background-color:#6B6D70 ; ");

        Button btnSalvar = criarBotao("salvar", "/imagens/save.png");
        btnSalvar.setTooltip(new Tooltip("Salvar"));
        Button btnApagar = criarBotao("Apagar", "/imagens/trash1.png");
        btnApagar.setTooltip(new Tooltip("Apagar"));
        rodape.setAlignment(Pos.BASELINE_RIGHT);

        rodape.setSpacing(10);
        btnSalvar.setTooltip(new Tooltip("Salvar dados do jogo"));

        btnSalvar.setOnAction(evento -> {
            Jogo jogo = new Jogo();
            jogo.setTitulo(tfTitulo.getText());
            jogo.setPlataforma(comboPlataforma.getValue().getId());
            jogo.setEstudio(comboEstudio.getValue().getId());
            jogo.setDataLancamento(dpDataLancamento.getValue());

            jogo.setFinalizado(cbFinalizado.isSelected());
            jogo.setPreco(Double.parseDouble(tfValor.getText()));

            // Criar o repositório para enviar o jogo
            JogoRepository repository = new JogoRepository();
            if (tfId.getText().equals("")) {
                repository.salvar(jogo);

                Alert mensagemSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                mensagemSalvar.setTitle("cadastro de jogos");
                mensagemSalvar.setHeaderText("o jogo foi gravado com sucesso");
                mensagemSalvar.setContentText("deseja cadastrar outro jogo?");

                Optional<ButtonType> escolhaCadastra = mensagemSalvar.showAndWait();

                if (escolhaCadastra.get() == ButtonType.OK) {
                    limparCampos();

                } else {
                    stage.close();
                }


            } else {
                jogo.setId(Integer.parseInt(tfId.getText()));
                repository.editar(jogo);

                Alert mensagemEditar = new Alert(Alert.AlertType.INFORMATION);
                mensagemEditar.setTitle("Editar Jogo");
                mensagemEditar.setHeaderText("o jogo foi editado com sucesso");
                mensagemEditar.showAndWait();

                stage.close();

            }
            limparCampos();
        });
        if (operacao.equals("visualizar")) {

            rodape.setPrefHeight(70); // ocupa altura
            rodape.setPrefWidth(200);  // ocupa largura

            return rodape;
        } else if (operacao.equals("") || operacao == null) {


            rodape.getChildren().addAll(btnSalvar, btnApagar);
            return rodape;
        }
        rodape.getChildren().addAll(btnSalvar, btnApagar);
        return rodape;
    }
    private void limparCampos() {

        tfTitulo.clear();
        tfValor.clear();
        comboEstudio.setItems(null);
        comboPlataforma.setItems(null);
        cbFinalizado.setSelected(false);
        dpDataLancamento.setValue(LocalDate.now());
        tfTitulo.requestFocus();
    }


}
