package hrmscripts;

import java.io.IOException;

//import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.actions.ActionDriver;
@Test
public class Login extends ActionDriver {
	
	public void ValidLogin() throws IOException
	{
		type(".//*[@id='txtUsername']", "user02", "UserName");
		type(".//*[@id='txtPassword']", "pass1234", "Password");
		click(".//*[@id='btnLogin']", "Login");
				
	}
	
}