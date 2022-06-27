package gui;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sprava.Kraj;
import sprava.Obec;
import sprava.Pozice;

/**
 *
 * @author Ahmad Akel
 */
public class DialogObce extends Stage {

    private Obec obec;
    private boolean edit;
    private Consumer<Obec> outputOperation;

    private TextField pscTextField;
    private TextField nazevObceTextField;
    private TextField pocetMTextField;
    private TextField pocetZTextField;
    private ComboBox<Kraj> krajComboBox;
    private ComboBox<Pozice> poziceComboBox;

    private static Kraj krajComboBoxValue;
    private static Pozice poziceComboBoxValue;

    public static DialogObce factoryDialogObce(Supplier<Obec> obecInput, Consumer<Obec> obecOutput) {
        return new DialogObce(obecInput, obecOutput);
    }

    private DialogObce(Supplier<Obec> obecInput, Consumer<Obec> obecOutput) {
        // input for editing
        edit = (obecInput != null);
        obec = (edit) ? obecInput.get() : new Obec();

        // output for new element
        outputOperation = obecOutput;

        // general
        setTitle("Dialog Obce");
        setWidth(350);
        setHeight(400);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.WINDOW_MODAL);
        setScene(createScene());

        // input
        if (edit) {
            set();
        }
    }

    private Scene createScene() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        int row = 0;

        pscTextField = addRowTextField(gridPane, "PSC", row++);
        nazevObceTextField = addRowTextField(gridPane, "Nazev Obce", row++);
        pocetMTextField = addRowTextField(gridPane, "Pocet Muzu", row++);
        pocetZTextField = addRowTextField(gridPane, "Pocet Zen", row++);
        krajComboBox = addRowComboBox(gridPane, "Kraj", row++, Kraj.values());
        poziceComboBox = addRowComboBox(gridPane, "Pozice", row++, Pozice.values());

        Button ulozButton = new Button("Ulož");
        ulozButton.setOnAction(e -> {
            update();

            outputOperation.accept(obec);
            close();
        });
        gridPane.add(ulozButton, 1, ++row);

        root.getChildren().addAll(gridPane);
        Scene scene = new Scene(root);
        return scene;
    }

    private static TextField addRowTextField(GridPane gridPane, String labelName, int row) {
        gridPane.add(new Label(labelName), 0, row);
        TextField textField = new TextField();
        gridPane.add(textField, 1, row);
        return textField;
    }

    private static <T> ComboBox<T> addRowComboBox(GridPane gridPane, String labelName, int row, Enum[] enumList) {
        gridPane.add(new Label(labelName), 0, row);
        ComboBox<T> comboBox = new ComboBox(FXCollections.observableList(Arrays.asList(enumList)));
        gridPane.add(comboBox, 1, row);
        return comboBox;
    }

    private void update() {
        try {
            obec.setPSC(Integer.valueOf(pscTextField.getText()));
            obec.setNazevObce(nazevObceTextField.getText());
            obec.setPocetMuzu(Integer.valueOf(pocetMTextField.getText()));
            obec.setPocetZen(Integer.valueOf(pocetZTextField.getText()));
            obec.setCelkem(obec.getPocetMuzu() + obec.getPocetZen());
            krajComboBoxValue = krajComboBox.getValue();
            poziceComboBoxValue = poziceComboBox.getValue();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Neco je Prazdne!").showAndWait();
        }
    }

    private void set() {
        pscTextField.setText(String.valueOf(obec.getPSC()));
        nazevObceTextField.setText(obec.getNazevObce());
        pocetMTextField.setText(String.valueOf(obec.getPocetMuzu()));
        pocetZTextField.setText(String.valueOf(obec.getPocetZen()));
        //krajComboBox.setValue(obec.getKraj());
        //poziceComboBox.setValue(obec.getPozice());
    }

    public static Kraj getKrajComboBoxValue() {
        return krajComboBoxValue;
    }

    public static Pozice getPoziceComboBoxValue() {
        return poziceComboBoxValue;
    }

}
