package model;

/***
 * @author Breanna Olden
 * WGU ID: 001532324
 *
 */

public abstract class Part {
    private int partId;
    private String partName;
    private int partStock;
    private double partPrice;
    private int partMin;
    private int partMax;

    // Constructor
    public Part(int partId, String partName, int partStock, double partPrice, int partMin, int partMax)
    {
        this.partId = partId;
        this.partName = partName;
        this.partStock = partStock;
        this.partPrice = partPrice;
        this.partMin = partMin;
        this.partMax = partMax;
    }

    /***
     *
     * Creating setters and getters
     * RUNTIME ERROR: did not have void in setter statements gave the error: missing return statement
     */

    /***
     *
     * @return getter for partId
     * Retrieves the partId
     */
    public int getPartId() {
        return partId;
    }

    /***
     *
     * @param partId setter for partId
     * Sets the ID for parts
     */
    public void setPartId(int partId) {
        this.partId = partId;
    }

    /***
     *
     * @return getter for partName
     * Retrieves the partName
     */
    public String getPartName() {
        return partName;
    }

    /***
     *
     * @param partName setter for partName
     * Sets the name for parts
     */
    public void setPartName(String partName)
    {
        this.partName = partName;
    }

    /***
     *
     * @return getter for partPrice
     * Retrieves the price of the part
     */
    public double getPartPrice() {
        return partPrice;
    }

    /***
     *
     * @param partPrice setter for partPrice
     * Sets the price for parts
     */
    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    /***
     *
     * @return getter for partStock
     * Retrieves the inventory number for parts
     */
    public int getPartStock() {
        return partStock;
    }

    /***
     *
     * @param partStock setter for partStock
     * Sets the inventory number for parts
     */
    public void setPartStock(int partStock) {
        this.partStock = partStock;
    }

    /***
     *
     * @return getter for minimum inventory numbers
     * Retrieves the minimum required number of parts
     */
    public int getPartMin() {
        return partMin;
    }

    /***
     *
     * @param partMin setter for minimum inventory numbers
     * Sets the minimum required number of parts
     */
    public void setPartMin(int partMin) {
        this.partMin = partMin;
    }

    /***
     *
     * @return getter for maximum number of inventory allowed
     * Retrieves the maximum allowed number of parts
     */
    public int getPartMax() {
        return partMax;
    }

    /***
     *
     * @param partMax setter for partMax
     * Sets the max allowed number of each part
     */
    public void setPartMax(int partMax) {
        this.partMax = partMax;
    }


}
