import java.util.Scanner;

// Version 1.0
// Jan 21, 2021, 17:

public class DAY {
	// determines whether a given year is bouncy.
	public static boolean isLeapYear(int y) {
		if (y % 4 != 0) {return false;} // normal year
		else if (y % 100 != 0) {return true;} // leap year
		else if (y % 400 != 0) {return false;} // normal year
		else {return true;} // leap year
	}
	
	public static int daysInYear(int y) {
		if (isLeapYear(y)) {return 366;}
		else {return 365;}
	}
	
	// counts the number of days in a given month.
	public static int daysInMonth(int m, int y) {
		if (m == 2) {
			if (isLeapYear(y)) {return 29;}
			else {return 28;}
		}
		else if ((m == 4) || (m == 6) || (m == 9) || (m == 11)) {return 30;}
		else {return 31;}
	}
	
	// determines whether an entry of two months, days, and years is valid.
	public static boolean isValid(int y1, int y2, int m1, int m2, int d1, int d2) {
		if ((y1 <= y2) && (1 <= m1) && (m1 <= 12) && (1 <= m2) && (m2 <= 12) && (1 <= d1) && (d1 <= daysInMonth(m1, y1)) && (1 <= d2) && (d2 <= daysInMonth(m2, y2))) {return true;}
		else {return false;}
	}

	public static int daysBetween(int y, int m1, int d1, int m2, int d2) {
		if (m1 == m2) {
			return d2 - d1;
		}
		else {
			int currentMonth = m1 + 1;
			int dayCount = daysInMonth(m1, y) - d1;
			while (currentMonth < m2) {
				dayCount += daysInMonth(currentMonth, y);
				currentMonth++;
			}
			dayCount += d2;
			return dayCount;
		}
	}
	
	public static int daysSoFar(int y, int m, int d) {
		return daysBetween(y, 1, 1, m, d);
	}
	
	public static int daysToNewYear(int y, int m, int d) {
		return daysBetween(y, m, d, 12, 31) + 1;
	}
	
	
	public static void main(String[] args) {
		Scanner scantron4000 = new Scanner(System.in);
		
		System.out.println("This program will calculate the number of days a person lived, if they were born on the start date and died on the end date (being born and dying on the same day still counts as one day of life)!");
		System.out.println("=== START DATE ===");
		System.out.print("Year: ");
		int year1 = scantron4000.nextInt();
		System.out.print("Month: ");
		int month1 = scantron4000.nextInt();
		System.out.print("Day: ");
		int day1 = scantron4000.nextInt();
		System.out.println("\n");		
		System.out.println("=== END DATE ===");
		System.out.print("Year: ");
		int year2 = scantron4000.nextInt();
		System.out.print("Month: ");
		int month2 = scantron4000.nextInt();
		System.out.print("Day: ");
		int day2 = scantron4000.nextInt();
		System.out.println("\n");
		
		if(!(isValid(year1, year2, month1, month2, day1, day2))) {System.out.println("Invalid entries! Please try harder next time.");}
		else {
			int dayCounter = 1;
			if (year1 == year2) {
				dayCounter += daysBetween(year1, month1, day1, month2, day2);
			} else {
				dayCounter += daysToNewYear(year1, month1, day1);
				int currentYear = year1 + 1;
				while (currentYear < year2) {
					dayCounter += daysInYear(currentYear);
					currentYear++;
				}
				dayCounter += daysSoFar(year2, month2, day2);
			}
			System.out.println("\nThe length of your life has been " + dayCounter + " days");
		}
	}
}