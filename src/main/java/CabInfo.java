/**
 * Created by sravan on 10/30/16.
 */
public class CabInfo {

    public String thisCab;
    public String thisDriver;

    public CabInfo(String cab, String driver) {
        this.thisCab = cab;
        this.thisDriver = driver;
    }

    public String insertQuery() {
        System.out.println("INSERT INTO CABS (cabName, cabDriver) VALUES (“" +this.thisCab +"”,“"+ this.thisDriver + "”)");
        return "INSERT INTO CABS (cabName, cabDriver) VALUES (\"" +this.thisCab +"\",\""+ this.thisDriver + "\")";
    }

    public String retriveQuery() {
        return "SELECT * FROM CABS";
    }
}
