package com.project.hr.dl;
public class MerchantDTO implements java.io.Serializable,Comparable<MerchantDTO>
{
private int merchantNumber;
private String merchantName;
private String merchantAddress;
private String merchantContact;
private String merchantDeliveryBoyName;
private String merchantDeliveryBoyContact;
public MerchantDTO()
{
this.merchantNumber=0;
this.merchantName="";
this.merchantAddress="";
this.merchantContact="";
this.merchantDeliveryBoyName="";
this.merchantDeliveryBoyContact="";
}
public void setMerchantNumber(int merchantNumber)
{
this.merchantNumber=merchantNumber;
}
public int getMerchantNumber()
{
return this.merchantNumber;
}
public void setMerchantName(java.lang.String merchantName)
{
this.merchantName=merchantName;
}
public java.lang.String getMerchantName()
{
return this.merchantName;
}
public void setMerchantAddress(java.lang.String merchantAddress)
{
this.merchantAddress=merchantAddress;
}
public java.lang.String getMerchantAddress()
{
return this.merchantAddress;
}
public void setMerchantContact(java.lang.String merchantContact)
{
this.merchantContact=merchantContact;
}
public java.lang.String getMerchantContact()
{
return this.merchantContact;
}
public void setMerchantDeliveryBoyName(java.lang.String merchantDeliveryBoyName)
{
this.merchantDeliveryBoyName=merchantDeliveryBoyName;
}
public java.lang.String getMerchantDeliveryBoyName()
{
return this.merchantDeliveryBoyName;
}
public void setMerchantDeliveryBoyContact(java.lang.String merchantDeliveryBoyContact)
{
this.merchantDeliveryBoyContact=merchantDeliveryBoyContact;
}
public java.lang.String getMerchantDeliveryBoyContact()
{
return this.merchantDeliveryBoyContact;
}
public int hashCode()
{
return merchantNumber;
}
public int compareTo(MerchantDTO other)
{
return this.merchantName.compareToIgnoreCase(other.merchantName);
}
public boolean equals(Object object)
{
if(!(object instanceof MerchantDTO)) return false;
MerchantDTO other;
other=(MerchantDTO)object;
return this.merchantNumber==other.getMerchantNumber();
}

}