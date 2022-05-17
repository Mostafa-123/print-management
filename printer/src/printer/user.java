package printer;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import static printer.DATABASE.*;
public class user extends person {
    payment Pay;
    public user(){Pay=new payment();}
    public String request(int id, String password, String document) throws SQLException {
        Random randomNumber = new Random();
        Date dateOfRequest = new Date();
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            if (resultRequest.getInt(5) != 0) {
                return "you must wait until your old printing requset is vrefied";
            } else {
                resultRequest.updateInt(5, randomNumber.nextInt(100));
                resultRequest.updateBoolean(6, false);
                resultRequest.updateString(8, dateOfRequest.toString());
                resultRequest.updateString(7, document);
                resultRequest.updateRow();
                return "you request number is "+resultRequest.getInt(5);
            }
        }

        return "not found";
    }

    public String deleteRequest(int id, String password) throws SQLException {
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            if (resultRequest.getInt(5) != 0) {
                resultRequest.updateInt(5, 0);
                resultRequest.updateBoolean(6, false);
                resultRequest.updateString(8, "");
                resultRequest.updateString(7, "");
                resultRequest.updateDouble(10, 0);
                resultRequest.updateRow();
                return "done";
            }
        }

        return "not found";
    }
    public String uploadDoc(int id, String password, String document) throws SQLException {
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            resultRequest.updateString(7, document);
            resultRequest.updateRow();
            return "found";
        }
        return "not found";
    }

    public String getDocument(int id, String password) throws SQLException {
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            if (resultRequest.getString(7) != null) {
                return "your doc is      " + resultRequest.getString(7);
            }
        }
        return "you dont have printing request";
    }

    public Boolean status(int id, String password) throws SQLException {
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            if (resultRequest.getInt(5) != 0) {
                return resultRequest.getBoolean(6);
            }
        }
        return false;
    }
    public String printDocumentWithCash(int id, String password) throws SQLException {
        String doc;
        if (status(id, password)) {
            doc = Pay.payCash(id, password) +"/n"+getDocument(id, password) + "take it from printer";
            deleteRequest(id, password);
            return doc;
        }
        return "your request is not aproved until now please wait";
    }

    public String printDocumentWithCredit(int id, String password) throws SQLException {
        String doc;
        if (status(id, password)) {
            if (Pay.payWithCredit(id, password) == 1) {
                doc = getDocument(id, password) + "take it from printer";
                deleteRequest(id, password);
                return doc;
            } else {
                return "your money is less than document price";
            }
        }
        return "your request is not aproved until now please wait";
    }
    public String getRequest(int id, String password) throws SQLException {
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            if (resultRequest.getInt(5) != 0) {
                return "your requestNum=" + resultRequest.getInt(5) + "your requestStatus=" + resultRequest.getBoolean(6) + "your document=" + resultRequest.getString(7) + "you request date=" + resultRequest.getString(8);
            }
        }
        return "you dont have printing request";
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
