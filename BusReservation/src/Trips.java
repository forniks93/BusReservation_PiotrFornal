/**
 * class Trips with information about possible trip
 * constructor/getter/setter/toString for variable travelInfo
 */
public class Trips {
    /**
     * variable travelInfo
     */
    private String travelInfo;

    /**
     * Constructor for Trips
     * @param travelInfo Information about planning trip
     */
    public Trips(String travelInfo) {
        this.travelInfo = travelInfo;

    }

    /**
     * get String travelInfo
     * @return travelInfo - getter
     */
    public String getTravelInfo() {
        return travelInfo;
    }

    /**
     * set String travelInfo
     * @param travelInfo setter
     */
    public void setTravelInfo(String travelInfo) {
        this.travelInfo = travelInfo;
    }

    /**
     * Information about travelInfo to view
     * @return travelInfo string
     */
    @Override
    public String toString() {
        return travelInfo;
    }
}