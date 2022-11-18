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
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductForm implements Initializable {

    @FXML
    public TextField mainProductSearchTxt;
    @FXML
    public Button cancelAddProductButton;
    @FXML
    public TextField productMinTxt;
    @FXML
    public TextField productMaxTxt;
    @FXML
    public TextField productPriceTxt;
    @FXML
    public TextField productStockTxt;
    @FXML
    public TextField productNameTxt;
    @FXML
    public TextField productIdTxt;
    @FXML
    public TextField addProductSearchTxt;

    public static int productIdNum;
    @FXML
    public TableView partsTable;
    @FXML
    public TableView associatedPartTable;
    @FXML
    public Button addProductSaveButton;
    @FXML
    public Button addProductRemoveAssociatedPartButton;
    @FXML
    public Button addProductAddButton;
    @FXML
    public TableColumn partIdColumn;
    @FXML
    public TableColumn partNameColumn;
    @FXML
    public TableColumn partStockColumn;
    @FXML
    public TableColumn partPriceColumn;
    @FXML
    public TableColumn associatedPartIdColumn;
    @FXML
    public TableView associatedPartsTable;
    @FXML
    public TableColumn associatedPartNameColumn;
    @FXML
    public TableColumn associatedStockColumn;
    @FXML
    public TableColumn associatedPartPriceColumn;

    int productId = productIdNum;
    String productName;
    double productPrice;
    int productStock;
    int productMin;
    int productMax;
    boolean productAdded = false;

    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();



    /***
     * @param actionEvent
     * Function: Allows for addition of product to productTable.
     * Parses entry from textfield to the appropriate data type.
     *
     * Produces error if no product name entered.
     * Produces error if inventory parameters are invalid.
     * Produces error if other textfields left blank or with invalid
     * data types/entry.
     *
     * If product successfully added, navigates user back to home screen.
     *
     * RUNTIME ERROR
     * Logic error: stipulated that productstock >= productMax rather than <= productMax.
     * Would kick back error print in console.
     * @throws IOException
     * FUTURE ENHANCEMENT
     * Figure out how to auto increment id
     */
    public void addProductSaveClick(ActionEvent actionEvent) throws IOException {
    try {
        int productId = Integer.parseInt(productIdTxt.getText());
        String productName = productNameTxt.getText();
        double productPrice = Double.parseDouble(productPriceTxt.getText());
        int productStock = Integer.parseInt(productStockTxt.getText());
        int productMax = Integer.parseInt(productMaxTxt.getText());
        int productMin = Integer.parseInt(productMinTxt.getText());

        if (productName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to add product.");
            alert.setContentText("Please enter valid product name and try again.");
            alert.showAndWait();
        } else {
            if (productMax > productMin) {
                if (productStock >= productMin) {
                    if (productStock <= productMax) {
                        Product product = new Product(productId, productName, productPrice, productStock, productMin, productMax);
                        product.getAllAssociatedPart().addAll(associatedPart);
                        Inventory.addProduct(product);
                        productIdNum++;
                        productAdded = true;
                    } else {
                        System.out.println("Inventory number is greater than max inventory");
                    }
                } else {
                    System.out.println("Inventory number is less than min inventory");
                }
            } else {
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
        alert.setHeaderText("Unable to add product.");
        alert.setContentText("Blank fields or incomplete entry.");
        alert.showAndWait();
    }
    }


    /***
     * @param actionEvent
     * When cancel button is clicked, user is prompted to confirm
     * their desire to cancel this action. If user clicks "OK"
     * they are sent back to the home screen
     * @throws IOException
     */
    public void cancelAddProductClick(ActionEvent actionEvent) throws IOException {
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
     * @param actionEvent
     * Allows for addition of product to products Table.
     *
     */
    public void addProductAddClick(ActionEvent actionEvent) {
        Part partChosen = (Part) partsTable.getSelectionModel().getSelectedItem();
        associatedPart.add(partChosen);
    }

    /***
     *
     * @param actionEvent
     * Allows for removal of part associated with product from the associated
     * parts table.
     *
     */
    public void addProductRemoveAssociatedPartClick(ActionEvent actionEvent) {

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
     *
     * @param actionEvent
     * Function: allows for searching through textfield
     * above parts table on add product form.
     */
    public void addProductSearchClick(ActionEvent actionEvent) {
        FirstScreen.mainPartSearchClick(addProductSearchTxt, partsTable);
    }


    /***
     *
     * @param url
     * @param resourceBundle
     * Auto populates tables.
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
        associatedStockColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

    }


}
