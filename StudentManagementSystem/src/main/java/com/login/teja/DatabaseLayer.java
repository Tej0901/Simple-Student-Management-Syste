package com.login.teja;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DatabaseLayer extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
	public static Student objPojo[] = new Student[25];
	public static int pojoObjectIndex;
	
	
	public static void fetchAllDetailsToPojo(Statement stmt) throws SQLException, Exception
	{
		String query = "SELECT * FROM StudentDetails";
		ResultSet rSet = stmt.executeQuery(query);
		setRecordsInPojo(rSet);
		rSet.close();
	}
	
	
	public static void setRecordsInPojo(ResultSet rSet) throws SQLException,Exception
	{
		pojoObjectIndex=0;
		rSet.beforeFirst();
		while(rSet.next())
		{
			String ID = rSet.getString(1);
			String name = rSet.getString(2);
			String std = rSet.getString(3);
			int age = rSet.getInt(4);
			objPojo[pojoObjectIndex] = new Student(ID, name, std, age);
			pojoObjectIndex++;
		}
	}
	
	
	public static void getAllDetails(PrintWriter out)
	{
		out.println("<br/>");
		for(int i=0;i<pojoObjectIndex;i++)
		{
			String ID = objPojo[i].getID();
			String name = objPojo[i].getName();
			String std = objPojo[i].getStd();
			int age = objPojo[i].getAge();
			out.println("<tr><td>" + ID + "</td><td>" + name 
			+ "</td><td>" + std + "</td><td>" + age + "</td></tr>");
		}
	}
	
	
	public static void fetchParticularID(Statement stmt,Connection con,String ID,PrintWriter out) throws SQLException
	{
		out.println("<br/> After performing a query, result is as below:");
		boolean flag = false;
		for(int i=0;i<pojoObjectIndex;i++)
		{
			if(objPojo[i].getID().equals(ID))
			{
				out.println("<table border=1 width=50% height=50%> ");  
		        out.println("<tr><th>Student Name</th><th> Std</th><tr>");
				out.println("<br/><tr><td>"+objPojo[i].getName()+"</td><td>"+objPojo[i].getStd()+"</td></tr>");
				out.println("</table>");
				flag = true;
				break;
			}
		}
		if(!flag)
		{
			out.println("<br/> Not Found required details!!!!");
		}
	}
	
	
	public static int Delete(Statement stmt,Connection con,String ID) throws SQLException, Exception
	{
		String query = "DELETE FROM StudentDetails WHERE ID=?";
		PreparedStatement stmt2 = con.prepareStatement(query);
		stmt2.setString(1, ID);
		int i = stmt2.executeUpdate();
		fetchAllDetailsToPojo(stmt);
		stmt2.close();
		return i;
	}
	
	
	public static int Update(Statement stmt,Connection con,String ID,String name) throws SQLException,Exception
	{
		String query = "UPDATE StudentDetails SET StudentName=? WHERE ID=?";
		PreparedStatement stmt2 = con.prepareStatement(query);
		
		stmt2.setString(1, name);
		stmt2.setString(2,ID);
		int i = stmt2.executeUpdate();
		fetchAllDetailsToPojo(stmt);
		stmt2.close();
		return i;
	}
	
	
	public static int Insert(Statement stmt,Connection con,String ID,String name,String std,int age) throws SQLException,Exception
	{
		String query ="INSERT INTO StudentDetails VALUES(?,?,?,?)";
		PreparedStatement stmt2 = con.prepareStatement(query);
		stmt2.setString(1, ID);
		stmt2.setString(2, name);
		stmt2.setString(3, std);
		stmt2.setInt(4, age);
		int i = stmt2.executeUpdate();
		fetchAllDetailsToPojo(stmt);
		stmt2.close();
		return i;
	}
	
	
	public static void setConnectionAndPerformOperations(String option,HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try 
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/MysqlDatabase?autoReconnect=true&useSSL=false";//added useSSl=false to avoid the warning
		String userName = "root";
		String userPassword = "teja0901";
		Connection con = DriverManager.getConnection(url, userName, userPassword);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		
		
		switch (option) 
		{
			case "1":  //fetch all details
			{
				fetchAllDetailsToPojo(stmt);
				out.println("<br/>Records were stored in Pojo");
				out.println("<br/> After performing a query, result is as below:<br/><br/>");
				out.println("<table border=1 width=50% height=50%>");  
	            out.println("<tr><th>Student ID</th><th>Student Name</th><th> Std</th><th> Age</th><tr>");
	            getAllDetails(out);
	            out.println("</table>");
				break;
			}
			case "2":  // fetch particular detail
			{
				out.println("inside fetch");
				String ID = request.getParameter("studentId");
				fetchParticularID(stmt,con,ID, out);
				break;
			}
			case "3":   //delete
			{
				out.println("inside delete");
				String ID = request.getParameter("studentId");
				int i = Delete(stmt, con, ID);
				out.println("<br/> After performing a query, result is as below:");
				
				out.println("<br/>"+i+" rows updated");
				break;
			}
			case "4":   //update
			{
				out.println("inside update");
				String ID = request.getParameter("studentId");
				String name = request.getParameter("studentName");
				int i = Update(stmt, con, ID, name);
				out.println("<br/> After performing a query, result is as below:");
				out.println("<br/>"+i+" rows updated!!!");
				break;
			}
			case "5":   //insert
			{
				out.println("inside insert operation");
				String ID = request.getParameter("studentId");
				String name = request.getParameter("studentName");
				String std  = request.getParameter("studentStd");
				int age = Integer.parseInt(request.getParameter("studentAge"));
				int i = Insert(stmt, con, ID, name, std, age);
				out.println("<br/> After performing a query, result is as below:");
				out.println("<br/>"+i+" rows updated");
				break;
			}
		}	
	}
	catch (ClassNotFoundException e) 
	{
		out.println("\nInvalid Operation!!! Error Occurred!!!  -->  "+e);
	}
	catch (SQLException e) 
	{
		out.println("\nInvalid Operation!!! Error Occurred!!!  -->  "+e);
    } 
	catch (Exception e) 
	{
		out.println("\nInvalid Operation!!! Error Occurred!!!  -->  "+e);
	}
}

}