package printer;

import java.sql.SQLException;
import static printer.DATABASE.*;

public class search  {
    DATABASE database;
    search(){
        database=DATABASE.getInstance();
    }
    public int searchWithNameEmail(String name, String email) throws SQLException {
        personResult.beforeFirst();
        while (personResult.next()) {
            if (email.equalsIgnoreCase(personResult.getString(3)) && name.equalsIgnoreCase(personResult.getString(2))) {
                return personResult.getRow();
            }
        }
        return 0;
    }

    public int searchWithIdName(int id, String name) throws SQLException {
        personResult.beforeFirst();
        while (personResult.next()) {
            if (id == personResult.getInt(1) && name.equalsIgnoreCase(personResult.getString(2))) {
                return personResult.getRow();
            }
        }

        return 0;

    }

    public int searchWithIdEmail(int id, String email) throws SQLException {
        personResult.beforeFirst();
        while (personResult.next()) {
            if (id == personResult.getInt(1) && email.equalsIgnoreCase(personResult.getString(3))) {
                return personResult.getRow();
            }
        }

        return 0;

    }

    public int searchWithIdPassword(int id, String password) throws SQLException {
        personResult.beforeFirst();
        while (personResult.next()) {
            if (id == personResult.getInt(1) && password.equalsIgnoreCase(personResult.getString(4))) {
                return personResult.getRow();
            }
        }

        return 0;

    }

    public int requestSearchWithIdPassword(int id, String password) throws SQLException {
        resultRequest.beforeFirst();
        while (resultRequest.next()) {
            if (id == resultRequest.getInt(1) && password.equalsIgnoreCase(resultRequest.getString(2))) {
                return resultRequest.getRow();
            }
        }

        return 0;

    }

    public int serchWithIdREquestNumber(int id, int requestNumber) throws SQLException {
        resultRequest.beforeFirst();
        while (resultRequest.next()) {
            if (id == resultRequest.getInt(1) && requestNumber == resultRequest.getInt(5)) {
                return resultRequest.getRow();
            }
        }
        return 0;
    }
}
