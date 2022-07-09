package com.project.hr.dl;
import java.math.*;
public class CarServicePricingDTO implements java.io.Serializable,Comparable<CarServicePricingDTO>
{
private int carServicePricingCode;
private int carTypeCodes;
private int carServiceTypeCode;
private String carTypeTitle;
private String carServiceTypeName;
private BigDecimal finalPricingToSend;
public CarServicePricingDTO()
{
this.carTypeCodes=0;
this.carServiceTypeCode=0;
this.carServicePricingCode=0;
this.carServiceTypeName="";
this.carTypeTitle="";
this.finalPricingToSend=null;
}
public void setCarServiceTypeName(String carServiceTypeName)
{
this.carServiceTypeName=carServiceTypeName;
}
public String getCarServiceTypeName()
{
return this.carServiceTypeName;
}
public void setCarServicePricingCode(int carServicePricingCode)
{
this.carServicePricingCode=carServicePricingCode;
}
public int getCarServicePricingCode()
{
return this.carServicePricingCode;
}
public void setCarTypeCodes(int carTypeCodes)
{
this.carTypeCodes=carTypeCodes;
}
public int getCarTypeCodes()
{
return this.carTypeCodes;
}
public void setCarServiceTypeCode(int carServiceTypeCode)
{
this.carServiceTypeCode=carServiceTypeCode;
}
public int getCarServiceTypeCode()
{
return this.carServiceTypeCode;
}
public void setCarTypeTitle(java.lang.String carTypeTitle)
{
this.carTypeTitle=carTypeTitle;
}
public String getCarTypeTitle()
{
return this.carTypeTitle;
}
public void setFinalPricingToSend(java.math.BigDecimal finalPricingToSend)
{
this.finalPricingToSend=finalPricingToSend;
}
public java.math.BigDecimal getFinalPricingToSend()
{
return this.finalPricingToSend;
}
public int hashCode()
{
return carServicePricingCode;
}
public int compareTo(CarServicePricingDTO other)
{
return this.carServicePricingCode-other.carServicePricingCode;
}
public boolean equals(Object object)
{
if(!(object instanceof CarServicePricingDTO)) return false;
CarServicePricingDTO other;
other=(CarServicePricingDTO)object;
return this.carServicePricingCode==other.getCarServicePricingCode();
}
}