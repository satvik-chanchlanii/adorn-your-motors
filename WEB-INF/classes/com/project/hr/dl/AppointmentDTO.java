package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
public class AppointmentDTO implements java.io.Serializable,Comparable<AppointmentDTO>
{
private int appointmentNumber;
private String appointmentName;
private String appointmentAddress;
private String appointmentContact;
private String appointmentDescription;
private int merchantCode;
private int carServiceCode;
private String merchantName;
private String carServiceName;
public AppointmentDTO()
{
this.appointmentNumber=0;
this.appointmentName="";
this.appointmentAddress="";
this.appointmentContact="";
this.appointmentDescription="";
this.merchantCode=0;
this.carServiceCode=0;
this.merchantName="";
this.carServiceName="";
}
public void setAppointmentNumber(int appointmentNumber)
{
this.appointmentNumber=appointmentNumber;
}
public int getAppointmentNumber()
{
return this.appointmentNumber;
}
public void setAppointmentName(java.lang.String appointmentName)
{
this.appointmentName=appointmentName;
}
public java.lang.String getAppointmentName()
{
return this.appointmentName;
}
public void setAppointmentAddress(java.lang.String appointmentAddress)
{
this.appointmentAddress=appointmentAddress;
}
public java.lang.String getAppointmentAddress()
{
return this.appointmentAddress;
}
public void setAppointmentContact(java.lang.String appointmentContact)
{
this.appointmentContact=appointmentContact;
}
public java.lang.String getAppointmentContact()
{
return this.appointmentContact;
}
public void setAppointmentDescription(java.lang.String appointmentDescription)
{
this.appointmentDescription=appointmentDescription;
}
public java.lang.String getAppointmentDescription()
{
return this.appointmentDescription;
}
public void setMerchantCode(int merchantCode)
{
this.merchantCode=merchantCode;
}
public int getMerchantCode()
{
return this.merchantCode;
}
public void setCarServiceCode(int carServiceCode)
{
this.carServiceCode=carServiceCode;
}
public int getCarServiceCode()
{
return this.carServiceCode;
}
public void setMerchantName(java.lang.String merchantName)
{
this.merchantName=merchantName;
}
public java.lang.String getMerchantName()
{
return this.merchantName;
}
public void setCarServiceName(java.lang.String carServiceName)
{
this.carServiceName=carServiceName;
}
public java.lang.String getCarServiceName()
{
return this.carServiceName;
}
public int hashCode()
{
return appointmentNumber;
}
public int compareTo(AppointmentDTO other)
{
return this.appointmentDescription.compareToIgnoreCase(other.appointmentDescription);
}
public boolean equals(Object object)
{
if(!(object instanceof AppointmentDTO)) return false;
AppointmentDTO other;
other=(AppointmentDTO)object;
return this.appointmentNumber==other.getAppointmentNumber();
}
}