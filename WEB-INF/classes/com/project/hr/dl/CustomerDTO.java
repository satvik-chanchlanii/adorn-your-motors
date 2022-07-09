package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
public class CustomerDTO implements java.io.Serializable,Comparable<CustomerDTO>
{
private String customerId;
private String customerName;
private String customerAddress;
private String customerContact;
private String customerCarVehicleNumber;
private int carServicesCodes;
private String carServicesNames;
private String finalPricingToSend;
private int carTypesCodes;
private String carTypesNames;
private int merchantCode;
private String merchantName;
public CustomerDTO()
{
this.merchantCode=0;
this.merchantName="";
this.customerId="";
this.customerName="";
this.customerContact="";
this.customerAddress="";
this.customerCarVehicleNumber="";
this.carServicesCodes=0;
this.carServicesNames="";
this.finalPricingToSend="0";
this.carTypesCodes=0;
this.carTypesNames="";
}
public void setMerchantCode(int merchantCode)
{
this.merchantCode=merchantCode;
}
public int getMerchantCode()
{
return this.merchantCode;
}
public void setMerchantName(java.lang.String merchantName)
{
this.merchantName=merchantName;
}
public java.lang.String getMerchantName()
{
return this.merchantName;
}
public void setCustomerId(java.lang.String customerId)
{
this.customerId=customerId;
}
public java.lang.String getCustomerId()
{
return this.customerId;
}
public void setCustomerName(java.lang.String customerName)
{
this.customerName=customerName;
}
public java.lang.String getCustomerName()
{
return this.customerName;
}
public void setCustomerAddress(java.lang.String customerAddress)
{
this.customerAddress=customerAddress;
}
public java.lang.String getCustomerAddress()
{
return this.customerAddress;
}
public void setCustomerContact(java.lang.String customerContact)
{
this.customerContact=customerContact;
}
public java.lang.String getCustomerContact()
{
return this.customerContact;
}
public void setCustomerCarVehicleNumber(java.lang.String customerCarVehicleNumber)
{
this.customerCarVehicleNumber=customerCarVehicleNumber;
}
public java.lang.String getCustomerCarVehicleNumber()
{
return this.customerCarVehicleNumber;
}
public void setCarServicesCodes(int carServicesCodes)
{
this.carServicesCodes=carServicesCodes;
}
public int getCarServicesCodes()
{
return this.carServicesCodes;
}
public void setCarServicesNames(java.lang.String carServicesNames)
{
this.carServicesNames=carServicesNames;
}
public java.lang.String getCarServicesNames()
{
return this.carServicesNames;
}
public void setCarTypesCodes(int carTypesCodes)
{
this.carTypesCodes=carTypesCodes;
}
public int getCarTypesCodes()
{
return this.carTypesCodes;
}
public void setCarTypesNames(java.lang.String carTypesNames)
{
this.carTypesNames=carTypesNames;
}
public java.lang.String getCarTypesNames()
{
return this.carTypesNames;
}
public void setFinalPricingToSend(java.lang.String  finalPricingToSend)
{
this.finalPricingToSend=finalPricingToSend;
}
public java.lang.String  getFinalPricingToSend()
{
return this.finalPricingToSend;
}
public int hashCode()
{
return customerId.hashCode();
}
public int compareTo(CustomerDTO other)
{
return this.customerId.compareToIgnoreCase(other.customerId);
}
public boolean equals(Object object)
{
if(!(object instanceof CustomerDTO)) return false;
CustomerDTO other;
other=(CustomerDTO)object;
return this.customerId.equalsIgnoreCase(other.customerId);
}
}