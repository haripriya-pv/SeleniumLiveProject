package com.qa.utility;

import com.github.javafaker.Faker;

public class FakerUtility {
	
	public static String getName()
	{
	Faker faker = new Faker();
	return faker.name().firstName();
	}
	
	public static String getNumber()
	{
		Faker faker = new Faker();
		return faker.numerify("##########");
	}

}
