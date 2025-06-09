
import com.sap.it.api.mapping.*;

def String removeLeadingZeros(String value){
	return value.replaceFirst("^0+", "") 
}

def String removeLeadingSpaces(String value){
	return value.replaceFirst("^\\s+", "") 
}

def String addLeadingZeros(String value, int numberOfChars){
	return value.padLeft(numberOfChars, "0")
}

def String addLeadingSpaces(String value, int numberOfChars){
	return value.padLeft(numberOfChars)
}

def String addTrailingSpaces(String value, int numberOfChars){
	return value.padRight(numberOfChars)
}

def String padLeft(String value, int numberOfChars, String padding){
	return value.padLeft(numberOfChars, padding)
}

def String padRight(String value, int numberOfChars, String padding){
	return value.padRight(numberOfChars, padding)
}

def String take(String value, int num){
	return value.take(num)
}

def String drop(String value, int num){
	return value.drop(num)
}

def String safeSubstring(String value, int startIndex, int outputLength){
	return value.drop(startIndex).take(outputLength)
}

def String isNumber(String value){
	return value.isNumber().toString()
}