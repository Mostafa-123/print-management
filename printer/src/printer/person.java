
package printer;

import java.sql.SQLException;
import static printer.DATABASE.*;

public class person extends system {
    public String getId(String name, String email) throws SQLException {
        if (Search.searchWithNameEmail(name, email) > 0) {
            personResult.absolute(Search.searchWithNameEmail(name, email));
            return "your Id=" + personResult.getInt(1);
        }
        return "not found";
    }

    public String setId(String name, String email, int id) throws SQLException {
        if (Search.searchWithNameEmail(name, email) > 0) {
            personResult.absolute(Search.searchWithNameEmail(name, email));
            personResult.updateInt(1, id);
            personResult.updateRow();
            resultRequest.absolute(Search.searchWithNameEmail(name, email));
            resultRequest.updateInt(1, id);
            resultRequest.updateRow();
            return "done";
        }
        return "not found";
    }

    public String getName(int id, String email) throws SQLException {
        if (Search.searchWithIdEmail(id, email) > 0) {
            personResult.absolute(Search.searchWithIdEmail(id, email));
            return "name=" + personResult.getString(2);
        }

        return "not found";
    }

    public String setName(String newName, String email, int id) throws SQLException {
        if (Search.searchWithIdEmail(id, email) > 0) {
            personResult.absolute(Search.searchWithIdEmail(id, email));
            personResult.updateString(2, newName);
            personResult.updateRow();
            return "done";
        }

        return "not found";
    }

    public String getEmail(int id, String name) throws SQLException {
        personResult = statemenPerson.executeQuery(person);
        resultRequest = statementRequest.executeQuery(request);
        if (Search.searchWithIdName(id, name) > 0) {
            personResult.absolute(Search.searchWithIdName(id, name));
            return "email=" + personResult.getString(3);
        }

        return "not found";
    }

    public String setEmail(String name, String newEmail, int id) throws SQLException {
        if (Search.searchWithIdName(id, name) > 0) {
            personResult.absolute(Search.searchWithIdName(id, name));
            personResult.updateString(3, newEmail);
            personResult.updateRow();
            return "done";
        }

        return "not found";
    }
    public String updatepassword(int id, String oldPassword, String newPassword) throws SQLException {
        if (Search.searchWithIdPassword(id, oldPassword) > 0) {
            personResult.absolute(Search.searchWithIdPassword(id, oldPassword));
            personResult.updateString(4, newPassword);
            personResult.updateRow();
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, oldPassword));
            resultRequest.updateString(2, newPassword);
            resultRequest.updateRow();
            return "done";
        }

        return "not found";

    }

    public String getCredit(int id, String password) throws SQLException {
        personResult = statemenPerson.executeQuery(person);
        resultRequest = statementRequest.executeQuery(request);
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            return "credit=" + resultRequest.getInt(3);
        }

        return "not found";
    }

    public String setCredit(int creditNumber, String password, int id) throws SQLException {
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            resultRequest.updateInt(3, creditNumber);
            resultRequest.updateRow();
            return "done";
        }

        return "not found";
    }
    public double getMoney(int id, String password) throws SQLException {
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            if (resultRequest.getInt(3) != 0) {
                return resultRequest.getDouble(4);
            }
        }
        return 0;
    }

}
