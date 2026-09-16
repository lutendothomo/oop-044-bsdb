package za.co.wethinkcode.courier;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSchema {

    private DatabaseSchema() {
    }

    public static void createSchema(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
                 statement.execute("""
                         CREATE TABLE clients(
                             id INTEGER PRIMARY KEY,
                             full_name TEXT NOT NULL,
                             phone TEXT NOT NULL
                         )
                         """);
                 statement.execute("""
                    CREATE  TABLE shipments(
                        id INTEGER PRIMARY KEY,
                        weight_kg REAL NOT NULL,
                        delivery_method TEXT NOT NULL,
                        client_id INTEGER NOT NULL,
                        FOREIGN KEY(client_id) REFERENCES clients(id)    
                    )
                    """);


            // TODO: create the `clients` and `shipments` tables described in
            // resources/erd.png, including the primary key on each table, the
            // NOT NULL constraints, and the foreign key from
            // shipments.client_id to clients.id.
            //
            // Execute one CREATE TABLE statement per table, like this:
            //
            //     statement.execute("""
            //             CREATE TABLE ...
            //             """);
        }
    }
}
