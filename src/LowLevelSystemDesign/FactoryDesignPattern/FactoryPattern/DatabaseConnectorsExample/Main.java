package LowLevelSystemDesign.FactoryDesignPattern.FactoryPattern.DatabaseConnectorsExample;

/*
The factory design pattern is used to create objects without specifying the exact class of the object that will be created.
It provides an interface for creating instances of a class, but allows subclasses to alter the type of objects that will be created.
This promotes loose coupling and flexibility in object creation,
making it easier to adapt to different scenarios or change the implementation of object creation without modifying the client code.
 */
public class Main {
    public static void main(String[] args) {
        // Create MySQL connector
        DatabaseConnector mySQLConnector = DatabaseConnectorFactory.createConnector("mysql");
        mySQLConnector.connect();
        mySQLConnector.disconnect();
        // Create PostgreSQL connector
        DatabaseConnector postgreSQLConnector = DatabaseConnectorFactory.createConnector("postgresql");
        postgreSQLConnector.connect();
        postgreSQLConnector.disconnect();
    }
}
