import org.junit.jupiter.api.*;

import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SQLBasicQueries {

    Connection connection = null;

    @Test
    @Order(1)
    public void selectQuery(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_employees","root","Koushick@07");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "select * from employees";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String salary = resultSet.getString("salary");
                System.out.println(firstName + " | " + lastName + " | " + salary);
            }
            System.out.println("Select Query Executed..");
            statement.close();
            connection.commit();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    @Order(2)
    public void InsertQuery(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_employees","root","Koushick@07");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO employees (id,last_name,first_name,email, department, salary) VALUES (14,'Kumar','Dinesh','dinesh.kumar@foo.com', 'HR', 70000.00)";
            int rowsaffected = statement.executeUpdate(sql);
            System.out.println("The Number of rows affected : " + rowsaffected);
            System.out.println("Insert Query Updated..");
            System.out.println("Checking the table after the update...");
            String getnewrecord = "select * from employees where last_name='Kumar'";
            ResultSet resultSet = statement.executeQuery(getnewrecord);
            while (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String salary = resultSet.getString("salary");
                System.out.println(firstName + " | " + lastName + " | " + salary);
            }
            System.out.println("Select Query Executed...");
            statement.close();
            connection.commit();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(3)
    public void UpdateQuery(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_employees","root","Koushick@07");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            System.out.println("Calling Select Query Before Updating...");
            String getallrecords = "select * from employees";
            ResultSet resultSetget = statement.executeQuery(getallrecords);
            while (resultSetget.next()){
                String firstName = resultSetget.getString("first_name");
                String lastName = resultSetget.getString("last_name");
                String salary = resultSetget.getString("salary");
                System.out.println(firstName + " | " + lastName + " | " + salary);
            }
            System.out.println("Select Query Executed...");
            System.out.println("Updating The Record...");
            String updateSql = "update employees set salary=85000 where first_name='Dinesh' and last_name='Kumar'";
            int rowsaffected = statement.executeUpdate(updateSql);
            System.out.println("The Number of rows affected : " + rowsaffected);
            System.out.println("Update Query Executed...");
            System.out.println("Checking the table after the update..");
            String sql = "select * from employees where first_name='Dinesh' and last_name='Kumar'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String salary = resultSet.getString("salary");
                System.out.println(firstName + " | " + lastName + " | " + salary);
            }
            System.out.println("Select Query Executed...");
            statement.close();
            connection.commit();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(4)
    public void DeleteQueries(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_employees","root","Koushick@07");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String deleteSql = "delete from employees where first_name='Dinesh' and last_name='Kumar'";
            int rowsupdated = statement.executeUpdate(deleteSql);
            System.out.println("The Number of rows Affected : " + rowsupdated);
            System.out.println("Delete Query Executed..");
            System.out.println("Checking the table after the update..");
            String getdeletedata = "select * from employees where first_name='Dinesh' and last_name='Kumar'";
            ResultSet resultSet = statement.executeQuery(getdeletedata);
            if(!resultSet.next()){
                System.out.println("Employee Record is not Found. please change your query..");
            }
            statement.close();
            connection.commit();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
