package com.project.hr.dl;
public class CarTypeDTO implements java.io.Serializable,Comparable<CarTypeDTO>
{
private int carTypeCode;
private String carTypeName;
public CarTypeDTO()
{
this.carTypeCode=0;
this.carTypeName="";
}
public void setCarTypeCode(int carTypeCode)
{
this.carTypeCode=carTypeCode;
}
public int getCarTypeCode()
{
return this.carTypeCode;
}
public void setCarTypeName(java.lang.String carTypeName)
{
this.carTypeName=carTypeName;
}
public java.lang.String getCarTypeName()
{
return this.carTypeName;
}
public int hashCode()
{
return carTypeCode;
}
public int compareTo(CarTypeDTO other)
{
return this.carTypeName.compareToIgnoreCase(other.carTypeName);
}
public boolean equals(Object object)
{
if(!(object instanceof CarTypeDTO)) return false;
CarTypeDTO other;
other=(CarTypeDTO)object;
return this.carTypeCode==other.getCarTypeCode();
}
}