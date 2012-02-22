package no.ntnu.idi.simplebank;

import junit.framework.TestCase;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Validator;

public class Test extends TestCase {

	public Test(String testName)
	{
		super(testName);
	}

	public void testIsValidEmail()
	{
		Validator instance = ESAPI.validator();
		assertTrue(instance.isValidInput("test", "jeff.williams@aspectsecurity.com", "Email", 100, false));
	}
}

