package LowLevelSystemDesign.FactoryDesignPattern.FactoryPattern.DatabaseConnectorsExample;

public class MySQLConnector implements DatabaseConnector {
    @Override
    public void connect() {
        System.out.println("Connected to MySQL database");
    }
    @Override
    public void disconnect() {
        System.out.println("Disconnected from MySQL database");
    }
}
