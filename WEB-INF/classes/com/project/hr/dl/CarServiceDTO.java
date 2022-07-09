package com.project.hr.dl;
public class CarServiceDTO implements java.io.Serializable,Comparable<CarServiceDTO>
{
private int carServiceCode;
private String carServiceName;
public CarServiceDTO()
{
this.carServiceCode=0;
this.carServiceName="";
}
public void setCarServiceCode(int carServiceCode)
{
this.carServiceCode=carServiceCode;
}
public int getCarServiceCode()
{
return this.carServiceCode;
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
return carServiceCode;
}
public int compareTo(CarServiceDTO other)
{
return this.carServiceName.compareToIgnoreCase(other.carServiceName);
}
public boolean equals(Object object)
{
if(!(object instanceof CarServiceDTO)) return false;
CarServiceDTO other;
other=(CarServiceDTO)object;
return this.carServiceCode==other.getCarServiceCode();
}
}