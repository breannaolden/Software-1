/***
 * Created by Breanna Olden
 * Student ID: 001532324
 * C482 Software 1
 *
 */

package controller;

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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.Inventory.*;

public class FirstScreen implements Initializable {
    @FXML
    public Button addPartButton;
    @FXML
    public Button modifyPartButton;
    @FXML
    public Button deletePartButton;
    @FXML
    public TextField mainProductSearchTxt;
    @FXML
    public Button addProductButton;
    @FXML
    public Button modifyProductButton;
    @FXML
    public Button deleteProductButton;
    @FXML
    public Button exitFirstScreenButton;
    @FXML
    public TableView<Part> partsTable;
    @FXML
    public  TextField mainPartSearchTxt;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TableColumn<Part, Integer> partStockColumn;
    @FXML
    public TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private  TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    @FXML
    private TableColumn<Product, Integer> productStockColumn;

    private static int i;

    /***
     * Initialize method. Populates partsTable and productTable with
     * the appropriate data.
     * @param url
     * @param resourceBundle
     *
     * RUNTIME_ERROR
     * would not list default parts with this code
     *         partsTable.setItems(allParts);
     *         needed to utilize getter and specify from where
     *         I wanted to pull this data.
     * RUNTIME ERROR
     * Initially had inconsistent naming in FXML file and java file
     * would not run until these are all consistent. Renamed all columns and text fields
     * consistently and then it ran correctly.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productTable.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<>("productStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

        partsTable.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
    }

    /***
     * @param actionEvent
     * Function: When "Add" clicked on Parts side, navigates to "Add Parts" window.
     *
     */
    public void addPartButtonClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Part Sourced In House");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /***
     * @param actionEvent
     * Function: When "Modify" clicked on Parts side, navigates to "Modify Parts" window.
     * Loads info from selected item to Modify Part Form
     *
     * Produces error if no part selected prior to clicking of modify button
     *
     * RUNTIME ERROR:
     * Initially copy and pasted sendParts line and modified for sendIndex.
     * Did not change getter to getSelectedIndex instead of getSelectedItem.
     * Resulted in error stating that it expected an int but received a part
     * instead and wanted to change the data type of first parameter in sendIndex
     * to part.
     *
     *
     */

