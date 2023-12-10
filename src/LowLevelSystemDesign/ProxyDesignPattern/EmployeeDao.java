package LowLevelSystemDesign.ProxyDesignPattern;

public interface EmployeeDao {
    void createEntry(String role, String entry);
    void updateEntry(String role, String entry);
    void deleteEntry(String role, String entry);
}
