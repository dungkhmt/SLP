/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kse.slp.modules.usermanagement.validation;

/**
* Set user authentication
*/
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserValidation {

    /** Set rules for fields*/
    @NotNull(message = "You must fill in username")
    @Pattern(regexp="^[0-9a-zA-Z_]+$",message = "Invalid character, username contains only digits and characters")
    @Size(min = 5, max = 20, message = "The size of username must be in the range between 5 and 20")
    private String username;
    
    @NotNull(message = "You must fill in password")
    @Size(min = 8, max = 20, message = "The size of password must be in the range between 8 and 20")
    private String password;
    
    @NotNull(message = "You must retype password")
    @Size(min = 8, max = 20, message = "The size of repassword must be in the range between 8 and 20")
    private String repassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}           
    
}