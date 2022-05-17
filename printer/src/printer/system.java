package printer;

import java.sql.SQLException;
import static printer.DATABASE.*;
public class system { 
    static int id;
    static String password;
    static person personObject;
    public search Search;
     system(){
         this.Search=new search();
     }
     public int login(int id, String password) throws SQLException {
        this.id=id;
        this.password=password;
        if (Search.searchWithIdPassword(id, password) > 0) {
            personResult.absolute(Search.searchWithIdPassword(id, password));
            if ("admin".equalsIgnoreCase(personResult.getString(5))) {
                this.personObject = new Admin();
                return 1;
            }else{
                this.personObject = new user();
                return 2;}
        }
        return 0;
    }
     public person getperson() throws SQLException {
        return this.personObject;
    }
    public String signUp(int id, String name, String password, String email) throws SQLException {
        int credit = 0, requestNumber = 0;
        double money = 0,price=0;
        boolean state = false;
        String document = "", task = "", date = "";
        String personcreate = "insert into person values(" + id + ",'" + name + "' ,'" + email + "', '" + password + "','user ')";
        statemenPerson.execute(personcreate);
        String requestCreate = "insert into request values(" + id + ",'" + password + "'," + credit + "," + money + "," + requestNumber + "," + state + ", '" + document + "','" + date + "','" + task + "'," + id + ")";
        statementRequest.execute(requestCreate);
        return "done";
    }

}
