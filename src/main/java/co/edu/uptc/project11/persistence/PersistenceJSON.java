package co.edu.uptc.project11.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;

public class PersistenceJSON<T> {
	private String route;
	
	public PersistenceJSON(String route){
		this.route = route;
	}
	
	public SimpleUptcList<T> readDates(TypeToken<SimpleUptcList<T>> typeToken) throws IOException {
		JsonReader reader = new JsonReader(new FileReader(route));
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
		SimpleUptcList<T> listaObjetos = gson.fromJson(reader, typeToken.getType());
		reader.close();
		return listaObjetos;
	}
		
	public void writeDates(SimpleUptcList<T> list) throws FileNotFoundException {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
		String text = gson.toJson(list);
		PrintWriter writer = new PrintWriter(route);
		writer.write(text);
		writer.close();
	}

}
