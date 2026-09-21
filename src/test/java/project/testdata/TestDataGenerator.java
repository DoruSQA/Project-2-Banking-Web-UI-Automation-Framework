package project.testdata;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataGenerator {

	public static String uniqueEmail() {
		return "test_" + System.currentTimeMillis() + "@example.com";
	}

	public static String randomName() {
		return "JonnyCage" + UUID.randomUUID();
	}

	private static String randomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		StringBuilder result = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = ThreadLocalRandom.current().nextInt(characters.length());

			result.append(characters.charAt(index));
		}

		return result.toString();
	}

	public static String randomFirstName(int length) {
		return randomString(length);
	}
	
	public static String randomUsername(int length) {
		return randomString(length);
	}
	
	public static String randomPassword(int length) {
		return randomString(length);
	}

	public static String randomLastName(int length) {
		return randomString(length);
	}

	private static String randomNumeric(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length must be greater than 0");
		}

		if (length == 1) {
			return String.valueOf(ThreadLocalRandom.current().nextInt(0, 10));
		}

		int firstDigit = ThreadLocalRandom.current().nextInt(1, 10);

		StringBuilder result = new StringBuilder(length);
		result.append(firstDigit);

		for (int i = 1; i < length; i++) {
			result.append(ThreadLocalRandom.current().nextInt(0, 10));
		}

		return result.toString();
	}

	public static String randomZipCode(int length) {
		return randomNumeric(length);
	}
	public static String randomNumber(int length) {
		return randomNumeric(length);
	}
	
	public static String randomAmount(int length) {
		return randomNumeric(length);
	}
	public static String emptyString() {
	        return "";

	}
	
	public static String specialChars(int count) {
	    String chars = "!@#$%^&*()-_=+[]{}|;:',.<>?/`~\\";

	    if (count <= 0) {
	        return "";
	    }

	    StringBuilder result = new StringBuilder(count);
	    ThreadLocalRandom random = ThreadLocalRandom.current();

	    for (int i = 0; i < count; i++) {
	        result.append(chars.charAt(random.nextInt(chars.length())));
	    }

	    return result.toString();
	}
	
	

}