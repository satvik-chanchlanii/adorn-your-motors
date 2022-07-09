package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
public class ReviewDTO implements java.io.Serializable,Comparable<ReviewDTO>
{
private int reviewNumber;
private String reviewerName;
private String reviewerEmail;
private String reviewDescription;
private int merchantCode;
private String merchantName;
public ReviewDTO()
{
this.reviewNumber=0;
this.reviewerName="";
this.reviewerEmail="";
this.reviewDescription="";
this.merchantCode=0;
this.merchantName="";
}
public void setReviewerName(String reviewerName)
{
this.reviewerName=reviewerName;
}
public String getReviewerName()
{
return this.reviewerName;
}
public void setReviewerEmail(String reviewerEmail)
{
this.reviewerEmail=reviewerEmail;
}
public String getReviewerEmail()
{
return this.reviewerEmail;
}
public void setReviewNumber(int reviewNumber)
{
this.reviewNumber=reviewNumber;
}
public int getReviewNumber()
{
return this.reviewNumber;
}
public void setReviewDescription(java.lang.String reviewDescription)
{
this.reviewDescription=reviewDescription;
}
public java.lang.String getReviewDescription()
{
return this.reviewDescription;
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
public int hashCode()
{
return reviewNumber;
}
public int compareTo(ReviewDTO other)
{
return this.reviewDescription.compareToIgnoreCase(other.reviewDescription);
}
public boolean equals(Object object)
{
if(!(object instanceof ReviewDTO)) return false;
ReviewDTO other;
other=(ReviewDTO)object;
return this.reviewNumber==other.getReviewNumber();
}
}