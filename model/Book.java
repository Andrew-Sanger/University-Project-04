package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class Book extends AbstractHolding {

	// --Initiate class variables
	private static final int STANDARD_LOAN_FEE = 10;
	private static final int FIXED_DAILY_OVERDUE_FEE = 2;
	private static final int MAXIMUM_LOAN_PERIOD = 28;
	private static final String HOLDING_TYPE = "BOOK";

	// --Class constructor
	public Book(int holdingCode, String title) {
		super(holdingCode, title, STANDARD_LOAN_FEE, MAXIMUM_LOAN_PERIOD);
	}

	@Override
	public int calculateLateFee(int numberOfDaysLate) {
		return (numberOfDaysLate * Book.FIXED_DAILY_OVERDUE_FEE);
	}

	@Override
	public String toString() {
		return (super.toString() + ":" + HOLDING_TYPE);
	}
}
