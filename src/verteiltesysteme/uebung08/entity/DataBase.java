package verteiltesysteme.uebung08.entity;

/**
 * Created by 陈香甦 on 2017/6/13.
 */
public class DataBase {
    private String driver = "com.mysql.jdbc.Driver";
    private String dbName = "vs-08";
    private String passwrod = "vs-08-pw";
    private String userName = "vs-08";
    private String url = "jdbc:mysql://im-vm-011:3306/" + dbName;

    public DataBase(String driver, String dbName, String passwrod, String userName, String url) {
        this.driver = driver;
        this.dbName = dbName;
        this.passwrod = passwrod;
        this.userName = userName;
        this.url = url;
    }
}
