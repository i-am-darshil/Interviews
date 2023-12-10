package LowLevelSystemDesign.ProxyDesignPattern;

public class Main {
    public static void main(String[] args) {
        EmployeeDao employeeDaoProxy = new EmployeeDaoProxy(new EmployeeDaoImpl());

        employeeDaoProxy.createEntry("admin", "entry1");
        employeeDaoProxy.updateEntry("user", "entry1");
        employeeDaoProxy.deleteEntry("user", "entry1");
    }
}
