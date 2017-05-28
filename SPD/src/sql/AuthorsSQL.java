package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


import entities.*;
import javafx.scene.control.cell.PropertyValueFactory;
import controller.*;

public class AuthorsSQL {

	/*ControllerAuthors authors = new ControllerAuthors();
	public void connectToDB() throws SQLException{
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/spd","root","7525800");
		stmt=conn.createStatement();
		rs=stmt.executeQuery("Select * From Authors");
		while(rs.next()){		
			authors.authorsData.add(new Authors(rs.getInt("id_authors"), rs.getInt("id_identifiers"), rs.getString("full_name_en"), rs.getString("full_name_ru"), rs.getString("full_name_ua"), rs.getString("scientific_degree"), rs.getDate("data_of_birth"), rs.getString("email"),rs.getString("telephone")));
		}		
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		} finally{
			if (conn!=null){
				try{
					conn.close();
					stmt.close();
					rs.close();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());			}
			}
		}
	}
	
	public void refresTables() throws SQLException{
		authors.authorsData.clear();
		connectToDB();		
		authors.id_authors.setCellValueFactory((new PropertyValueFactory<Authors, Integer>("id_authors")));
		authors.id_identifiers.setCellValueFactory((new PropertyValueFactory<Authors, Integer>("id_identifiers")));
		authors.full_name_en.setCellValueFactory((new PropertyValueFactory<Authors, String>("full_name_en")));
		authors.full_name_ru.setCellValueFactory((new PropertyValueFactory<Authors, String>("full_name_ru")));
		authors.full_name_ua.setCellValueFactory((new PropertyValueFactory<Authors, String>("full_name_ua")));
		authors.scientific_degree.setCellValueFactory((new PropertyValueFactory<Authors, String>("scientific_degree")));
		authors.data_of_birth.setCellValueFactory((new PropertyValueFactory<Authors, Date>("data_of_birth")));
		authors.email.setCellValueFactory((new PropertyValueFactory<Authors, String>("email")));
		authors.telephone.setCellValueFactory((new PropertyValueFactory<Authors, String>("telephone")));	
		authors.tblAuthors.setItems(authors.authorsData);
		
	}*/
}
