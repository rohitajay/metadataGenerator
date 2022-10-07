package testdb;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class metaData {
    public static final String CONNECTION_STRING = "jdbc:postgresql://127.0.0.1:5432/abc";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";

    public static void connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING, "postgres", "9419131674");
            Statement statement = conn.createStatement();
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String query = "SELECT table_catalog, table_schema, table_name, column_name, ordinal_position, column_default, is_nullable, data_type, character_maximum_length,numeric_precision, udt_catalog \n" +
                    "  FROM information_schema.columns\n" +
                    " WHERE table_schema = 'public' LIMIT 5;";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.println(columnCount);
            List<String> column_list = new ArrayList<String>();
            for (int i = 1; i < columnCount; i++) {
                column_list.add(rsmd.getColumnLabel(i));
            }
            System.out.println(column_list);
            List<List<String>> value_list = new ArrayList<>();
            List<String> row_string = new ArrayList<>();

            while (resultSet.next()) {
                List<String> single_row = new ArrayList<>();
                for (String element: column_list){
                    single_row.add(resultSet.getString(element));
                    }
//
                value_list.add(single_row);
                }
            System.out.println(value_list);

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }

        public static void main(String[] args) {
            connect();
        }
}
