package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();



    /***
     * Add new part to Observable List called "allParts"
     * @param part
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /***
     * Returns list of all parts
     * @return
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    /***
     * Adds new product to Observable List "allProducts"
     * @param product
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /***
     * Returns list of all products
     * @return
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


    /***
     * Allows for searching parts in the table by ID number
     *
     * @param partId
     * @return
     */
    public static Part lookupPart(int partId) {
       for (int i = 0; i < allParts.size(); i++) {
           Part searchedPart = allParts.get(i);

           if (searchedPart.getPartId() == partId) {
               return searchedPart;
           }
       }
        return null;
    }



    /***
     * Allows for searching of table for full or partial matches of partName
     * @param partOfPartName represents a partial string
     * @return
     */
    public static ObservableList<Part> lookupPart(String partOfPartName) {
        ObservableList<Part> partName = FXCollections.observableArrayList();
        ObservableList<Part> allParts = getAllParts();

        for (Part searchedPart : allParts) {
            if (searchedPart.getPartName().contains(partOfPartName)) {
                partName.add(searchedPart);
            }
        }
        return partName;
    }

    /***
     * Allows for searching of products by ID number
     * @param productId
     * @return
     */
    public static Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            Product searchedProduct = allProducts.get(i);
            if (searchedProduct.getProductId() == productId) {
                return searchedProduct;
            }
        }
     return null;
 }


    /***
     * Allows for searching of product by full or partial matches.
     * @param partOfProductName represents a partial match to productName
     * @return
     */
    public static ObservableList<Product> lookupProduct(String partOfProductName) {
        ObservableList<Product> productName = FXCollections.observableArrayList();

        for (Product searchedProduct : allProducts) {
            if (searchedProduct.getProductName().contains(partOfProductName)) {
                productName.add(searchedProduct);
            }
        }
        return productName;
    }


    /***
     * Allows for modification of part by ID number or by name
     * Updates the allParts observable list.
     * @param i represents the index
     * @param part represents the part
     */
    public static void updatePart(int i, Part part) {
        allParts.set(i, part);
    }


    /***
     * Allows for modification of product by ID or by name
     * @param i represents the index at which the product is found in the array list
     * @param product represents the product
     */
     public static void updateProduct (int i, Product product) {
         allProducts.set(i, product);
 }



    /***
     * Allows for deletion of parts from allParts
     * @param partChosen represents the item selected in the tableView
     * @return
     */
    public static boolean deletePart(Part partChosen) {
        if (allParts.contains(partChosen)) {
            allParts.remove(partChosen);
            return true;
        } else {
            return false;
        }
    }

// Delete product

    /***
     * Allows for deletion of product from allProducts
     * @param partChosen represents item selected for deletion
     * @return
     */
    public static boolean deleteProduct(Product partChosen) {
        if (allProducts.contains(partChosen)) {
            allProducts.remove(partChosen);
            return true;
        } else {
            return false;
        }
    }
}
