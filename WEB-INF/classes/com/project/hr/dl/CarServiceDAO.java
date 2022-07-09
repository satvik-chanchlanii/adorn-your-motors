package com.project.hr.dl;
import java.sql.*;
import java.util.*;
public class CarServiceDAO
{
public void add(CarServiceDTO carService) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from carService where car_service_name=?");
preparedStatement.setString(1,carService.getCarServiceName());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("CarService :"+carService.getCarServiceName()+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into carService (car_service_name) values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,carService.getCarServiceName());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int carServiceCode=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
carService.setCarServiceCode(carServiceCode);
}catch(SQLException sql)
{
throw new DAOException(sql.getMessage());
}
}
public List<CarServiceDTO> getAll() throws DAOException
{
List<CarServiceDTO> carServices;
carServices=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from carService order by car_service_name");
CarServiceDTO carServiceDTO;
int carServiceCode;
String carServiceName;
while(resultSet.next())
{
carServiceCode=resultSet.getInt("car_service_code");
carServiceName=resultSet.getString("car_service_name").trim();
carServiceDTO=new CarServiceDTO();
carServiceDTO.setCarServiceCode(carServiceCode);
carServiceDTO.setCarServiceName(carServiceName);
carServices.add(carServiceDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());
}
return carServices;
}
public CarServiceDTO getByServiceCode(int carServiceCode) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from carService where car_service_code=?");
preparedStatement.setInt(1,carServiceCode);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid carService carServiceCode :"+carServiceCode);
}
CarServiceDTO carServiceDTO=new CarServiceDTO();
carServiceDTO.setCarServiceCode(resultSet.getInt("car_service_code"));
carServiceDTO.setCarServiceName(resultSet.getString("car_service_name").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return carServiceDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public boolean carServiceCodeExists(int carServiceCode) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select car_service_code from carservice where car_service_code=?");
preparedStatement.setInt(1,carServiceCode);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return exists;
}
}