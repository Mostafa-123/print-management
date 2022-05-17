package printer;

import java.sql.SQLException;
import static printer.DATABASE.*;

public class payment {
    search Search;
    payment(){Search=new search();}
    public String payCash(int id,String password) throws SQLException{
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            return "pay "+resultRequest.getDouble(10)+"to cashir";
    }
        return "no";
}
    public int payWithCredit(int id,String password) throws SQLException{
        double minus;
        if (Search.requestSearchWithIdPassword(id, password) > 0) {
            resultRequest.absolute(Search.requestSearchWithIdPassword(id, password));
            if(resultRequest.getDouble(4)>resultRequest.getDouble(10)){
                minus=resultRequest.getDouble(4)-resultRequest.getDouble(10);
                resultRequest.updateDouble(4, minus);
                resultRequest.updateRow();
                return 1;
            }
    }     
        return 0;
}
}
