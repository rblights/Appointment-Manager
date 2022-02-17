package Utilities;

import MVC.Model.Appointment;
import MVC.Model.Customer;

public class Selector {

    public static Appointment selectedAppointment = null;
    public static Customer selectedCustomer = null;

    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    public static void setSelectedAppointment(Appointment selectedAppointment) {
        Selector.selectedAppointment = selectedAppointment;
    }

    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public static void setSelectedCustomer(Customer selectedCustomer) {
        Selector.selectedCustomer = selectedCustomer;
    }
}
