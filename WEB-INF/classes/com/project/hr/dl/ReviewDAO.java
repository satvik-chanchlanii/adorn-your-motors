package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
import java.text.*;
public class ReviewDAO
{
public List<ReviewDTO> getAll() throws DAOException
{
List<ReviewDTO> reviews;
reviews=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select review.review_number,review.reviewer_name,review.reviewer_email,review.review_description from review order by review.reviewer_name");
ReviewDTO reviewDTO;
int reviewNumber;
String reviewerName;
String reviewerEmail;
String reviewDescription;
while(resultSet.next())
{
reviewNumber=resultSet.getInt("review_number");
reviewerName=resultSet.getString("reviewer_name").trim();
reviewerEmail=resultSet.getString("reviewer_email").trim();
reviewDescription=resultSet.getString("review_description").trim();
reviewDTO=new ReviewDTO();
reviewDTO.setReviewNumber(reviewNumber);
reviewDTO.setReviewerName(reviewerName);
reviewDTO.setReviewerEmail(reviewerEmail);
reviewDTO.setReviewDescription(reviewDescription);
reviews.add(reviewDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());
}
return reviews;
}
public void add(ReviewDTO review) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
ResultSet resultSet;
preparedStatement=connection.prepareStatement("select * from review where reviewer_email=?");
preparedStatement.setString(1,review.getReviewerEmail());
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Reviewer Email :"+review.getReviewerEmail()+" exists.");
}
preparedStatement.close();
resultSet.close();
preparedStatement=connection.prepareStatement("insert into review(reviewer_name,reviewer_email,review_description) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,review.getReviewerName());
preparedStatement.setString(2,review.getReviewerEmail());
preparedStatement.setString(3,review.getReviewDescription());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int reviewNumber=resultSet.getInt(1);
review.setReviewNumber(reviewNumber);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
throw new DAOException(sql.getMessage());
}
}
public boolean reviewerEmailExists(String reviewerEmail) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select reviewer_name from review where reviewer_email=?");
preparedStatement.setString(1,reviewerEmail);
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



}