    public void modifyPartButtonClick(javafx.event.ActionEvent actionEvent) throws IOException {

        Part partChosen = partsTable.getSelectionModel().getSelectedItem();
        i = getAllParts().indexOf(partChosen);

        if (partChosen == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No part chosen to modify.");
            alert.setContentText("Please choose a part to modify.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartForm.fxml"));
            loader.load();

            ModifyPartForm modifyPartForm = loader.getController();
            modifyPartForm.sendParts(partsTable.getSelectionModel().getSelectedItem());
            modifyPartForm.sendIndex(partsTable.getSelectionModel().getSelectedIndex());

            Parent root = loader.getRoot();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Part");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /***
     * @param actionEvent
     * Function When "Add" clicked on Products side, navigates to "Add Products" window.
     *
     */
    public void addProductButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Product Sourced In House");
        stage.setScene(new Scene(root));
        stage.show();
    }
    /***
     * @param actionEvent
     * Function: When "Modify" clicked on Parts side, navigates to "Modify Parts" window.
     * Sends info regarding selected item to Modify Product Form.
     *
     * Produces error if no product is selected prior to click of Modify button.
     *
     */
    public void modifyProductButtonClick(javafx.event.ActionEvent actionEvent) throws IOException {

        Product productChosen = productTable.getSelectionModel().getSelectedItem();
        i = getAllProducts().indexOf(productChosen);

        if (productChosen == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No product chosen to modify.");
            alert.setContentText("Please choose a product to modify.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductForm.fxml"));
            loader.load();

            ModifyProductForm modifyProductForm = loader.getController();
            modifyProductForm.sendProduct(productTable.getSelectionModel().getSelectedItem());
            modifyProductForm.sendIndex(productTable.getSelectionModel().getSelectedIndex());

            Parent root = loader.getRoot();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(root));
            stage.show();
        }
        }


    /***
     * @param actionEvent
     * Function: Allows for searching of products by partial or full product names or product IDs.
     *
     * FUTURE ENHANCEMENT
     * this is currently case sensitive. Would be useful for this to be case insensitive as
     * users do not always use correct case when searching.
     *
     */
    public void mainProductSearchClick(ActionEvent actionEvent) {
        try {
            String search = mainProductSearchTxt.getText();
            ObservableList<Product> product = Inventory.lookupProduct(search);
            if (product.size() == 0) {
                int productId = Integer.parseInt(search);
                Product searchedProduct = Inventory.lookupProduct(productId);
                if (searchedProduct != null) {
                    product.add(searchedProduct);
                }
            }
            productTable.setItems(product);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No matching product found.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }

    /***
     * @param actionEvent
     * Function: allows for searching of parts by partial or full name/ID
     *
     * FUTURE ENHANCEMENT
     * This search is currently case sensitive. Users do not always search
     * with correct case, it would be useful for this search to be case
     * insensitive.
     *
     *
     */
   public void mainPartSearchClick(ActionEvent actionEvent) {
        mainPartSearchClick(mainPartSearchTxt, partsTable);
   }

    /***
     * @param mainPartSearchTxt
     * Parses text typed in textfield, utilizes this to compare to partsTable
     *
     * @param partsTable
     * Function: Allows for search of parts table using partial or full name/ID.
     *
     * Produces error if no matching part found.
     *
     * FUTURE ENHANCEMENT
     * Make this case insensitive.
     *

     */
    static void mainPartSearchClick(TextField mainPartSearchTxt, TableView<Part> partsTable) {
        try {
            String search = mainPartSearchTxt.getText();
            ObservableList<Part> part = Inventory.lookupPart(search);
            if (part.size() == 0) {
                int partId = Integer.parseInt(search);
                Part searchedPart = Inventory.lookupPart(partId);
                if (searchedPart != null) {
                    part.add(searchedPart);
                }
            }
            partsTable.setItems(part);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No matching part found.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }

    }

    /***
     * @param actionEvent
     * Function: Allows for deletion of part from partsTable.
     * Requests confirmation from user prior to deletion.
     * If user clicks "OK" on confirmation pop up, then proceeds
     * with deletion.
     *
     * RUNTIME ERROR
     * Initially did not have error button pop up if user tried to
     * delete item without selecting item first. Added warning pop up.
     * Then had confirmation pop up before error pop up of none selected.
     * Adjusted logic to check if selection is null and provide error before
     * proceeding if selected is not null.
     *
     *
     */
    public void mainDeletePartClick(ActionEvent actionEvent) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("WARNING");
            alert2.setHeaderText("Unable to delete part.");
            alert2.setContentText("Please select part to delete and try again.");
            alert2.showAndWait();
        } else {
            if (selectedPart != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Confirm Delete Action");
                confirm.setHeaderText("Are you sure you want to delete this part?");
                Optional<ButtonType> result = confirm.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    //Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
                    Inventory.deletePart(selectedPart);
                }
            }
        }

        }

    /***
     * @param actionEvent
     * Function: Allows for deletion of product from productTable.
     * Produces error if none selected.
     *
     */
    public void mainDeleteProductClick(ActionEvent actionEvent) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to delete product.");
            alert.setContentText("Please select product to delete.");
            alert.showAndWait();
        } else {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Delete Action");
            confirm.setHeaderText("Are you sure you want to delete this product?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (selectedProduct.getAllAssociatedPart().isEmpty()) {
                    Inventory.deleteProduct(selectedProduct);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable to delete product.");
                    alert.setContentText("Invalid selection");
                    alert.showAndWait();
                }
            }
        }
    }



    /***
     * @param actionEvent
     * Function: Closes application when exit button is clicked.
     * Prompts user for confirmation of closure before exiting application.
     *
     */
    public void exitFirstScreenClick(javafx.event.ActionEvent actionEvent) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Exit Action");
        confirm.setHeaderText("Are you sure you want to close application?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}


