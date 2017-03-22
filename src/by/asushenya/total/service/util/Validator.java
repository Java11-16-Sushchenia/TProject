package by.asushenya.total.service.util;

public class Validator {
	
	public static boolean validateId(int id){
		if(id <= 0) {
			return false;
		}		
		return true;
	}
	
	public static boolean validateRateAmount(double amount){
		if(amount <= 0){
			return false;
		}
		return true;
	}
}
