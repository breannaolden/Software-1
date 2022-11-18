package model;

// InHouse class inherits from Part class
public class InHouse extends Part {
    /***
     * Initializing machineId, new variable for use with parts that are sourced In House
     */
    private int machineId;

    /***
     *
     * @param partId represents the part ID number
     * @param partName represents the name of the part
     * @param partStock represents the current inventory levels of the part
     * @param partPrice represents the price of the part
     * @param partMin represents the minimum allowable inventory levels
     * @param partMax represents the maximum allowable inventory levels
     * @param machineId represents the unique ID number of the machine the part is associated with
     */
    public InHouse(int partId, String partName, int partStock, double partPrice,  int partMin, int partMax, int machineId) {
        super(partId, partName, partStock, partPrice, partMin, partMax);
        this.machineId = machineId;
    }

    /***
     * Establishing getter for machineId
     * @return
     */
    public int getMachineId() {
        return machineId;
    }

    /***
     * Establishing getter for machineId
     * @param machineId
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }


}
