package za.co.wethinkcode.courier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseSchemaTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = Database.connect(":memory:");
        DatabaseSchema.createSchema(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    void clientsTable_hasIdAsPrimaryKey() throws SQLException {
        assertTrue(primaryKeyFlags("clients").get("id"));
    }

    @Test
    void clientsTable_fullNameAndPhoneAreNotNull() throws SQLException {
        Map<String, Boolean> notNull = notNullFlags("clients");
        assertTrue(notNull.get("full_name"));
        assertTrue(notNull.get("phone"));
    }

    @Test
    void shipmentsTable_hasIdAsPrimaryKey() throws SQLException {
        assertTrue(primaryKeyFlags("shipments").get("id"));
    }

    @Test
    void shipmentsTable_weightMethodAndClientIdAreNotNull() throws SQLException {
        Map<String, Boolean> notNull = notNullFlags("shipments");
        assertTrue(notNull.get("weight_kg"));
        assertTrue(notNull.get("delivery_method"));
        assertTrue(notNull.get("client_id"));
    }

    @Test
    void shipmentsTable_clientIdReferencesClients() throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("PRAGMA foreign_key_list(shipments)")) {

            assertTrue(resultSet.next(), "expected shipments to declare a foreign key");
            assertEquals("clients", resultSet.getString("table"));
            assertEquals("client_id", resultSet.getString("from"));
            assertEquals("id", resultSet.getString("to"));
        }
    }

    private Map<String, Boolean> primaryKeyFlags(String table) throws SQLException {
        return columnFlags(table, "pk");
    }

    private Map<String, Boolean> notNullFlags(String table) throws SQLException {
        return columnFlags(table, "notnull");
    }

    private Map<String, Boolean> columnFlags(String table, String flagColumn) throws SQLException {
        Map<String, Boolean> flags = new HashMap<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("PRAGMA table_info(" + table + ")")) {
            while (resultSet.next()) {
                flags.put(resultSet.getString("name"), resultSet.getInt(flagColumn) > 0);
            }
        }
        return flags;
    }
}
