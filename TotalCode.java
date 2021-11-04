import java.sql.*;
import java.util.*;
public class TotalCode {
  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);
      //establshing connection
      Class.forName("org.sqlite.JDBC");
      //creating db
      Connection con = DriverManager.getConnection("jdbc:sqlite:Movies.db");
      if(con != null) {
    	  //creating movie table in database
    	  Statement st=con.createStatement();
      	int n=st.executeUpdate("create table movies(name text,actorname text,actressname text,directorname text,releaseyear int)");
    	  //inserting values into movies table
    	  PreparedStatement pstmt = con.prepareStatement("insert into movies values(?,?,?,?,?)");
          String name=sc.next();
          String actorname=sc.next();
          String actressname=sc.next();
          String directorname=sc.next();
          int releaseyear=sc.nextInt();
          pstmt.setString(1,name);
          pstmt.setString(2,actorname);
          pstmt.setString(3,actressname);
          pstmt.setString(4,directorname);
          pstmt.setInt(5,releaseyear);
          int n = pstmt.executeUpdate();
          System.out.println(n);
        
        //querying data from movies table  
        PreparedStatement pstmt = con.prepareStatement("select * from movies");
        ResultSet rs = pstmt.executeQuery();
        System.out.println("Movie\t\t Actor\t\t Actress\t\t Director\t\t Releaseyear");
        System.out.println("_______________________________________________________________________________________________");
        while(rs.next()) {
          System.out.println(rs.getString(1)+"\t"+"\t"+rs.getString(2)+"\t"+"\t"+rs.getString(3)+"\t"+"\t"+rs.getString(4)+"\t"+"\t"+rs.getInt(5));
        }
        
        con.close();  
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}
