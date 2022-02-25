package Utilities;

import MVC.Model.Appointment;
import MVC.Model.Customer;

/** Class that holds Static Appointment & Customer for selection. */
public class Selector {

    public static Appointment selectedAppointment = null;
    public static Customer selectedCustomer = null;

    /** selectedAppointment getter.
     * @return selectedAppointment*/
    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    /** selectedAppointment setter.
     * @param selectedAppointment */
    public static void setSelectedAppointment(Appointment selectedAppointment) {
        Selector.selectedAppointment = selectedAppointment;
    }

    /** selectedCustomer getter.
     * @return selectedCustomer*/
    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    /** selectedCustomer setter.
     * @param selectedCustomer */
    public static void setSelectedCustomer(Customer selectedCustomer) {
        Selector.selectedCustomer = selectedCustomer;
    }
}
