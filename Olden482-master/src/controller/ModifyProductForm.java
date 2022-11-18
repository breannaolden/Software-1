/***
 * Created by Breanna Olden
 * Student ID: 001532324
 * C482 Software 1
 *
 */

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Part;
import model.Product;
import model.Inventory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.AddProductForm.productIdNum;

public class ModifyProductForm implements Initializable {
    @FXML
    public Button saveModifyProductButton;
    @FXML
    public Button cancelModifyProductButton;
    @FXML
    public TextField modifyProductIdTxt;
    @FXML
    public TextField modifyProductNameTxt;
    @FXML
    public TextField modifyProductStockTxt;
    @FXML
    public TextField modifyProductPriceTxt;
    @FXML
    public TextField modifyProductMaxTxt;
    @FXML
    public TextField modifyProductMinTxt;
    @FXML
    public Button modifyProductAddButton;
    @FXML
    public TextField mainPartSearchTxt;
    @FXML
    public TableView partsTable;
    @FXML
    public TableView associatedPartsTable;
    @FXML
    public TableColumn associatedPartIdColumn;
    @FXML
    public TableColumn associatedPartNameColumn;
    @FXML
    public TableColumn associatedPartStockColumn;
    @FXML
    public TableColumn associatedPartPriceColumn;
    @FXML
    public TableColumn partIdColumn;
    @FXML
    public TableColumn partNameColumn;
    @FXML
    public TableColumn partStockColumn;
    @FXML
    public TableColumn partPriceColumn;
    public Button removeAssociatedPartBtn;


    int productId = productIdNum;
    String productName;
    double productPrice;
    int productStock;
    int productMax;
    int productMin;
    boolean productAdded = false;
    int i;

    /***
     * Creating observable list to hold parts associated with products
     */
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();

    /***
     * When search bar is utilized (after pressing of enter button on keyboard),
     * searches partsTable with string of text that was entered in the search bar
     * @param actionEvent
     *
     * RUNTIME ERROR
     * Did not work initially because I had since changed the name of the search
     * bar in the FXML file and was calling for a text field that no longer existed.
     */
    public void mainpartSearchClick(ActionEvent actionEvent) {
        FirstScreen.mainPartSearchClick(mainPartSearchTxt, partsTable);
    }



    /***
     * @param actionEvent
     *      * Function: for saving mdofications made to selected product.
     *      *
     *      * Parses entries into textfields into appropriate data types
     *      *
     *      * If there is no product name entered, will produce an error.
     *      *
     *      * Checks if the inventory parameters are valid (max > min,
     *      * stock <= max, stock >= min. If not, will produce an error.
     *      *
     *      * Checks for invalid or blank entries and produces an error
     *      * if this is the case.
     *      *
     *      * If product is added successfully, navigates back to the
     *      * main screen.
     */
    public void saveModifyProductClick(ActionEvent actionEvent) {
        try {
            productId = Integer.parseInt(modifyProductIdTxt.getText());
            productName = modifyProductNameTxt.getText();
            productPrice = Double.parseDouble(modifyProductPriceTxt.getText());
            productStock = Integer.parseInt(modifyProductStockTxt.getText());
            productMax = Integer.parseInt(modifyProductMaxTxt.getText());
            productMin = Integer.parseInt(modifyProductMinTxt.getText());

            if (productName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Unable to save changes.");
                alert.setContentText("Please enter valid product name.");
                alert.showAndWait();
            } else {
                if (productMax > productMin && productMax >= productStock && productMin <= productStock) {
                   // if (productStock >= productMin) {
                        //if (productStock >= productMax) {
                            Product product = new Product(productId, productName, productPrice, productStock, productMin, productMax);
                            product.getAllAssociatedPart().addAll(associatedPart);
                            Inventory.updateProduct(i, product);
                            productAdded = true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Inventory parameters invalid");
                    alert.setContentText("Please keep inventory numbers entered within parameters.");
                    alert.showAndWait();
                    System.out.println("Inventory parameters invalid");
                }
                if (productAdded) {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/FirstScreen.fxml"));
                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    stage.setTitle("Home Screen");
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            }
        } catch (NumberFormatException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to save modifications.");
            alert.setContentText("Blank fields or incomplete entry.");
            alert.showAndWait();
        }
    }



    /***
     * @param actionEvent
     *      * When cancel is clicked, generate confirmation that user does
     *      * wish to cancel modification of product. If user clicks "OK",
     *      * navigates to home screen
     * @throws IOException
     *
     */
    public void cancelModifyProductClick(ActionEvent actionEvent) throws IOException {

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Cancel");
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
     * @param actionEvent
     *      * Function: Allows for addition of part to associated parts table.
     *      *
     *      * Produces error if no part is selected prior to click of add button.
     */
    public void modifyProductAddClick(ActionEvent actionEvent) {

        Part partChosen = (Part) partsTable.getSelectionModel().getSelectedItem();

        if (partChosen == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to add associated part.");
            alert.setContentText("Please select a part to add as an associated part.");
            alert.showAndWait();
        } else {
            associatedPart.add(partChosen);
        }

       // Part partChosen = associatedPartsTable.getSelectionModel().getSelectedItem();
       // associatedPart.add(partChosen);
    }


    /***
     * @param selectedItem
     * Function: takes information from main page, prepopulates
     * textfields on Modify Product Form with this information
     * to assist user in making desired change.
     *

     */
    public void sendProduct(Product selectedItem) {
        modifyProductIdTxt.setText(String.valueOf(selectedItem.getProductId()));
        modifyProductNameTxt.setText(selectedItem.getProductName());
        modifyProductStockTxt.setText(String.valueOf(selectedItem.getProductStock()));
        modifyProductPriceTxt.setText(String.valueOf(selectedItem.getProductPrice()));
        modifyProductMaxTxt.setText(String.valueOf(selectedItem.getProductMax()));
        modifyProductMinTxt.setText(String.valueOf(selectedItem.getProductMin()));
        associatedPart = selectedItem.getAllAssociatedPart();
        associatedPartsTable.setItems(associatedPart);
    }

    /***
     * @param selectedIndex
     * Function: takes index for selected item from main screen,
     * sends it to Modify Product Form.
     *
     */
    public void sendIndex(int selectedIndex) {
        i = selectedIndex;
    }

    /***
     * @param actionEvent
     * Function: Allows for deletion of part from the associated parts table.
     *
     * Does not allow for deletion of part if no part is selected.
     *
     * Requests confirmation from user regarding deletion of associated part
     * prior to fulfilling deletion.
     *
     *
     */
    public void removeAssociatedPartClick(ActionEvent actionEvent) {
        Part partChosen = (Part) associatedPartsTable.getSelectionModel().getSelectedItem();
        if (partChosen == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Please select an associated part to remove.");
            alert.showAndWait();
        } else {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Remove Action");
            confirm.setHeaderText("Are you sure you would like to remove this part?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (partChosen != null) {
                    associatedPart.remove(partChosen);
                }
        }
        }
    }

    /***
     * @param url
     * @param resourceBundle
     * Function: populates parts table and associated parts table with data
     * either from the home screen or from memory.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        associatedPartsTable.setItems(associatedPart);
        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        associatedPartStockColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
    }


}
