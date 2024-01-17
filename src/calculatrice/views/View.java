package calculatrice.views;

import com.sun.javafx.application.PlatformImpl;

import calculatrice.ctrl.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Classe représentant la vue de l'application MVC "Calculatrice" du module
 * D400.
 *
 * @author <a href="mailto:mario.ramalho@edufr.ch">Mario Ramalho</a>
 * @since 09.12.2023
 * @version 1.0.0
 */
public class View implements Initializable {

    @FXML
    private TextField inputA;

    @FXML
    private TextField inputB;

    @FXML
    private Label label;

    @FXML
    private RadioButton typeEntier;

    private Controller controller;
    private final String fxml;

    /**
     * Le constructeur de la classe View. Toujours initialiser TOUS les attributs !
     * 
     * @param controller le contrôleur de la vue
     */
    public View(Controller controller) {
        // Si vous avez une erreur "Location is not set" c'est que ce chemin est faux.
        fxml = "/calculatrice/views/view.fxml";
        this.controller = controller;
    }

    /**
     * Méthode de démarrage de la vue.
     */
    public void start() {

        Platform.startup(() -> {
            try {
                Stage mainStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));
                fxmlLoader.setControllerFactory(type -> {
                    return this;
                });
                Parent root = (Parent) fxmlLoader.load();
                Scene principalScene = new Scene(root);
                mainStage.setScene(principalScene);
                mainStage.setTitle("D08 - Calculatrice");
                mainStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
                Platform.exit();
            }
        });

    }

    /**
     * Méthode appelée par JavaFX lorsque la vue est initialisée.
     * Par défaut les champs de saisie sont initialisés avec les valeurs 2 et 5.
     *
     * @param url l'URL de la vue
     * @param rb les ressources de la vue
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputA.setText("2");
        inputB.setText("5");
    }

    /**
     * Méthode appelée par la vue lorsque l'utilisateur clique sur le bouton "+".
     * Cette méthode appelle le contrôleur pour effectuer l'opération.
     *
     * @param event l'événement de clic sur le bouton "+"
     */
    @FXML
    void handlePlusAction(ActionEvent event) {
        controller.additionne(inputA.getText(), inputB.getText(), typeEntier.isSelected());
    }

    /**
     * Méthode appelée par la vue lorsque l'utilisateur clique sur le bouton "-".
     * Cette méthode appelle le contrôleur pour effectuer l'opération.
     *
     * @param event l'événement de clic sur le bouton "-"
     */
    @FXML
    void handleMinusAction(ActionEvent event) {
        controller.soustrait(inputA.getText(), inputB.getText(), typeEntier.isSelected());
    }

    /**
     * Méthode appelée par le contrôleur pour afficher le résultat dans la vue.
     *
     * @param valeur la valeur à afficher
     */
    public void affiche(String valeur) {
        label.setText(valeur);
    }

}
