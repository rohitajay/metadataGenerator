import java.io.FileWriter;
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
            String query = "SELECT *\n" +
                    "  FROM information_schema.columns\n" +
                    " WHERE table_schema = 'public';";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<String> column_list = new ArrayList<String>();
            for (int i = 1; i < columnCount; i++) {
                column_list.add(rsmd.getColumnLabel(i));
            }
            System.out.println(column_list);
            List<List<String>> value_list = new ArrayList<>();
            while (resultSet.next()) {
                List<String> row_string = new ArrayList<String>();

                for (int j = 0; j < columnCount - 1; j++) {

                    row_string.add(resultSet.getString(column_list.get(j)));
                    value_list.add(row_string);
                }
            System.out.println(value_list);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }

        public static void main(String[] args) {
            connect();
        }
}
