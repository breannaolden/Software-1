package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private int productId;
    private String productName;
    private int productStock;
    private double productPrice;
    private int productMin;
    private int productMax;

    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();

    // Constructor
    public Product(int productId, String productName, double productPrice, int productStock, int productMin, int productMax) {
        this.productId = productId;
        this.productName = productName;
        this.productStock = productStock;
        this.productPrice = productPrice;
        this.productMin = productMin;
        this.productMax = productMax;
    }

        /***
         *
         * @param productId sets the id for products, created setter and getter
         */
        public void setProductId ( int productId){
            this.productId = productId;
        }
        public int getProductId () {
            return productId;
        }

        public void setProductName (String productName){
            this.productName = productName;
        }
        public String getProductName () {
            return productName;
        }
        /***
         *
         * @param productPrice sets the price for products, created setter and getter
         */
        public void setProductPrice ( double productPrice){
            this.productPrice = productPrice;
        }
        public double getProductPrice () {
            return productPrice;
        }

        /***
         *
         * @param productStock sets the inventory levels for products, created setter and getter
         */
        public void setProductStock ( int productStock){
            this.productStock = productStock;
        }
        public int getProductStock () {
            return productStock;
        }

        /***
         *
         * @param productMin sets minimum inventory levels for products, created setter and getter
         */
        public void setProductMin ( int productMin){
            this.productMin = productMin;
        }
        public int getProductMin () {
            return productMin;
        }

        /***
         *
         * @param productMax sets the maximum inventory level, created setter and getter
         */
        public void setProductMax ( int productMax){
            this.productMax = productMax;
        }
        public int getProductMax () {
            return productMax;
        }


    /***
     * Adds associated parts
     * @param part
     *
     */
        public void addAssociatedPart (Part part){
            associatedPart.add(part);
        }


    /***
     * Deletes associated parts
     * @param selectedAssociatedPart
     * @return
     */
        public boolean deleteAssociatedPart (Part selectedAssociatedPart){
            if (associatedPart.contains(selectedAssociatedPart)) {
                associatedPart.remove(selectedAssociatedPart);
                return true;
            } else {
                return false;
            }
        }

    /***
     * Returns list of all associated parts
     * @return
     */
        public ObservableList<Part> getAllAssociatedPart () {
            return associatedPart;
        }


    }

