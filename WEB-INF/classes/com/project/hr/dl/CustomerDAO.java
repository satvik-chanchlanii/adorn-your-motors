package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
import java.text.*;
public class CustomerDAO
{
public List<CustomerDTO> getAll() throws DAOException
{
List<CustomerDTO> customers;
customers=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from customer order by customer.customer_name");
CustomerDTO customerDTO;
int customerId;
String customerName;
int merchantCode;
String merchantName="";
int carServiceCode;
int carTypeCode;
String carServiceName="";
String carTypeName="";
String customerContact;
String customerAddress;
String customerCarVehicleNumber;
String finalPricingToSend;
while(resultSet.next())
{
merchantCode=resultSet.getInt("merchant_codes");
customerId=resultSet.getInt("customer_id");
customerName=resultSet.getString("customer_name").trim();
customerAddress=resultSet.getString("customer_address").trim();
customerContact=resultSet.getString("customer_contact").trim();
customerCarVehicleNumber=resultSet.getString("customer_car_vechile_number").trim();
carServiceCode=resultSet.getInt("car_services_codes");
carTypeCode=resultSet.getInt("car_types_codes");
finalPricingToSend=resultSet.getString("final_pricing_to_send").trim();
customerDTO=new CustomerDTO();
customerDTO.setMerchantCode(merchantCode);
customerDTO.setMerchantName(merchantName);
customerDTO.setCustomerId("CUSTOMER"+customerId);
customerDTO.setCustomerName(customerName);
customerDTO.setCustomerAddress(customerAddress);
customerDTO.setCustomerContact(customerContact);
customerDTO.setCustomerCarVehicleNumber(customerCarVehicleNumber);
customerDTO.setCarServicesCodes(carServiceCode);
customerDTO.setCarServicesNames(carServiceName);
customerDTO.setCarTypesCodes(carTypeCode);
customerDTO.setCarTypesNames(carTypeName);
customerDTO.setFinalPricingToSend(finalPricingToSend);
customers.add(customerDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException exception)
{
System.out.println(exception);
throw new DAOException(exception.getMessage());
}
return customers;
}
public void add(CustomerDTO customer) throws DAOException
{
try
{
	
String customerContact=customer.getCustomerContact();
String customerCarVehicleNumber=customer.getCustomerCarVehicleNumber();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
ResultSet resultSet;
preparedStatement=connection.prepareStatement("select customer_id from customer where customer_contact=?");
preparedStatement.setString(1,customerContact);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Customer Contact exists :"+customerContact);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select customer_id from customer where customer_car_vechile_number=?");
preparedStatement.setString(1,customerCarVehicleNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Customer Car Vechile Number exists :"+customerCarVehicleNumber);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into customer (merchant_codes,customer_name,customer_address,customer_contact,customer_car_vechile_number,car_services_codes,car_types_codes,final_pricing_to_send) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setInt(1,customer.getMerchantCode());
preparedStatement.setString(2,customer.getCustomerName());
preparedStatement.setString(3,customer.getCustomerAddress());
preparedStatement.setString(4,customer.getCustomerContact());
preparedStatement.setString(5,customer.getCustomerCarVehicleNumber());
preparedStatement.setInt(6,customer.getCarServicesCodes());
preparedStatement.setInt(7,customer.getCarTypesCodes());
preparedStatement.setString(8,customer.getFinalPricingToSend());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int customerId=resultSet.getInt(1);
customer.setCustomerId("CUSTOMER"+customerId);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
throw new DAOException(sql.getMessage());
}
}
public boolean customerContactExists(String customerContact) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select customer_name from customer where customer_contact=?");
preparedStatement.setString(1,customerContact);
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
public boolean customerCarVehicleNumberExists(String customerCarVehicleNumber) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select customer_name from customer where customer_car_vechile_number=?");
preparedStatement.setString(1,customerCarVehicleNumber);
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
/*
public List<CustomerDTO> getCustomerByCarServiceCode(CarServiceDTO carServiceDTO) throws DAOException
{
List<CustomerDTO> customers;
customers=new LinkedList<>();
try
{
int carServiceCode=carServiceDTO.getCarServiceCode();
int carTypeCode=carTypeDTO.getCarTypeCode();
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
throw new DAOException("Invalid Car Service :"+carServiceCode);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from carType where car_type_code=?");
preparedStatement.setInt(1,carTypeCode);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Car Type :"+carTypeCode);
}
resultSet.close();
preparedStatement.close();
Statement statement;
statement=connection.createStatement();
resultSet=statement.executeQuery("select customer.customer_id,customer.customer_name,customer.customer_contact,customer.customer_address, from customer,hospital where customer.hospital_code=hospital.hospitalNumber and hospital.hospitalNumber=?");
int hospitalCode;
int customerId;
String name;
java.sql.Date dateOfBooking;
String gender;
boolean isVaccinated;
BigDecimal registrationAmount;
String disease;
String aadharCardNumber;
CustomerDTO customerDTO;
while(resultSet.next())
{
customerId=resultSet.getInt("customer_id");
name=resultSet.getString("name").trim();
gender=resultSet.getString("gender");
isVaccinated=resultSet.getBoolean("is_vaccinated");
dateOfBooking=resultSet.getDate("date_of_booking");
registrationAmount=resultSet.getBigDecimal("registration_amount");
disease=resultSet.getString("disease").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
customerDTO=new CustomerDTO();
customerDTO.setCustomerId("PATIENT"+customerId);
customerDTO.setName(name);
customerDTO.setGender(gender);
customerDTO.setIsVaccinated(isVaccinated);
customerDTO.setDateOfBooking(dateOfBooking);
customerDTO.setRegistrationAmount(registrationAmount);
customerDTO.setDisease(disease);
customerDTO.setAadharCardNumber(aadharCardNumber);
customers.add(customerDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return customers;
}
*/

public Set<CustomerDTO> getByMerchantCode(int merchantCode) throws DAOException
{
Set<CustomerDTO> customers=new TreeSet<>();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select merchant_number from merchant where merchant_number=?");
preparedStatement.setInt(1,merchantCode);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid merchant code :"+merchantCode);
}
resultSet.close();
preparedStatement.close();

//preparedStatement=connection.prepareStatement("select customer.customer_id,customer.customer_name,customer.customer_address,customer.customer_contact,customer.customer_car_vechile_number,customer.car_services_codes,customer.car_types_codes,customer.final_pricing_to_send from customer,merchant where customer.merchant_codes=merchant.merchant_number and customer.merchant_codes=?");
//preparedStatement=connection.prepareStatement("select * from customer where hospital_code=?");


preparedStatement=connection.prepareStatement("select customer.customer_id,customer.customer_name,customer.customer_address,customer.customer_contact,customer.customer_car_vechile_number,customer.final_pricing_to_send,customer.car_services_codes,customer.car_types_codes,merchant.merchant_name,carservice.car_service_name,cartype.car_type_name from customer,merchant,carservice,cartype where customer.merchant_codes=? and carservice.car_service_code=customer.car_services_codes and cartype.car_type_code=customer.car_types_codes and merchant.merchant_number=customer.merchant_codes");

preparedStatement.setInt(1,merchantCode);
resultSet=preparedStatement.executeQuery();
CustomerDTO customerDTO;

int customerId;
String customerName;
//int merchantCode;
int carServiceCode;
int carTypeCode;
String customerContact;
String customerAddress;
String customerCarVehicleNumber;
String finalPricingToSend;
while(resultSet.next())
{
customerDTO=new CustomerDTO();
customerId=resultSet.getInt("customer_id");
customerName=resultSet.getString("customer_name").trim();
customerAddress=resultSet.getString("customer_address").trim();
customerContact=resultSet.getString("customer_contact").trim();
customerCarVehicleNumber=resultSet.getString("customer_car_vechile_number").trim();
carServiceCode=resultSet.getInt("car_services_codes");
carTypeCode=resultSet.getInt("car_types_codes");
finalPricingToSend=resultSet.getString("final_pricing_to_send").trim();
customerDTO.setMerchantCode(merchantCode);
customerDTO.setCustomerId("CUSTOMER"+customerId);
customerDTO.setCustomerName(customerName);
customerDTO.setCustomerAddress(customerAddress);
customerDTO.setCustomerContact(customerContact);
customerDTO.setCustomerCarVehicleNumber(customerCarVehicleNumber);
customerDTO.setCarServicesCodes(carServiceCode);
customerDTO.setCarTypesCodes(carTypeCode);
customerDTO.setFinalPricingToSend(finalPricingToSend);
customers.add(customerDTO);
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return customers;
}
public CustomerDTO getByCustomerId(String id) throws DAOException
{
CustomerDTO customerDTO=null;
int actualCustomerId=0;
try
{
actualCustomerId=Integer.parseInt(id.substring(8));
}catch(Exception exception)
{
throw new DAOException("Invalid customer id 3:"+id);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select customer.customer_id,customer.customer_name,customer.merchant_codes,customer.customer_address,customer.customer_contact,customer.customer_car_vechile_number,customer.final_pricing_to_send,customer.car_services_codes,customer.car_types_codes,merchant.merchant_name,carservice.car_service_name,cartype.car_type_name from customer,merchant,carservice,cartype where customer.customer_id=? and carservice.car_service_code=customer.car_services_codes and cartype.car_type_code=customer.car_types_codes and merchant.merchant_number=customer.merchant_codes");
preparedStatement.setInt(1,actualCustomerId);
System.out.println("11");
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid customer id 4:"+id);
}
int customerId;
String customerName;
int merchantCode;
int carServiceCode;
int carTypeCode;
String customerContact;
String customerAddress;
String customerCarVehicleNumber;
String finalPricingToSend;
customerDTO=new CustomerDTO();
merchantCode=resultSet.getInt("merchant_codes");
customerId=resultSet.getInt("customer_id");
customerName=resultSet.getString("customer_name").trim();
customerAddress=resultSet.getString("customer_address").trim();
customerContact=resultSet.getString("customer_contact").trim();
customerCarVehicleNumber=resultSet.getString("customer_car_vechile_number").trim();
carServiceCode=resultSet.getInt("car_services_codes");
carTypeCode=resultSet.getInt("car_types_codes");
finalPricingToSend=resultSet.getString("final_pricing_to_send").trim();
customerDTO.setMerchantCode(merchantCode);
customerDTO.setCustomerId("CUSTOMER"+customerId);
customerDTO.setCustomerName(customerName);
customerDTO.setCustomerAddress(customerAddress);
customerDTO.setCustomerContact(customerContact);
customerDTO.setCustomerCarVehicleNumber(customerCarVehicleNumber);
customerDTO.setCarServicesCodes(carServiceCode);
customerDTO.setCarTypesCodes(carTypeCode);
customerDTO.setFinalPricingToSend(finalPricingToSend);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return customerDTO;
}
public void deleteByCustomerId(String customerId) throws DAOException
{
int actualCustomerId=0;
try
{
actualCustomerId=Integer.parseInt(customerId.substring(8));
}catch(Exception exception)
{
throw new DAOException("Invalid customer id 1:"+customerId);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select customer_name from customer where customer_id=?");
preparedStatement.setInt(1,actualCustomerId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid customer id 2:"+customerId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from customer where customer_id=?");
preparedStatement.setInt(1,actualCustomerId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
}
