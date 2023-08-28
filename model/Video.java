package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class Video extends AbstractHolding {
	
	// --Initiate class variables
	private static final int MAXIMUM_LOAN_PERIOD = 7;
	private static final String HOLDING_TYPE = "VIDEO";

	// --Class constructor
	public Video(int holdingCode, String title, int fee) {
		super(holdingCode, title, fee, MAXIMUM_LOAN_PERIOD);
	}

	@Override
	public int calculateLateFee(int numberOfDaysLate) {
		return (numberOfDaysLate * (this.getStandardLoanFee() / 2));
	}
	
	@Override
	public String toString() {
		return (super.toString() + ":" + HOLDING_TYPE);
	}
}
