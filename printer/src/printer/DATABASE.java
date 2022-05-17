package printer;
import java.sql.*;
public class DATABASE {
    static DATABASE databaseObject=new DATABASE();
    private static String user = "root";
    private static String password = "";
    private static String adress = "jdbc:mysql://localhost/printer";
    public static String person = "select * from person";
    public static String request = "select * from request";
    private static Connection connect;
    public static Statement statemenPerson;
    public static Statement statementRequest;
    public static ResultSet personResult;
    public static ResultSet resultRequest;
    public static DATABASE getInstance(){
        return databaseObject;
    }
    private DATABASE(){}
    public Connection conect() throws SQLException{
        connect = DriverManager.getConnection(adress, user, password);
        statemenPerson = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statementRequest = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        personResult = statemenPerson.executeQuery(person);
        resultRequest = statementRequest.executeQuery(request);
        return null;
}
}
