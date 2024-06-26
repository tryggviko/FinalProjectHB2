package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AskrifandiController {
    @FXML
    private TextField userName;

    @FXML
    private Label labelLogIn;


    //aðferðin frá SceneSwitcher
    SceneSwitcher sceneSwitcher = new SceneSwitcher();


    public void cancelLogin(ActionEvent event) {
        try {
            sceneSwitcher.switchScene(event, "home-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserName(){
        try {
            return userName.getText();
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    public void logIn(ActionEvent event){
        if(getUserName() != null){
            try {
                HomeController homeController = sceneSwitcher.switchSceneLogIn(event, "home-view.fxml");
                homeController.setUserName(getUserName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            labelLogIn.setText("No numbers");
    }

}
