package LowLevelSystemDesign.ProxyDesignPattern;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void createEntry(String role, String entry) {
        System.out.println("created entry");
    }

    @Override
    public void updateEntry(String role, String entry) {
        System.out.println("updated entry");
    }

    @Override
    public void deleteEntry(String role, String entry) {
        System.out.println("deleted entry");
    }
}
