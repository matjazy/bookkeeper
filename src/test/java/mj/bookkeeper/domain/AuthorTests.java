package mj.bookkeeper.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuthorTests {

	@Test
	public void testComparisonOfLowerWithHigherRatingAuthor() {
		int comparisonResult = compareTwoAuthorValues(1d, 2d);
		assertEquals(comparisonResult, -1);
	}
	
	@Test
	public void testComparisonOfHigherWithLowerRatingAuthor() {
		int comparisonResult = compareTwoAuthorValues(2d, 1d);
		assertEquals(comparisonResult, 1);
	}
	
	@Test
	public void testComparisonOfEqualRatingAuthors() {
		int comparisonResult = compareTwoAuthorValues(1d, 1d);
		assertEquals(comparisonResult, 0);
	}
	
	@Test
	public void testComparisonOfNullAndDoubleRatingScenario() {
		int comparisonResult = compareTwoAuthorValues(null, 1d);
		assertEquals(comparisonResult, -1);
	}
	
	@Test
	public void testComparedAuthorHasNullRatingScenario() {
		int comparisonResult = compareTwoAuthorValues(1d, null);
		assertEquals(comparisonResult, 1);
	}
	
	@Test
	public void testBothAuthorsHaveNullRatingScenario() {
		int comparisonResult = compareTwoAuthorValues(null, null);
		assertEquals(comparisonResult, 0);		
	}
	
	private int compareTwoAuthorValues(Double value, Double value1) {
		Author author = new Author("author", value);
		Author author1 = new Author("author1", value1);
		return author.compareTo(author1);
	}
}