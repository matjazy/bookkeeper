package mj.bookkeeper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class for author.
 * @author MJazy
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Author implements Comparable<Author> {

	private String author;
	private Double averageRating;

	@Override
	/**
	 * Comparison method for Author class.
	 * @return -1 if comparing author's rating is lower than supplied author's rating, 1 if higher, 0 if equals.
	 */
	public int compareTo(Author author) {
		if (averageRating < author.averageRating) {
			return -1;
		}
		else if (averageRating > author.averageRating) {
			return 1;
		}
		return 0;
	}
}
