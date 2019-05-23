package mj.bookkeeper.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuthorTests {

	@Test
	public void testComparisonOfLowerWithHigherRatingAuthor() {
		Author lowerRatingAuthor = new Author ("lower", 1d);
		Author higherRatingAuthor = new Author ("higher", 2d);
		int comparisonResult = lowerRatingAuthor.compareTo(higherRatingAuthor);
		assertEquals(comparisonResult, -1);
	}
	
	@Test
	public void testComparisonOfHigherWithLowerRatingAuthor() {
		Author higherRatingAuthor = new Author ("higher", 2d);
		Author lowerRatingAuthor = new Author ("lower", 1d);
		int comparisonResult = higherRatingAuthor.compareTo(lowerRatingAuthor);
		assertEquals(comparisonResult, 1);
	}
	
	@Test
	public void testComparisonOfEqualRatingAuthors() {
		Author equalRatingAuthor = new Author ("equal", 1d);
		Author equalRatingAuthor1 = new Author ("equal1", 1d);
		int comparisonResult = equalRatingAuthor.compareTo(equalRatingAuthor1);
		assertEquals(comparisonResult, 0);
	}
	
	
}
