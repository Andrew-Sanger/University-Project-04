package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public interface Holding {

	// Abstract methods passed to AbstractHolding and then Book and Video.
	public abstract int getHoldingCode();

	public abstract String getHoldingTitle();

	public abstract int getStandardLoanFee();

	public abstract int getMaxLoanPeriod();

	public abstract String getBorrowDate();

	public abstract void setBorrowDate(String newDate);

	public abstract int calculateLateFee(int numberOfDaysLate);

	public abstract boolean isOnLoan();
}
