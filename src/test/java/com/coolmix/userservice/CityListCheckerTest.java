package com.coolmix.userservice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coolmix.userservice.utils.CityListChecker;

public class CityListCheckerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCityListChecker() {
		CityListChecker cityListChecker = CityListChecker.getInstance();
		cityListChecker.loadFile("cities.txt");
		assertEquals("These cities are connected", true, cityListChecker.checkIfConnected("Newark", "Livingston"));
		assertEquals("These cities are connected", true, cityListChecker.checkIfConnected("New York", "Tampa"));
		assertEquals("These cities are not connected", false, cityListChecker.checkIfConnected("New York", "San Francisco"));
	}
}
