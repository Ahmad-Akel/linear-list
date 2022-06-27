package gui;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kolekce.AbstrDoubleList;
import sprava.Kraj;
import sprava.Obec;
import sprava.Sprava;
import sprava.SpravaObyvatele;
import static sprava.SpravaObyvatele.kraje;

/**
 *
 * @author Ahmad Akel
 */
public class ProgObyvatele extends Application {

    private Sprava spravce;
    private Predicate<? super Obec> filter = obec -> obec != null;
    private Consumer<String> error = (error) -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setContentText(error);
        alert.showAndWait();
    };

    private ListView<Obec> listView;
    private ObservableList<Obec> items = FXCollections.observableArrayList();
    private Label prumerLabel;

    private static final int SCENE_HEIGHT = 670;
    private static final int SCENE_WIDTH = 1270;

    public ProgObyvatele() {
        spravce = SpravaObyvatele.vytvorSpravce(AbstrDoubleList<Obec>::new);
        prumerLabel = new Label(String.valueOf(0));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        listView = createListView();

        ControlPanelHBox controlPanelHBox = createControlPanelHBoxCommands();
        ControlPanelVBox controlPanelVBox = createControlPanelVBoxCommands();

        root.getChildren().addAll(listView, controlPanelHBox, controlPanelVBox);

        primaryStage.setTitle("Sprava Obyvatele");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ListView<Obec> createListView() {
        listView = new ListView<>();
        listView.setPrefSize(SCENE_WIDTH - 170, SCENE_HEIGHT - 110);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectedItemProperty()
                .addListener((changed, oldVal, newVal) -> System.out.println(newVal));
        listView.setTranslateX(SCENE_HEIGHT - 655);
        listView.setTranslateY(SCENE_WIDTH - 1255);

        if (true) {
            listView.setCellFactory((cell) -> {
                return new ListCell<Obec>() {
                    @Override
                    protected void updateItem(Obec item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty && item != null) {
                            setText(item.toString());
                        } else {
                            setText(null);
                        }
                    }
                };
            });
        }
        return listView;
    }

    private ControlPanelHBox createControlPanelHBoxCommands() {
        ControlPanelHBox controlPanelHBox = new ControlPanelHBox();
        controlPanelHBox.setTranslateX(15);
        controlPanelHBox.setTranslateY(575);
        controlPanelHBox.setPrefSize(1255, 55);

        controlPanelHBox.addLabel("Zobraz");
        controlPanelHBox.addComboBox(Kraj.values(), zobrazHandler);

        controlPanelHBox.addButton("Zobraz Vše", zobrazVseHandler);

        controlPanelHBox.addLabel("Zobraz Obce nad Průměr");
        controlPanelHBox.addComboBox(Kraj.values(), zobrazobceNadPrumerHandler);

        controlPanelHBox.addLabel("Zruš");
        controlPanelHBox.addComboBox(Kraj.values(), zrusHandler);

        controlPanelHBox.addLabel("Zajišti Průměr");
        controlPanelHBox.addComboBox(Kraj.values(), zajstiPrumerHandler);
        controlPanelHBox.addLabel(prumerLabel);

        return controlPanelHBox;
    }

    private ControlPanelVBox createControlPanelVBoxCommands() {
        ControlPanelVBox controlPanelVBox = new ControlPanelVBox();
        controlPanelVBox.setTranslateX(1130);
        controlPanelVBox.setTranslateY(15);
        controlPanelVBox.setPrefSize(140, 560);

        controlPanelVBox.addButton("Import Data", importHandler);
        controlPanelVBox.addButton("Generuj", generujHandler);
        controlPanelVBox.addButton("Přidej", pridejHandler);
        controlPanelVBox.addButton("Odeběr", odeberHandler);
        controlPanelVBox.addButton("Uložit", ulozitHandler);
        controlPanelVBox.addButton("Načti", nactiHandler);
        controlPanelVBox.addButton("Zálohuj", zalohujHandler);
        controlPanelVBox.addButton("Obnov", obnovHandler);
        controlPanelVBox.addButton("Zruš Vše", zrusVseHandler);
        return controlPanelVBox;
    }

    private void renewListView() {
        listView.getItems().clear();
        SpravaObyvatele.kraje.stream().filter(seznam -> !seznam.jePrazdny())
                .forEach((seznam) -> {
                    seznam.stream().filter(obec -> obec != null).forEach(listView.getItems()::add);
                });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private final EventHandler<ActionEvent> importHandler = event -> {
        spravce.importData("kraje.csv");
        renewListView();
    };

    private final EventHandler<ActionEvent> generujHandler = event -> {
    };

    private final EventHandler<ActionEvent> pridejHandler = event -> {
        Stage dialog = DialogObce.factoryDialogObce(null, (obec) -> {
            try {
                spravce.vlozObec(obec, DialogObce.getPoziceComboBoxValue(), DialogObce.getKrajComboBoxValue());
                renewListView();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Překročena Kapacita Seznamu").showAndWait();
            }
        });
        dialog.showAndWait();
        renewListView();
    };

    private final EventHandler<ActionEvent> odeberHandler = event -> {
        
    };

    private final EventHandler<ActionEvent> ulozitHandler = event -> {
    };

    private final EventHandler<ActionEvent> nactiHandler = event -> {
    };

    private final EventHandler<ActionEvent> zalohujHandler = event -> {
    };

    private EventHandler<ActionEvent> obnovHandler = event -> {
    };

    private EventHandler<ActionEvent> zrusVseHandler = event -> {

    };

    private final EventHandler<ActionEvent> zrusHandler = event -> {
        Kraj kraj = ((ComboBox<Kraj>) event.getSource()).getValue();
        if (kraj == null) {
            return;
        }
        spravce.zrus(kraj);
        renewListView();
    };

    private final EventHandler<ActionEvent> zobrazHandler = (ActionEvent event) -> {
        Kraj typFilter = ((ComboBox<Kraj>) event.getSource()).getValue();
        if (typFilter == null) {
            return;
        }
        spravce.zobrazObce(typFilter);
        listView.getItems().clear();
        SpravaObyvatele.kraje.get(typFilter.ordinal())
                .stream().filter(filter)
                .forEach(listView.getItems()::add);
        //((ComboBox<Kraj>) event.getSource()).getSelectionModel().clearSelection();
    };

    private final EventHandler<ActionEvent> zobrazVseHandler = event -> {
        renewListView();
    };

    private final EventHandler<ActionEvent> zajstiPrumerHandler = event -> {
        Kraj kraj = ((ComboBox<Kraj>) event.getSource()).getValue();
        if (kraj == null) {
            return;
        }
        float prumer = spravce.zjistiPrumer(kraj);
        prumerLabel.setText(String.valueOf(prumer));
    };

    private final EventHandler<ActionEvent> zobrazobceNadPrumerHandler = event -> {
        Kraj kraj = ((ComboBox<Kraj>) event.getSource()).getValue();
        listView.getItems().clear();
        spravce.zobrazObceNadPrumer(kraj);
        Iterator<Obec> it = kraje.get(kraj.ordinal()).iterator();
        while (it.hasNext()) {
            if (it.next().getCelkem() > spravce.zjistiPrumer(kraj)) {
                listView.getItems().add(it.next());
            }
        }
    };
}
