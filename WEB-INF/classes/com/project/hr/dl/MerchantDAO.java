package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
import java.text.*;
public class MerchantDAO
{
public void add(MerchantDTO merchant) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from merchant where merchant_name=?");
preparedStatement.setString(1,merchant.getMerchantName());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Merchant :"+merchant.getMerchantName()+" exists.");
}
String merchantContact=merchant.getMerchantContact();
String merchantName=merchant.getMerchantName();
String merchantDeliveryBoyContact=merchant.getMerchantDeliveryBoyContact();
preparedStatement=connection.prepareStatement("select merchant_number from merchant where merchant_name=?");
preparedStatement.setString(1,merchantName);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Merchant Name already exists :"+merchantName);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select merchant_number from merchant where merchant_contact=?");
preparedStatement.setString(1,merchantContact);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("This contact already exists :"+merchantContact);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select merchant_number from merchant where merchant_delivery_boy_contact=?");
preparedStatement.setString(1,merchantDeliveryBoyContact);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("This contact already exists :"+merchantDeliveryBoyContact);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into merchant(merchant_name,merchant_address,merchant_contact,merchant_delivery_boy_name,merchant_delivery_boy_contact) values(?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,merchant.getMerchantName());
preparedStatement.setString(2,merchant.getMerchantAddress());
preparedStatement.setString(3,merchant.getMerchantContact());
preparedStatement.setString(4,merchant.getMerchantDeliveryBoyName());
preparedStatement.setString(5,merchant.getMerchantDeliveryBoyContact());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int merchantNumber=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
merchant.setMerchantNumber(merchantNumber);
}catch(SQLException sql)
{
throw new DAOException(sql.getMessage());
}
}
public List<MerchantDTO> getAll() throws DAOException
{
List<MerchantDTO> merchants;
merchants=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from merchant order by merchant_name");
MerchantDTO merchantDTO;
int merchantNumber;
String merchantName;
String merchantAddress;
String merchantContact;
String merchantDeliveryBoyName;
String merchantDeliveryBoyContact;
while(resultSet.next())
{
merchantNumber=resultSet.getInt("merchant_number");
merchantName=resultSet.getString("merchant_name").trim();
merchantAddress=resultSet.getString("merchant_address").trim();
merchantContact=resultSet.getString("merchant_contact").trim();
merchantDeliveryBoyName=resultSet.getString("merchant_delivery_boy_name").trim();
merchantDeliveryBoyContact=resultSet.getString("merchant_delivery_boy_contact").trim();
merchantDTO=new MerchantDTO();
merchantDTO.setMerchantNumber(merchantNumber);
merchantDTO.setMerchantName(merchantName);
merchantDTO.setMerchantAddress(merchantAddress);
merchantDTO.setMerchantContact(merchantContact);
merchantDTO.setMerchantDeliveryBoyName(merchantDeliveryBoyName);
merchantDTO.setMerchantDeliveryBoyContact(merchantDeliveryBoyContact);
merchants.add(merchantDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());
}
return merchants;
}
public MerchantDTO getByMerchantNumber(int merchantNumber) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from merchant where merchant_number=?");
preparedStatement.setInt(1,merchantNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid merchantNumber :"+merchantNumber);
}
String merchantName;
String merchantAddress;
String merchantContact;
String merchantDeliveryBoyName;
String merchantDeliveryBoyContact;
MerchantDTO merchantDTO=new MerchantDTO();
merchantDTO.setMerchantNumber(resultSet.getInt("merchant_number"));
merchantDTO.setMerchantName(resultSet.getString("merchant_name").trim());
merchantDTO.setMerchantAddress(resultSet.getString("merchant_address").trim());
merchantDTO.setMerchantContact(resultSet.getString("merchant_contact").trim());
merchantDTO.setMerchantDeliveryBoyName(resultSet.getString("merchant_delivery_boy_name").trim());
merchantDTO.setMerchantDeliveryBoyContact(resultSet.getString("merchant_delivery_boy_contact").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return merchantDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
/*
public void update(MerchantDTO merchant) throws DAOException
{
try
{
int merchantNumber=merchant.getMerchantNumber();
String merchantName=merchant.getMerchantName();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from merchant where merchantNumber=?");
preparedStatement.setInt(1,merchantNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid merchantNumber :"+merchantNumber);
}
resultSet.close();
preparedStatement.close();

String merchantContact=merchant.getMerchantContact();
preparedStatement=connection.prepareStatement("select merchantNumber from merchant where merchantName=? and merchantNumber<>?");
preparedStatement.setString(1,merchantName);
preparedStatement.setInt(2,merchantNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Merchant Name exists :"+merchantName);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select merchantNumber from merchant where merchantContact=? and merchantNumber<>?");
preparedStatement.setString(1,merchantContact);
preparedStatement.setInt(2,merchantNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Merchant Contact exists :"+merchantContact);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update merchant set merchantName=?,totalBeds=?,bedsAvailable=?,totalVentilators=?,ventilatorsAvailable=?,totalICU=?,icuAvailable=?,merchantContact=? where merchantContact=?");
preparedStatement.setString(1,merchant.getMerchantName());
preparedStatement.setInt(2,merchant.getTotalBeds());
preparedStatement.setInt(3,merchant.getBedsAvailable());
preparedStatement.setInt(4,merchant.getTotalVentilators());
preparedStatement.setInt(5,merchant.getVentilatorsAvailable());
preparedStatement.setInt(6,merchant.getTotalICU());
preparedStatement.setInt(7,merchant.getICUAvailable());
preparedStatement.setString(8,merchant.getMerchantContact());
preparedStatement.setInt(9,merchantNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}*/
public void deleteByMerchantNumber(int merchantNumber) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select merchant_number from merchant where merchant_number=?");
preparedStatement.setInt(1,merchantNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid merchantNumber :"+merchantNumber);
}
resultSet.close();
preparedStatement.close();
/*
preparedStatement=connection.prepareStatement("select customer_name from customer where merchant_number=?");
preparedStatement.setInt(1,merchantNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Cannot delete merchant as it has been alloted to customer");
}
*/
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from merchant where merchant_number=?");
preparedStatement.setInt(1,merchantNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public boolean merchantNumberExists(int merchantNumber) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select merchant_number from merchant where merchant_number=?");
preparedStatement.setInt(1,merchantNumber);
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