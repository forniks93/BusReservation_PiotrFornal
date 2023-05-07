import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI class
 * @author PFornal
 * @version 1.0
 */
public class GUI {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JButton Buying_selectFile;
    private JComboBox BuyingListOfTrips;
    private JCheckBox Buying_DicountedOption;
    private JTextField Buying_PassengersTextField;
    private JLabel Buying_LabelPassengers;
    private JButton Buying_SelectedConnection;
    private JList Buying_FullList;
    private JList Informations_ListOfOrders;
    private JButton Informations_ApplyOrder;
    private JButton Informations_RemoveTrip;
    private JButton Informations_InfoAboutOrder;
    private JButton Buying_ShowTrips;
    private DefaultListModel listModel;
    private DefaultListModel listModel2;

    List<Trips> trip;
    List<Trips> orderFinally;

    /**
     * GUI Parameterless class constructor
     */
    public GUI() {
        listModel = new DefaultListModel<>();
        listModel2 = new DefaultListModel<>();
        Buying_FullList.setModel(listModel);
        Informations_ListOfOrders.setModel(listModel2);
        trip = new ArrayList<>();
        orderFinally = new ArrayList<>();

        Buying_selectFile.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = "";
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setDialogTitle("Choose file with property date");
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filterOfTypes = new FileNameExtensionFilter("Only txt files", "txt");
                fileChooser.addChoosableFileFilter(filterOfTypes);

                int DialogWindow = fileChooser.showOpenDialog(null);
                if (DialogWindow == JFileChooser.APPROVE_OPTION) {
                    path = fileChooser.getSelectedFile().getAbsolutePath();
                }
                loadDataFromFile(path);

            }
        });

        Buying_ShowTrips.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                renderTripsList();
                renderProductListComboBox();
                listModel.addElement(trip);
                Buying_ShowTrips.setEnabled(false);

            }
        });

        Buying_SelectedConnection.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTrip = BuyingListOfTrips.getSelectedItem().toString();
                try {
                    int passengerValue = Integer.parseInt(Buying_PassengersTextField.getText());

                } catch (NumberFormatException a) {
                    JOptionPane.showMessageDialog(null, "Bad value of passengers!");
                }
                if (Buying_PassengersTextField.getText().startsWith(" ") || Buying_PassengersTextField.getText().endsWith(" ")) {
                    JOptionPane.showMessageDialog(null, "No valid form specified - check if there is no space at the beginning or in the end");
                }else if(Integer.parseInt(Buying_PassengersTextField.getText())<=0){
                    JOptionPane.showMessageDialog(null,"Number of passengers must by higher than 0");
                }
                else {
                    if (Buying_DicountedOption.isSelected() == true) {
                        orderFinally.add(new Orders(BuyingListOfTrips.getSelectedItem().toString(),
                                "Price of your ticket will be discounted 20%",
                                Buying_PassengersTextField.getText().toString()));
                    } else {
                        orderFinally.add(new Orders(BuyingListOfTrips.getSelectedItem().toString(),
                                "Normal price of your tickets",
                                Buying_PassengersTextField.getText()));
                    }
                }
                System.out.println(orderFinally);
            }
        });

        tabbedPane.addChangeListener(new ChangeListener() {
            /**
             * Invoked when the target of the listener has changed its state.
             *
             * @param e a ChangeEvent object
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    renderTripsList();
                    renderProductListComboBox();
                } else {
                }
            }
        });

        Informations_InfoAboutOrder.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                renderOrdersList();
            }
        });

        Informations_ApplyOrder.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrintWriter outWrite = new PrintWriter("BusReservationInfo.txt");
                    outWrite.println("Booked tickets!");
                    for (Trips t : orderFinally) {
                        outWrite.println(" - " + t.toString());
                    }
                    outWrite.close();
                    JOptionPane.showMessageDialog(null, "Your ticket order has been saved in the BusReservationInfo.txt file");

                } catch (IOException a) {
                    JOptionPane.showMessageDialog(null, "TODO Zła nazwa pliku/folderu czy coś. Błąd zapisu");
                }
            }
        });

        Informations_RemoveTrip.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultListModel model = (DefaultListModel) Informations_ListOfOrders.getModel();
                int selectedIndex = Informations_ListOfOrders.getSelectedIndex();
                if (selectedIndex != -1) {
                    model.remove(selectedIndex);
                    orderFinally.remove(selectedIndex);
                }
                renderOrdersList();
            }
        });
    }

    /**
     * Method for reading data from a selected file and adding everything to a separate list
     * @param path - Information from file
     */
    public void loadDataFromFile(String path) {
        try {
            File readFile = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(readFile));
            String tripInformations;
            while ((tripInformations = bufferedReader.readLine()) != null) {
                String[] trips = tripInformations.split(":");
                System.out.println();
                trip.add(new Trips(trips[0]));
            }
            Buying_selectFile.setEnabled(false);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while selecting the file!");
        }

    }

    /**
     * Method for rendering Trips List
     */
    public void renderTripsList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Trips t : trip) {
            listModel.addElement(t.toString());
        }
        Buying_FullList.setModel(listModel);
    }

    /**
     * Method for rendering Orders List
     */
    public void renderOrdersList() {
        DefaultListModel<String> listModel2 = new DefaultListModel<>();
        for (Trips tripsOrderFinally : orderFinally) {
            listModel2.addElement(tripsOrderFinally.toString());
        }
        Informations_ListOfOrders.setModel(listModel2);
    }

    /**
     * Method for rendering ComboBox list
     */
    public void renderProductListComboBox() {
        BuyingListOfTrips.removeAllItems();
        for (Trips t : trip) {
            BuyingListOfTrips.addItem(t.getTravelInfo());
        }
    }

    /**
     *
     * Main function of class GUI
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bus Reservation");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 400);
    }
}
