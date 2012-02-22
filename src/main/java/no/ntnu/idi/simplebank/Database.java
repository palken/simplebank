package no.ntnu.idi.simplebank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	
	private Connection databaseConnection = null;
	
	
	public void createDatabase() {
		try {
			databaseConnection = DatabaseConnectionSingleton.getDatabaseInstance().getConnection();
			Statement createTables = databaseConnection.createStatement();
			
			createTables.execute("" +
					"CREATE TABLE IF NOT EXISTS users (" +
					"username VARCHAR(30) NOT NULL CONSTRAINT username_pk PRIMARY KEY, " +
					"first_name VARCHAR(30) NOT NULL, " +
					"last_name VARCHAR(30) NOT NULL, " +
					"password VARCHAR(30) NOT NULL, " +
					"somethingprivate VARCHAR(120) " +
					")");
			
			createTables.execute("" +
					"CREATE TABLE IF NOT EXISTS account (" +
					"accountId INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"accountName VARCHAR(30), " +
					"accountType VARCHAR(30), " +
					"money REAL NOT NULL, " +
					"usernameaccount VARCHAR(30) NOT NULL, " +
					"FOREIGN KEY (usernameaccount) REFERENCES users(username))");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void addUser(User user) {
		try {
			databaseConnection = DatabaseConnectionSingleton.getDatabaseInstance().getConnection();
			Statement addUserStatement = databaseConnection.createStatement();
			
			addUserStatement.executeUpdate("" +
					"INSERT INTO users VALUES(" +
					"'" + user.getUsername() + "', " +
					"'" + user.getFirst_name() + "', " +
					"'" + user.getSurname() + "', " +
					"'" + user.getPassword() + "', " +
					"'" + user.getSomethingPrivate() + "')");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User getUser(String username) {
		try {
			databaseConnection = DatabaseConnectionSingleton.getDatabaseInstance().getConnection();
			Statement getUserStatement = databaseConnection.createStatement();
			
			ResultSet resultSet = getUserStatement.executeQuery("SELECT * FROM users WHERE username = '"+ username + "';");
			
			String rs_username = null;
			String rs_first_name = null;
			String rs_surname = null;
			String rs_somethingsecret = null;
			
			while (resultSet.next()) {
				rs_username = resultSet.getString("username");
				rs_first_name = resultSet.getString("first_name");
				rs_surname = resultSet.getString("last_name");
				rs_somethingsecret = resultSet.getString("somethingprivate");
			}
			
			return new User(rs_username, rs_first_name, rs_surname, rs_somethingsecret);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Database() {
		
	}

	public boolean authenticate(String username, String password) {
		try {
			databaseConnection = DatabaseConnectionSingleton.getDatabaseInstance().getConnection();
			String query = "SELECT username, password FROM users WHERE username = '" + username + "';";
			Statement getuserStatement = databaseConnection.createStatement();
		
			ResultSet userResultSet = getuserStatement.executeQuery(query);
			
			if (userResultSet.next()) {
				String resultsetUsername = userResultSet.getString("username");
				String resultsetPassword = userResultSet.getString("password");
				
				if (resultsetUsername.equals(username) && resultsetPassword.equals(password)) {
					return true;
				}
			}
			
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Account> getAccountsForUsername(String username) {
		try {
			databaseConnection = DatabaseConnectionSingleton.getDatabaseInstance().getConnection();
			Statement getAccountStatement = databaseConnection.createStatement();
			
			ResultSet accountsResultSet = getAccountStatement.executeQuery("" +
					"SELECT accountName, accountType, money " +
					"FROM account " +
					"WHERE usernameaccount = '"+ username + "' ;");
			
			List<Account> accountList = new ArrayList<Account>();
			
			while(accountsResultSet.next()) {
				String accountName = accountsResultSet.getString("accountName");
				String accountType = accountsResultSet.getString("accountType");
				Double money = accountsResultSet.getDouble("money");
				
				User accountOwner = getUser(username);
				
				Account account = new Account(accountName, accountType, money, accountOwner);
				
				accountList.add(account);
			}
			
			return accountList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ArrayList<Account>(); // Return empty list if db-connection fails
	}
	
	public void addAccount(Account account) {
		try {
			
			databaseConnection = DatabaseConnectionSingleton.getDatabaseInstance().getConnection();
			
			String query = "" +
					"INSERT INTO account(" +
					"accountName, accountType, money, usernameaccount) " +
					"VALUES('" + account.getAccountName() + "', " +
							"'" + account.getAccountType() + "', " +
							"'" + account.getMoney() + "', " +
							"'" + account.getAccountOwner().getUsername() + "');";
			
			Statement addAccountStatement = databaseConnection.createStatement();
			addAccountStatement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}