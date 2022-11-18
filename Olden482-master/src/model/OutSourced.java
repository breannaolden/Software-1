package model;

public class OutSourced extends Part
{
    public String companyName;

    /***
     *
     * @param partId - provides the part id number
     * @param partName - provides the part name
     * @param partPrice - provides the part price
     * @param partStock - provides the number in inventory
     * @param partMin - provides the minimum inventory number allowed
     * @param partMax - provides the maximum inventory number allowed
     * @param companyName - provides the name of the company for outsourced parts
     *                    Created getter and setter
     */
    public OutSourced(int partId, String partName, int partStock, double partPrice,  int partMin, int partMax, String companyName) {
        super(partId, partName, partStock, partPrice, partMin, partMax);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
