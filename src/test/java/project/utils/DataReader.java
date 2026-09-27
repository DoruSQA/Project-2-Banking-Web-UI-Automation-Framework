package project.utils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;

public class DataReader {

	private static final Gson gson = new Gson();


	public static <T> T read(String fileName, Class<T> clazz) {

		try {

			Reader reader = new InputStreamReader(
					DataReader.class.getClassLoader().getResourceAsStream("testdatafiles/" + fileName + ".json"));

			return gson.fromJson(reader, clazz);

		} catch (Exception e) {

			throw new RuntimeException("Cannot read json file: " + fileName, e);
		}
	}

	public static <T> T read(String fileName, Type type) {

		try (Reader reader = new InputStreamReader(
				DataReader.class.getClassLoader().getResourceAsStream("testdatafiles/" + fileName + ".json"))) {

			return gson.fromJson(reader, type);

		} catch (Exception e) {

			throw new RuntimeException("Cannot read json file: " + fileName, e);
		}
	}


}
