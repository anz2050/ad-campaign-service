package com.anz.ad.campaign.util;

import java.time.LocalDateTime;

public class DateTimeUtility {
	
	public static LocalDateTime addSecondsToCreatedDateTime(LocalDateTime startDate, int seconds){
	    LocalDateTime changeDate = startDate.plusSeconds(seconds);
	    return changeDate;
	}
	
	
	public static boolean isCurrentTimeIsGreater(LocalDateTime createdDateTime, LocalDateTime currentDateTime){
		boolean result = false;
		if (createdDateTime.isAfter(currentDateTime)) {
			result = true;
		}
	    return result;
	}
	
	public static boolean isCurrentTimeIsLessThanCampignTime(LocalDateTime createdDateTime, LocalDateTime currentDateTime){
		boolean result = false;
		if (createdDateTime.isBefore(currentDateTime)) {
			result = true;
		}
	    return result;
	}
	
	
	/*public static void main(String []args) {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		LocalDateTime prevDateTime = currentDateTime.minusSeconds(5);
		LocalDateTime changeDate = addSecondsToCreatedDateTime(currentDateTime, 60);
		
		System.out.println("currentDateTime: " + currentDateTime);
		System.out.println("changeDate: " + changeDate);
		
		
		if (isCurrentTimeIsGreater(currentDateTime, changeDate)) {
			System.out.println("The current date time is  greater than change date time");
			
		} else {
			System.out.println("The current date time is  lesser than change date time");
		}
	}*/

}
