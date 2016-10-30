import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
//        CabInfo c1 = new CabInfo("John Doe", "Taxi1");
//        Thread t1 = new Thread(new DbGet(c1));
//
//        CabInfo c2 = new CabInfo("Peter Jackson", "STaxi1");
//        Thread t2 = new Thread(new DbGet(c2));
//
//        CabInfo c3 = new CabInfo("Alright", "Lets go");
//        Thread t3 = new Thread(new DbGet(c3));
//
//        t1.start();
//        t2.start();
//        t3.start();


        CabInfo c4 = new CabInfo("Doe", "ABCTaxi1");
        Thread t4 = new Thread(new DbPut(c4));

        CabInfo c5 = new CabInfo("Jackson", "ZXCSTaxi1");
        Thread t5 = new Thread(new DbPut(c5));

        CabInfo c6 = new CabInfo("John", "QWE");
        Thread t6 = new Thread(new DbPut(c6));

        t5.start();
        t6.start();
        t4.start();
    }
}

class DbGet implements Runnable {

    CabInfo thisCab;

    public DbGet(CabInfo myCab) throws SQLException {
        this.thisCab = myCab;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is running");
            Database db = new Database();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(this.thisCab.retriveQuery());
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            int columnsCount = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsCount; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(Thread.currentThread().getName() + ":" + rsmd.getColumnName(i) + ": " + columnValue + "");
                }
                System.out.println("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class DbPut implements Runnable {
   CabInfo thisCab;

    public DbPut(CabInfo myCab) {
        this.thisCab = myCab;
    }
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is running");
            while(!DbInsert.checkIfFree()) {
                Thread.sleep(10);
            }
            DbInsert.takeMe(Thread.currentThread().getName());
            Connection conn = DbInsert.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(this.thisCab.insertQuery());
            DbInsert.freeMe(Thread.currentThread().getName());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
