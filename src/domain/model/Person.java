package domain.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person{
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String passwordHashed;

	public Person(String userid, String email, String password, String firstName, String lastName) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setPasswordHashed(password);
	}
	
	public Person() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email.toLowerCase();
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public String getPasswordHashed() {return this.passwordHashed;}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		String h = this.hashPassword(password);
		return this.passwordHashed.equals(h);
	}

	public void setPasswordHashed(String password) {
        this.passwordHashed = this.hashPassword(password);
    }

	private String hashPassword(String password) {
	    try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            crypt.reset();
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
            byte[] digest = crypt.digest();
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            return digestAsBigInteger.toString(16);
        }catch(Exception e){
	        return null;
        }
    }

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		String first = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
		this.firstName = first;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		String last = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
		this.lastName = last;
	}
	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}

}