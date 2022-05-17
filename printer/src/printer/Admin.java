package printer;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import static printer.DATABASE.*;
public class Admin extends user {
     Admin() {
    }
    public String addPerson(int id, String name, String password, String email, String Jop) throws SQLException {
        int credit = 0, requestNumber = 0;
        double money = 0,price=0;
        boolean state = false;
        String document = "", task = "", date = "";
        String query = "insert into person values(" + id + ",'" + name + "' ,'" + email + "', '" + password + "' ,'" + Jop + "')";
        statemenPerson.execute(query);
        String requestCreate = "insert into request values(" + id + ",'" + password + "'," + credit + "," + money + "," + requestNumber + "," + state + ", '" + document + "','" + date + "','" + task + "'," + id + ")";
        statementRequest.execute(requestCreate);
        return "done";
    }

    public String deletePerson(int id) throws SQLException {
        personResult.beforeFirst();
        while (personResult.next()) {
            if (id == personResult.getInt(1)) {
                personResult.deleteRow();
                deletePersonRequest(id);
                return "done";
            }
        }
        return "not found";

    }
    public void deletePersonRequest(int id) throws SQLException {
        resultRequest.beforeFirst();
        while (resultRequest.next()) {
            if (id == resultRequest.getInt(1)) {
                resultRequest.deleteRow();
            }
        }
    }

    public String searchPerson(int id, String name) throws SQLException {
        if (Search.searchWithIdName(id, name) > 0) {
            personResult.absolute(Search.searchWithIdName(id, name));
            return "name=" + personResult.getString(2) + ":email=" + personResult.getString(3) + ":password=" + personResult.getString(4);
        }

        return "not found";
    }

    public String addJop(int id, String email, String jop) throws SQLException {
        if (Search.searchWithIdEmail(id, email) > 0) {
            personResult.absolute(Search.searchWithIdEmail(id, email));
            personResult.updateString(5, jop);
            personResult.updateRow();
            return "done";
        }

        return "not found";
    }

    public void print(DefaultTableModel defaults) throws SQLException {
        personResult.beforeFirst();
        resultRequest.beforeFirst();
        defaults.addColumn("id");
        defaults.addColumn("name");
        defaults.addColumn("email");
        defaults.addColumn("jop");
        defaults.addColumn("credit number");
        defaults.addColumn("money");
        defaults.addColumn("request number");
        defaults.addColumn("request state");
        defaults.addColumn("request document");
        defaults.addColumn("task");
        while (personResult.next()&&resultRequest.next()) {
           defaults.addRow(new Object[] {personResult.getInt(1),personResult.getString(2),personResult.getString(3),personResult.getString(5),resultRequest.getInt(3),resultRequest.getDouble(4),resultRequest.getInt(5),resultRequest.getBoolean(6),resultRequest.getString(7),resultRequest.getString(9)});
        }

    }

    public String task(int id, String task) throws SQLException {
        resultRequest.beforeFirst();
        while (resultRequest.next()) {
            if (id == resultRequest.getInt(1)) {
                resultRequest.updateString(9, task);
                resultRequest.updateRow();
                return "done";
            }
        }
        return "not found";
    }

    public String setMoney(int id, String password, double money) throws SQLException {
        double collect;
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            collect=resultRequest.getDouble(4)+money;
            resultRequest.updateDouble(4, collect);
            resultRequest.updateRow();
            return "done";
        }

        return "you not have an email";
    }

    public String approveRequest(int id, int requestNumber,double moneyOfDocument) throws SQLException {
        if (Search.serchWithIdREquestNumber(id, requestNumber) > 0) {
            resultRequest.absolute(Search.serchWithIdREquestNumber(id, requestNumber));
            resultRequest.updateBoolean(6, true);
            resultRequest.updateDouble(10, moneyOfDocument);
            resultRequest.updateRow();
            return "done";
        }

        return "not found";
    }

    public String disapproveRequest(int id, int requestNumber) throws SQLException {
        if (Search.serchWithIdREquestNumber(id, requestNumber) > 0) {
            resultRequest.absolute(Search.serchWithIdREquestNumber(id, requestNumber));
            resultRequest.updateBoolean(6, false);
            resultRequest.updateRow();
            return "done";
        }
        return "not found";
    }

}
