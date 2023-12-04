package LowLevelSystemDesign.FactoryDesignPattern.FactoryPattern.DatabaseConnectorsExample;

public class PostgreSQLConnector implements DatabaseConnector {
    @Override
    public void connect() {
        System.out.println("Connected to PostgreSQL database");
    }
    @Override
    public void disconnect() {
        System.out.println("Disconnected from PostgreSQL database");
    }
}
