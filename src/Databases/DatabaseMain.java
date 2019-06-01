package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.UpdateResult;

public class DatabaseMain {
	
	public static Connection getConnection(String dbmsHost, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbmsHost,user,password);
	}
	
	static void printAndUpdateResultset(ResultSet resultset) throws SQLException {
		System.out.println("---- Print the result ----");
		while(resultset.next()) {
			int size = resultset.getMetaData().getColumnCount();
			for(int i=1;i<=size;i++) {
				System.out.print("(");
				System.out.print(resultset.getMetaData().getColumnName(i));
				System.out.print(", ");
				System.out.print(resultset.getMetaData().getColumnType(i));
				System.out.print("): ");
				System.out.println(resultset.getObject(i).toString());
			}
			if(resultset.getString("course_name").equals("AP")) {
				int id = resultset.getInt("course_id");
				resultset.updateInt("course_id", id+1);
				resultset.updateRow();
			}
			System.out.println("---- End of row ----");
		}
		System.out.println("---- End of result ----");
	}
	
	static void printResultset(ResultSet resultset) throws SQLException {
		System.out.println("---- Print the result ----");
		while(resultset.next()) {
			int size = resultset.getMetaData().getColumnCount();
			for(int i=1;i<=size;i++) {
				System.out.print("(");
				System.out.print(resultset.getMetaData().getColumnName(i));
				System.out.print(", ");
				System.out.print(resultset.getMetaData().getColumnType(i));
				System.out.print("): ");
				System.out.println(resultset.getObject(i).toString());
			}
			System.out.println("---- End of row ----");
		}
		System.out.println("---- End of result ----");
	}
	
	public static ResultSet selectQuery(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
        return statement.executeQuery("select * from courses where course_name like '%'");
	}
	
	public static ResultSet updatableResultSet(Connection connection) throws SQLException {
		Statement statement = connection.createStatement(
			    //ResultSet.TYPE_FORWARD_ONLY,
			    ResultSet.TYPE_SCROLL_INSENSITIVE,
			    //ResultSet.CONCUR_READ_ONLY,
			    ResultSet.CONCUR_UPDATABLE,
			    ResultSet.CLOSE_CURSORS_AT_COMMIT
			   );
        return statement.executeQuery("select * from courses where course_name like '%'");
	}
	
	public static int updateQuery(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
        return statement.executeUpdate("update courses set course_name = CONCAT(course_name,'-') where course_name like '%s'");
	}
	
	public static int insertQuery(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
        return statement.executeUpdate("insert into courses (course_id, course_name, units) values(10,'NewCourse',3)");
	}
	
	public static int deleteQuery(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
        return statement.executeUpdate("delete from courses where course_name like 'NewCourse%'");
	}
	
	public static int updateQueryCancel(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
        return statement.executeUpdate("update courses set course_name = SUBSTRING(course_name, 1, LENGTH(course_name)-1) where course_name like '%s-'");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String dbmsHost = "jdbc:mysql://localhost:3306/ap_db";
        String user     = "root";
        String password = "";
        
        Connection connection = getConnection(dbmsHost, user, password);
        
        System.out.println("Initial table");
        ResultSet resultset = selectQuery(connection);
        printResultset(resultset);
   
        insertQuery(connection);
        updateQuery(connection);
        
        System.out.println("After update");
        resultset = selectQuery(connection);
        printResultset(resultset);
        
        updateQueryCancel(connection);
        
        System.out.println("After cancel update");
        resultset = selectQuery(connection);
        printResultset(resultset);
        
        deleteQuery(connection);
        
        resultset = updatableResultSet(connection);
        printAndUpdateResultset(resultset);
	}
}
