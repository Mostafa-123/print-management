package printer;

import java.sql.*;

public class Printer  {
    public static void main(String[] args) throws SQLException {
        try {
            system systems=new system();
            systems.Search.database.conect();
            loginORSign logOrSign=new loginORSign();
            logOrSign.show();
            logOrSign.setLocation(400, 300);
            logOrSign.setVisible(true);
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());
        }
    }

}
 