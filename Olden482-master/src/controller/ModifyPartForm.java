/***
 * Created by Breanna Olden
 * Student ID: 001532324
 * C482 Software 1
 *
 */

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.AddPartForm.partIdNum;

public class ModifyPartForm implements Initializable {

    public Button cancelModifyPartButton;
    public Button saveModifyPartButton;
    public Label modifyPartSourceTxt;

    public TextField modifyPartIdTxt;
    public TextField modifyPartNameTxt;
    public TextField ModifyPartStockTxt;
    public TextField modifyPartPriceTxt;
    public TextField modifyPartMaxTxt;
    public TextField modifyPartMachineIDTxt;
    public TextField modifyPartMinTxt;
    public RadioButton partInHouseRBtn;
    public RadioButton modifyPartOutsourcedRBtn;
    public ToggleGroup mpToggle;

    int partId = partIdNum;
    String partName;
    double partPrice;
    int partStock;
    int partMax;
    int partMin;
    int machineId;
    String companyName;
    boolean partUpdated = false;
    int i;



    /***
     *
     * @param actionEvent Function: If the In House radio button is selected,
     *      * changes text to "Machine ID"
     */
    public void onModifyPartInHouse(ActionEvent actionEvent) {modifyPartSourceTxt.setText("Machine ID");
    }



    /***
     *
     * @param actionEvent Function: If the Outsourced radio button is selected,
     *      * changes text to "Company Name"
     */
    public void onModifyPartOutsourced(ActionEvent actionEvent) {modifyPartSourceTxt.setText("Company Name");
    }



    /***
     *
     * @param actionEvent Function: When cancel is clicked, confirm that user wants to cancel their request.
     *      * If user clicks "OK", navigate to home screen.
     * @throws IOException
     */
    public void cancelModifyPartClick(ActionEvent actionEvent) throws IOException {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Cancel Action");
        confirm.setHeaderText("Are you sure you would like to cancel?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FirstScreen.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Home Screen");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /***
     * @param selectedItem Function: Retrieves data about selected part from home screen,
     *      * makes appropriate modifications if part is sourced in house or
     *      * outsourced. Populates text fields with this data.
     */
    public void sendParts(Part selectedItem) {
        modifyPartIdTxt.setText(String.valueOf(selectedItem.getPartId()));
        modifyPartNameTxt.setText(String.valueOf(selectedItem.getPartName()));
        ModifyPartStockTxt.setText(String.valueOf(selectedItem.getPartStock()));
        modifyPartPriceTxt.setText(String.valueOf(selectedItem.getPartPrice()));
        modifyPartMaxTxt.setText(String.valueOf(selectedItem.getPartMax()));
        modifyPartMinTxt.setText(String.valueOf(selectedItem.getPartMin()));

        if (selectedItem instanceof InHouse) {
            partInHouseRBtn.setSelected(true);
            modifyPartMachineIDTxt.setText(String.valueOf(((InHouse)selectedItem).getMachineId()));
            modifyPartSourceTxt.setText("Machine ID");

        }
        if (selectedItem instanceof OutSourced) {
            modifyPartOutsourcedRBtn.setSelected(true);
            modifyPartMachineIDTxt.setText(String.valueOf(((OutSourced)selectedItem).getCompanyName()));
            modifyPartSourceTxt.setText("Company Name");
        }
    }

    /***
     *
     * @param selectedIndex Function: retrieves index of selected part from home screen
     */
    public void sendIndex(int selectedIndex) {
        i = selectedIndex;
    }


    /***
     * Function: Allows for saving of changes made to part.
     * Saves machineId if in house radio button is selected.
     * Saves companyName if outsourced radio button is selected.
     * Parses information from text fields into the appropriate data type.
     *
     * Produces error if no name is entered.
     * Checks the inventory parameters are valid (max > min, stock < max,
     * stock > min). Produces error if this is not the case.
     * If all parameters are filled with valid data, updates part, sends info
     * to partsTable on main screen.
     *
     * @param actionEvent Function: Allows for saving of changes made to part.
     *      * Saves machineId if in house radio button is selected.
     *      * Saves companyName if outsourced radio button is selected.
     *      * Parses information from text fields into the appropriate data type.
     *      *
     *      * Produces error if no name is entered.
     *      * Checks the inventory parameters are valid (max > min, stock < max,
     *      * stock > min). Produces error if this is not the case.
     *      * If all parameters are filled with valid data, updates part, sends info
     *      * to partsTable on main screen.
     * @throws IOException
     */
    public void saveModifyPartClick(ActionEvent actionEvent) throws IOException {
        try {
            if (partInHouseRBtn.isSelected()) {
                partId = Integer.parseInt(modifyPartIdTxt.getText());
                partName = modifyPartNameTxt.getText();
                partPrice = Double.parseDouble(modifyPartPriceTxt.getText());
                partStock = Integer.parseInt(ModifyPartStockTxt.getText());
                partMax = Integer.parseInt(modifyPartMaxTxt.getText());
                partMin = Integer.parseInt(modifyPartMinTxt.getText());
                machineId = Integer.parseInt(modifyPartMachineIDTxt.getText());

                if (partName.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable to add product.");
                    alert.setContentText("Please enter valid product name and try again.");
                    alert.showAndWait();
                } else {
                    if (partMax > partMin && partMax >= partStock && partMin <= partStock) {
                        //if (partStock >= partMin) {
                        //if (partStock >= partMax) {
                        InHouse part = new InHouse(partId, partName, partStock, partPrice, partMin, partMax, machineId);
                        Inventory.updatePart(i, part);
                        partUpdated = true;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setHeaderText("Unable to add product.");
                        alert.setContentText("Inventory numbers are not within parameters.");
                        alert.showAndWait();

                        System.out.println("Inventory parameters invalid");
                    }
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to add product.");
            alert.setContentText("Select a part to modify or remove.");
            alert.showAndWait();
        }
        try {
            if (modifyPartOutsourcedRBtn.isSelected()) {
                partId = Integer.parseInt(modifyPartIdTxt.getText());
                partName = modifyPartNameTxt.getText();
                partPrice = Double.parseDouble(modifyPartPriceTxt.getText());
                partStock = Integer.parseInt(ModifyPartStockTxt.getText());
                partMax = Integer.parseInt(modifyPartMaxTxt.getText());
                partMin = Integer.parseInt(modifyPartMinTxt.getText());
                companyName = modifyPartMachineIDTxt.getText();
                if (partName.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable to add product.");
                    alert.setContentText("Please enter valid product name and try again.");
                    alert.showAndWait();
                } else {
                    if (partMax > partMin) {
                        if (partStock >= partMin) {
                            if (partStock <= partMax) {
                                OutSourced part = new OutSourced(partId, partName, partStock, partPrice, partMin, partMax, companyName);
                                Inventory.updatePart(i, part);
                                partUpdated = true;
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Error");
                                alert.setHeaderText("Unable to save changes.");
                                alert.setContentText("Inventory number is greater than max inventory");
                                alert.showAndWait();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error");
                            alert.setHeaderText("Unable to save changes.");
                            alert.setContentText("Inventory number is less than max inventory");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setHeaderText("Unable to save changes.");
                        alert.setContentText("Inventory parameters are invalid.");
                        alert.showAndWait();
                        System.out.println("Inventory parameters invalid");
                    }
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to save changes.");
            alert.setContentText("Blank fields or invalid input.");
            alert.showAndWait();
        }
        if (partUpdated) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/FirstScreen.FXML"));
            loader.load();

            FirstScreen firstScreen = loader.getController();
            Parent root = loader.getRoot();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Home Screen");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}