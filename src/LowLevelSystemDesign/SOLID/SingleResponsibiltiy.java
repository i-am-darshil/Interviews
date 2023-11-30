package LowLevelSystemDesign.SOLID;

//  A class should have single reason to change.
public class SingleResponsibiltiy {

    // Here 3 reasons to change
    class IncorrectInvoice {
        public double calculateInvoide() {
            return 0;
        }

        public void saveToDB() {

        }

        public void printInvoice() {

        }
    }

    class Invoice {}

    class InvoiceCalculator {
        Invoice invoice;
        public double calculateInvoide() {
            return 0;
        }
    }

    class InvoiceDAO {
        Invoice invoice;
        public void saveToDB() {

        }
    }

    class InvoicePrinter {
        Invoice invoice;
        public void printInvoice() {

        }
    }

}
