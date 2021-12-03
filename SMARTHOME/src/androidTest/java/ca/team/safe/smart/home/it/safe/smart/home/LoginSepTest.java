package ca.team.safe.smart.home.it.safe.smart.home;

import static org.junit.Assert.*;
import com.google.common.truth.Truth;

import org.junit.Test;

public class LoginSepTest {

    @Test
    public void isValidPassword() {
        assertTrue( LoginSep.isValidPassword("Abc@123"));
    }
      @Test
    public void secureIDValidation() {
          assertTrue( LoginSep.secureIDValidation("111111123"));
    }

    @Test
    public void emailValidation() {
          assertTrue( LoginSep.emailValidation("pammy@gm.m"));
    }

}