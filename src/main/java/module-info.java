module br.ds.senac.gamesfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens br.ds.senac.gamesfx to javafx.fxml;
    exports br.ds.senac.gamesfx;
}