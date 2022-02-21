package Utilities.Languages;

import java.util.Locale;
import java.util.ResourceBundle;

/** Static ResourceBundle w/ getters & setters. */
public class RBundle {
    static ResourceBundle rBundle = ResourceBundle.getBundle("Utilities/Languages/Nat", Locale.getDefault());

    public static ResourceBundle getrBundle() {
        return rBundle;
    }

    public static void setrBundle(ResourceBundle rBundle) {
        RBundle.rBundle = rBundle;
    }
}
