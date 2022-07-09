package com.project.hr.dl;
import java.sql.*;
public class DAOConnection
{
private DAOConnection(){}
static public Connection getConnection() throws DAOException
{
Connection connection=null;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
connection=DriverManager.getConnection
("jdbc:mysql://localhost:3306/hr","hr","hr");
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return connection;
}
}