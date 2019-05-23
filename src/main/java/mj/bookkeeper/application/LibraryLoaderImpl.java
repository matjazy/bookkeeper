package mj.bookkeeper.application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import mj.bookkeeper.domain.Book;

/**
 * Implementation of BookLoader that uses basic java libraries and org.json library to load books from json file.
 * @author MJazy
 *
 */
@Component
public class LibraryLoaderImpl implements LibraryLoader{
	
	private final String BOOKS_FILE_PATH = "src/main/resources/static/books.json";
	
	@Override
	public List<Book> loadAllBooks() {
		return mapItemsToBooks(getItemsArrayFromFile());		
	}
	
	private JSONArray getItemsArrayFromFile() {
		JSONArray jsonArray = new JSONArray();
		try {
			InputStream inputStream = new FileInputStream(BOOKS_FILE_PATH);
			String result = new BufferedReader(new InputStreamReader(inputStream))
					  .lines().collect(Collectors.joining("\n"));
			inputStream.close();
			JSONObject jsonObject = new JSONObject(result);
			jsonArray = jsonObject.getJSONArray("items");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	private List<Book> mapItemsToBooks(JSONArray jsonArray) {
		List<Book> books = new LinkedList<Book>();
		for (int index = 0; index < jsonArray.length(); index++) {
			JSONObject jsonObjectItem = (JSONObject) jsonArray.get(index);
			books.add(mapItemToBook(jsonObjectItem));			
		}
		return books;
	}
	
	private Book mapItemToBook(JSONObject item) {
		JSONObject volumeInfo = item.getJSONObject("volumeInfo");
		Book book = Book.builder()
				.isbn(prepareISBN(item, volumeInfo))
				.title(volumeInfo.optString("title", null))
				.subtitle(volumeInfo.optString("subtitle", null))
				.publisher(volumeInfo.optString("publisher", null))
				.publishedDate(preparePublishedDate(volumeInfo))
				.description(volumeInfo.optString("description", null))
				.pageCount(volumeInfo.optInt("pageCount", 0))
				.thumbnailUrl(prepareThumbnailUrl(volumeInfo))
				.language(volumeInfo.optString("language", null))
				.previewLink(volumeInfo.optString("previewLink", null))
				.averageRating(volumeInfo.optDouble("averageRating", 0))
				.authors(prepareStringArrayFromVolumeInfo(volumeInfo, "authors"))
				.categories(prepareStringArrayFromVolumeInfo(volumeInfo, "categories"))
				.build();		
		return book;
	}
	
	private long preparePublishedDate(JSONObject volumeInfo) {
		String publishedDate = volumeInfo.optString("publishedDate", null);
		if (publishedDate == null) {
			return 0;
		}
		// If there is only a year in a date then count it as first of January of that year.
		if (publishedDate.length() == 4) {
			publishedDate = publishedDate + "-01-01";
		}
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		return LocalDate.parse(publishedDate, dateFormatter)
				.atStartOfDay(ZoneOffset.UTC)
				.toInstant()
				.toEpochMilli();
	}
	
	
	private String prepareThumbnailUrl(JSONObject volumeInfo) {
		JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
		return	imageLinks.optString("thumbnail", null);		
	}
	
	private String[] prepareStringArrayFromVolumeInfo(JSONObject volumeInfo, String parameterName) {
		if (!volumeInfo.has(parameterName)) {
			return null;
		}
		List<String> stringList = new LinkedList<String>();
		JSONArray jsonArray = volumeInfo.getJSONArray(parameterName);
		for (int index = 0; index < jsonArray.length(); index++) {
			stringList.add(jsonArray.getString(index));
		}
		return stringList.toArray(new String[0]);
	}
		
	private String prepareISBN(JSONObject item, JSONObject volumeInfo) {
		String isbn;
		JSONArray industryIdentifiers = volumeInfo.getJSONArray("industryIdentifiers");
		if(industryIdentifiers.toString().contains(("ISBN_13"))) {
			isbn = getISBNFromIndustryIdentifiers(industryIdentifiers);
			}
		else {
			isbn = item.getString("id");
		}
		return isbn;
	}
	
	private String getISBNFromIndustryIdentifiers(JSONArray industryIdentifiers) {
		String isbn = null;
		for (int index = 0; index < industryIdentifiers.length(); index++) {
			JSONObject identifierJSONObject = (JSONObject) industryIdentifiers.get(index);
			if (identifierJSONObject.getString("type").equals("ISBN_13")) {
				isbn =  identifierJSONObject.getString("identifier");			
			}
		};	
		return isbn;
	}
		
}
