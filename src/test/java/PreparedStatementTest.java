import org.junit.jupiter.api.Test;

import java.sql.*;

public class PreparedStatementTest {

    Connection connection = null;

    @Test
    public void executePreparedStatements(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_employees","root","Koushick@07");
            connection.setAutoCommit(false);
            String sql = "select * from employees where salary > ? and department=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1,50000);
            statement.setString(2,"Engineering");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String salary = resultSet.getString("salary");
                System.out.println(firstName + " | " + lastName + " | " + salary);
            }
            System.out.println("Prepared Statements Executed...");
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
