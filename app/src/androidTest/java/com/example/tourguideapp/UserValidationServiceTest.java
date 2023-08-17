package com.example.tourguideapp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserValidationServiceTest {

    @Test
    public void testValidName() {
        assertTrue(UserValidationService.isValidPassword("John Doe"));
        assertTrue(UserValidationService.isValidName("Alice O'Connor"));
        assertFalse(UserValidationService.isValidName("123 Invalid"));
    }



    @Test
    public void testValidUserId() {
        assertTrue(UserValidationService.isValidUserId("john_doe"));
        assertTrue(UserValidationService.isValidUserId("alice-oconnor"));
        assertFalse(UserValidationService.isValidUserId("invalid user id"));
    }



    @Test
    public void testValidPassword() {
        assertTrue(UserValidationService.isValidPassword("P@ssw0rd"));
        assertFalse(UserValidationService.isValidPassword("simplepassword"));
        assertFalse(UserValidationService.isValidPassword("NoNumbers"));
    }

}
