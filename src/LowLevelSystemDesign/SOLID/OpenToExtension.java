package LowLevelSystemDesign.SOLID;

// Open for extension, closed for modification
public class OpenToExtension {

    class Invoice {}


    // currently save to DB. If save to file, we will have to modify.
    class IncorrectInvoiceDAO {
        Invoice invoice;
        public void saveToDB() {

        }
    }

    interface InvoiceDAO {
        public void save();
    }

    class DatabaseInvoiceDao implements InvoiceDAO {
        @Override
        public void save() {

        }
    }

    class FileInvoiceDao implements InvoiceDAO {
        @Override
        public void save() {

        }
    }

}
