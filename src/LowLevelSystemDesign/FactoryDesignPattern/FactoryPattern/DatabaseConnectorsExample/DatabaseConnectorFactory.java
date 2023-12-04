package LowLevelSystemDesign.FactoryDesignPattern.FactoryPattern.DatabaseConnectorsExample;

public class DatabaseConnectorFactory {
    public static DatabaseConnector createConnector(String databaseType) {
        switch (databaseType.toLowerCase()) {
            case "mysql":
                return new MySQLConnector();
            case "postgresql":
                return new PostgreSQLConnector();
            default:
                throw new IllegalArgumentException("Unsupported database type: " + databaseType);
        }
    }
}
