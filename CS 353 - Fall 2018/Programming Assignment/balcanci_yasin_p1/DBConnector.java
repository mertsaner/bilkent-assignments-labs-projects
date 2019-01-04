import java.sql.*;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class DBConnector {
    static String addStudent(String sid, String sname, String bdate, String telno, String scity, String year, double gpa){
        return "insert into student values(\""+sid+"\",\""+sname+"\",\""+bdate+"\",\""+telno+"\",\""+scity+"\",\""+year+"\","+gpa+");";
    }
    static String addCompany(String cid, String cname, int quota){
        return "insert into company values(\""+cid+"\",\""+cname+"\","+quota+");";
    }
    static String addApply(String sid, String cid){
        return "insert into apply values(\""+sid+"\",\""+cid+"\");";
    }
    public static void main(String args[]) throws Exception{
        Statement st1;
        ResultSet rs1;
        String createStudentTable = "create table if not exists student(sid char(12) primary key, sname varchar(50), bdate date," +
                "telno char(10), scity varchar(20), year char(20), gpa float) ENGINE=InnoDB;";
        String createCompanyTable = "create table if not exists company(cid char(8) primary key, cname varchar(20), quota int) ENGINE=InnoDB;";
        String createApplyTable = "create table if not exists apply(sid char(12), cid char(8), primary key(sid, cid), foreign key(sid) references student(sid), foreign key(cid) references company(cid)) ENGINE=InnoDB;";
        
        int lport=5656;
        int rport=3306;
        String rhost="localhost";
        String host="<YOUR_HOST_NAME>";
        String user="<USERNAME_TO_CONNECT_TO_HOST>";
        String password="<USERNAME_TO_CONNECT_TO_HOST>";
        String dbuserName = "<USERNAME_FOR_MYSQL_DATABASE>";
        String dbpassword = "<PASSWORD_FOR_MYSQL_DATABASE>";       
        String url = "jdbc:mysql://localhost:"+lport;
        String driverName="com.mysql.cj.jdbc.Driver";
        Connection conn = null;
        Session session= null;
        try{
            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            int server_port = 22;
            session=jsch.getSession(user, host, server_port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");
            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
            System.out.println("Port Forwarded");

            //mysql database connectivity
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection (url, dbuserName, dbpassword);
            System.out.println ("Database connection established");
            st1 = conn.createStatement();

            // Drop tables if they exist
            st1.executeUpdate("DROP TABLE if exists apply; ");
            st1.executeUpdate("DROP TABLE if exists student; ");
            st1.executeUpdate("DROP TABLE if exists company; ");

            // Create Tables
            st1.executeUpdate(createStudentTable);
            st1.executeUpdate(createCompanyTable);
            st1.executeUpdate(createApplyTable);
            //System.out.println("Tables are created");

            // Add values to tables
            st1.executeUpdate(addStudent("21000001", "Ayse", "1995-05-10", "5321113333", "Ankara", "senior", 2.75));
            st1.executeUpdate(addStudent("21000002", "Ali", "1997-12-09", "5355361234", "Istanbul", "junior", 3.44));
            st1.executeUpdate(addStudent("21000003", "Veli", "1998-10-25", "5553214455", "Istanbul", "freshman", 2.36));
            st1.executeUpdate(addStudent("21000004", "John", "1999-01-15", "5335336622", "Chicago", "freshman", 2.55));
            st1.executeUpdate(addCompany("C101", "tubitak", 2));
            st1.executeUpdate(addCompany("C102", "aselsan", 5));
            st1.executeUpdate(addCompany("C103", "havelsan", 3));
            st1.executeUpdate(addCompany("C104", "microsoft", 5));
            st1.executeUpdate(addCompany("C105", "merkez bankasi", 3));
            st1.executeUpdate(addCompany("C106", "tai", 4));
            st1.executeUpdate(addCompany("C107", "milsoft", 2));
            st1.executeUpdate(addApply("21000001", "C101"));
            st1.executeUpdate(addApply("21000001", "C102"));
            st1.executeUpdate(addApply("21000001", "C103"));
            st1.executeUpdate(addApply("21000002", "C101"));
            st1.executeUpdate(addApply("21000002", "C105"));
            st1.executeUpdate(addApply("21000003", "C104"));
            st1.executeUpdate(addApply("21000003", "C105"));
            st1.executeUpdate(addApply("21000004", "C107"));
            //System.out.println("Values are added.");

            // SELECT * FROM student
            rs1 = st1.executeQuery("SELECT * FROM student;");
            System.out.format("%15s\n", "student");
            while(rs1.next()){
                String sid = rs1.getString("sid");
                String sname = rs1.getString("sname");
                String bdate = rs1.getString("bdate");
                String telno = rs1.getString("telno");
                String scity = rs1.getString("scity");
                String year = rs1.getString("year");
                double gpa = rs1.getFloat("gpa");
                System.out.format("%15s%10s%15s%15s%15s%15s%15f\n", sid, sname, bdate, telno, scity, year, gpa);
            }

            System.out.println("DONE");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(conn != null && !conn.isClosed()){
                System.out.println("Closing Database Connection");
                conn.close();
            }
            if(session !=null && session.isConnected()){
                System.out.println("Closing SSH Connection");
                session.disconnect();
            }
        }
    }
}
