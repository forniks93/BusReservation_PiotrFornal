/**
 * Orders class inheriting Trips class
 * Information about travelTrip, discount and number of passengers
 * constructor/getter/setter/toString for variable travelInfo/isDiscounted/passengers
 */
public class Orders extends Trips {
    /**
     * variable isDiscounted
     */
    private String isDiscounted;
    /**
     * variable passengers
     */
    private String passengers;

    /**
     * Constructor for Orders
     * @param travelInfo inherited from class Trips
     * @param isDiscounted information about discounted price
     * @param passengers number of passengers
     */
    public Orders(String travelInfo, String isDiscounted, String passengers) {
        super(travelInfo);
        this.isDiscounted = isDiscounted;
        this.passengers = passengers;
    }

    /**
     * get isDiscounted
     * @return isDiscounted - getter
     */
    public String getIsDiscounted() {
        return isDiscounted;
    }

    /**
     * set isDiscounted
     * @param isDiscounted setter
     */
    public void setIsDiscounted(String isDiscounted) {
        this.isDiscounted = isDiscounted;
    }

    /**
     * get passengers
     * @return passengers - getter
     */
    public String getPassengers() {
        return passengers;
    }

    /**
     * set passengers
     * @param passengers setter
     */
    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    /**
     * Information about travelInfo, isDiscounted and passengers to view
     * @return travelInfo, isDiscounted, passengers String
     */
    @Override
    public String toString() {
        return getTravelInfo() + " - " + isDiscounted + " - Liczba pasażerów: " + passengers;
    }
}

