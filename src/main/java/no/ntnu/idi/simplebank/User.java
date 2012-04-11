package no.ntnu.idi.simplebank;

public class User {
	
	private String username;
	private String password;
	private String first_name;
	private String surname;
	private String somethingPrivate;
	
	public User(String username, String name, String surname, String somethingprivate) {
		this.username = username;
		this.first_name =  name;
		this.surname = surname;
		this.somethingPrivate = somethingprivate;
	}
	
	public User(String username, String password, String name, String surname, String somethingPrivate) {
		this.username = username;
		this.password = password;
		this.first_name =  name;
		this.surname = surname;
		this.somethingPrivate = somethingPrivate;
	}

	public String getUsername() {
		return username;
	}

    public String getPassword() {
		return password;
	}

    public String getFirst_name() {
		return first_name;
	}

    public String getSurname() {
		return surname;
	}

    public String getSomethingPrivate() {
		return somethingPrivate;
	}

}
