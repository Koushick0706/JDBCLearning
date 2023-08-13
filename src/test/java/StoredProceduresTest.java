import org.junit.jupiter.api.Test;

import java.sql.*;

public class StoredProceduresTest {

    Connection connection = null;

    @Test
    public void executeINStoredProcedures(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_employees","root","Koushick@07");
            connection.setAutoCommit(false);
            System.out.println("Checking the salaries for Engineering department before updating..");
            Statement statement1 = connection.createStatement();
            String sql = "select * from employees where department='Engineering'";
            ResultSet resultSet = statement1.executeQuery(sql);
            while (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String salary = resultSet.getString("salary");
                System.out.println(firstName + " | " + lastName + " | " + salary);
            }
            System.out.println("Select Query Executed....");
            System.out.println("Updating the salaries for Engineering Department");
            CallableStatement statement = connection.prepareCall("{call increase_salary_in_department(?, ?)}");
            statement.setString(1,"Engineering");
            statement.setDouble(2,40000);
            statement.execute();
            System.out.println("Checking the salary after update");
            String sqlupdate = "select * from employees where department='Engineering'";
            ResultSet resultSet1 = statement1.executeQuery(sqlupdate);
            while (resultSet1.next()){
                String firstName = resultSet1.getString("first_name");
                String lastName = resultSet1.getString("last_name");
                String salary = resultSet1.getString("salary");
                System.out.println(firstName + " | " + lastName + " | " + salary);
            }
            System.out.println("Select Query Executed....");

            statement.close();
            statement1.close();
            connection.commit();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void executeOUTStoredProcedures(){

    }

    public void executeINOUTStoredProcedures(){

    }
}
