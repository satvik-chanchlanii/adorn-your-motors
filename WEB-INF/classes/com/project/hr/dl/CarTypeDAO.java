package com.project.hr.dl;
import java.sql.*;
import java.util.*;
public class CarTypeDAO
{
public void add(CarTypeDTO carType) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from carType where car_type_name=?");
preparedStatement.setString(1,carType.getCarTypeName());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("CarType :"+carType.getCarTypeName()+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into carType (car_type_name) values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,carType.getCarTypeName());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int carTypeCode=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
carType.setCarTypeCode(carTypeCode);
}catch(SQLException sql)
{
throw new DAOException(sql.getMessage());
}
}
public List<CarTypeDTO> getAll() throws DAOException
{
List<CarTypeDTO> carTypes;
carTypes=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from carType order by car_type_name");
CarTypeDTO carTypeDTO;
int carTypeCode;
String carTypeName;
while(resultSet.next())
{
carTypeCode=resultSet.getInt("car_type_code");
carTypeName=resultSet.getString("car_type_name").trim();
carTypeDTO=new CarTypeDTO();
carTypeDTO.setCarTypeCode(carTypeCode);
carTypeDTO.setCarTypeName(carTypeName);
carTypes.add(carTypeDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());
}
return carTypes;
}
public CarTypeDTO getByTypeCode(int carTypeCode) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from carType where car_type_code=?");
preparedStatement.setInt(1,carTypeCode);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid carType carTypeCode :"+carTypeCode);
}
CarTypeDTO carTypeDTO=new CarTypeDTO();
carTypeDTO.setCarTypeCode(resultSet.getInt("car_type_code"));
carTypeDTO.setCarTypeName(resultSet.getString("car_type_name").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return carTypeDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public boolean carTypeCodeExists(int carTypeCode) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select car_type_code from cartype where car_type_code=?");
preparedStatement.setInt(1,carTypeCode);
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