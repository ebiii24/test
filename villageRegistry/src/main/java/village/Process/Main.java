package village.Process;

import java.sql.*;

import village.NoMatchFoundException.NoMatchFoundException;
import village.Utils.DBUtils;
public class Main {
    public static void main(String[] args) throws SQLException, NoMatchFoundException {
        DBUtils.getResidentByPartialName("test");
    }
}