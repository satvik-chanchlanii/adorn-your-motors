package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
import java.text.*;
public class AppointmentDAO
{
public void add(AppointmentDTO appointment) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
ResultSet resultSet;
preparedStatement=connection.prepareStatement("insert into appointment (appointment_name,appointment_address,appointment_contact,cars_services_codes,appointment_description) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,appointment.getAppointmentName());
preparedStatement.setString(2,appointment.getAppointmentAddress());
preparedStatement.setString(3,appointment.getAppointmentContact());
preparedStatement.setInt(4,appointment.getCarServiceCode());
preparedStatement.setString(5,appointment.getAppointmentDescription());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int appointmentNumber=resultSet.getInt(1);
appointment.setAppointmentNumber(appointmentNumber);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
throw new DAOException(sql.getMessage());
}
}
public boolean appointmentContactExists(String appointmentContact) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select appointment_name from appointment where appointment_contact=?");
preparedStatement.setString(1,appointmentContact);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
throw new DAOException(sql.getMessage());
}
return exists;
}
}