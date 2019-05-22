package mj.bookkeeper.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class for a book.
 * @author MJazy
 *
 */
@lombok.Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class Book {
		
	private String isbn;
	private String title;
	private String subtitle;
	private String publisher;
	private long publishedDate;
	private String description;
	private int pageCount;
	private String thumbnailUrl;
	private String language;
	private String previewLink;
	private double averageRating;
	private String[] authors;
	private String[] categories;	
	
}
