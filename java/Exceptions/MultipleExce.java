import java.io.IOException;
import java.sql.SQLException;

public class MultipleExce {

    static void thrower(boolean throwIOException) throws IOException, SQLException {
        if (throwIOException) {
            throw new SQLException("SQL");
            
        } else{
            throw new IOException("IO");
        }
    }
    

    public static void main(String[] args) {
        try {
            thrower(true);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
