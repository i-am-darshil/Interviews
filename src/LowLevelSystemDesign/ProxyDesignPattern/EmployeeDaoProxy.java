package LowLevelSystemDesign.ProxyDesignPattern;

public class EmployeeDaoProxy implements EmployeeDao {

    private final EmployeeDao employeeDao;
    public EmployeeDaoProxy(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void createEntry(String role, String entry) {
        if (!isAdmin(role)) {
            System.out.println("Unauthorized");
            return;
        }
        logStart();
        employeeDao.createEntry(role, entry);
        logEnd();

    }

    @Override
    public void updateEntry(String role, String entry) {
        if (!isAdmin(role)) {
            System.out.println("Unauthorized");
            return;
        }
        logStart();
        employeeDao.updateEntry(role, entry);
        logEnd();
    }

    @Override
    public void deleteEntry(String role, String entry) {
        if (!isAdmin(role)) {
            System.out.println("Unauthorized");
            return;
        }
        logStart();
        employeeDao.updateEntry(role, entry);
        logEnd();
    }

    public void logStart() {
        System.out.println("[START]");
    }

    public void logEnd() {
        System.out.println("[END]");
    }

    public boolean isAdmin(String role) {
        return role.equals("admin");
    }

    public boolean isUser(String role) {
        return role.equals("user");
    }
}
