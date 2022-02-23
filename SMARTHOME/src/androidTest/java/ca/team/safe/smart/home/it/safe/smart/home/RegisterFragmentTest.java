package ca.team.safe.smart.home.it.safe.smart.home;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegisterFragmentTest {

    @Test
    public void isValidPassword() {
        assertTrue( RegisterSep.isValidPassword("Abc@123"));
    }
    @Test
    public void secureIDValidation() {
        assertTrue( RegisterSep.secureIDValidation("111111123"));
    }

    @Test
    public void register() {
      //  assertTrue( RegisterSep.register("abc@gmail.com","Abcd@123","111111911"));
    }
}