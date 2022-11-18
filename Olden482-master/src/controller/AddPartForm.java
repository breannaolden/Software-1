/***
 * Created by Breanna Olden
 * Student ID: 001532324
 * C482 Software 1
 *
 */

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartForm implements Initializable {

    public Button cancelAddPartButton;
    public Button partSaveButton;

    @FXML
    private RadioButton partInHouseRBtn;
    @FXML
    private ToggleGroup addPartToggle;
    @FXML
    private RadioButton partOutsourcedRBtn;
    @FXML
    private Label partSourceTxt;
    @FXML
    private TextField partIdTxt;
    @FXML
    private TextField partNameTxt;
    @FXML
    private TextField partInventoryTxt;
    @FXML
    private TextField partPriceTxt;
    @FXML
    private TextField partMaxTxt;
    @FXML
    private TextField partMachineIDTxt;
    @FXML
    private TextField partMinTxt;
    public static int partIdNum;



    static int partIDNum = 1;

    /***
     * @param actionEvent
     * Function: saves information about new part to partsTable
     * Saves machineId if source is in house (known by radio button
     * selection), saves companyName if outsourced part.
     *
     * If part successfully saved, navigates user back to home screen.
     *
     * FUTURE ENHANCEMENT
     * Figure out how to auto increment id.
     *
     *
     * @throws IOException
     */
    public void onPartSaveClick(ActionEvent actionEvent) throws IOException {

        if (partInHouseRBtn.isSelected()) {

            int partId = Integer.parseInt(partIdTxt.getText());
            String partName = partNameTxt.getText();
            int partStock = Integer.parseInt(partInventoryTxt.getText());
            double partPrice = Double.parseDouble(partPriceTxt.getText());
            int partMax = Integer.parseInt(partMaxTxt.getText());
            int partMin = Integer.parseInt(partMinTxt.getText());
            int machineId = Integer.parseInt(partMachineIDTxt.getText());
            boolean sourceInHouse;

            //Assign boolean value based on if radio button is selected
            sourceInHouse = partInHouseRBtn.isSelected();

            Inventory.addPart(new InHouse(partId, partName, partStock, partPrice, partMin, partMax, machineId));

            // close window after save and return to main page
            Parent root = FXMLLoader.load(getClass().getResource("/view/FirstScreen.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Home Screen");
            stage.setScene(new Scene(root));
            stage.show();
        }
        if (partOutsourcedRBtn.isSelected()) {
            int partId = Integer.parseInt(partIdTxt.getText());
            String partName = partNameTxt.getText();
            int partStock = Integer.parseInt(partInventoryTxt.getText());
            double partPrice = Double.parseDouble(partPriceTxt.getText());
            int partMax = Integer.parseInt(partMaxTxt.getText());
            int partMin = Integer.parseInt(partMinTxt.getText());
            String companyName = partMachineIDTxt.getText();
            boolean sourceOutSourced;

            //Assign boolean value based on if radio button is selected
            sourceOutSourced = partOutsourcedRBtn.isSelected();

            Inventory.addPart(new OutSourced(partId, partName, partStock, partPrice, partMin, partMax, companyName));

            // close window after save and return to main page
            Parent root = FXMLLoader.load(getClass().getResource("/view/FirstScreen.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Home Screen");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }



    /***
     * @param actionEvent
     * Function: changes text based on radio button selection.
     * If select source as in house, will show "Machine ID"
     *
     * @throws IOException
     * function can potentially throw exception, handles without
     * try-catch block
     */
    public void onInHouse(ActionEvent actionEvent) throws IOException {
        partSourceTxt.setText("Machine ID");
        partMachineIDTxt.setPromptText("Ex.111");
    }


    /***
     * @param actionEvent
     * Function: changes text based on radio button selection.
     * If select source as outsourced, will show "company name"
     *
     */
    public void onOutsourced(ActionEvent actionEvent) {
        partSourceTxt.setText("Company Name");
        partMachineIDTxt.setPromptText("Ex. Smooth Tire Co.");
    }


    /***
     * @param actionEvent
     * Function: When cancel is clicked, return to home screen
     *
     * @throws IOException
     */
    public void cancelAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FirstScreen.fxml"));
        Stage stage = (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Home Screen");
        stage.setScene(new Scene(root));
        stage.show();

    }

    /***
     *
     * @param url
     * @param resourceBundle
     * Initialize controller
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //partInHouseRBtn.setSelected(true);

    }

}
