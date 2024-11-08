package com.udacity.examples.Testing;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelperTest {

    @BeforeClass
    public static void init(){
        
    }

    @AfterClass
    public void initEnd(){
        
    }

    @Before
    public void setupUp(){

    }

    @After
    public void tearDown(){

    }
    
    @Test
    public void test(){
        assertEquals(3, 3);
    }

    @Test
    public void testGetCount(){

    }

	
}
