package controller;

public class InputChecker
{
	public static final int LETTERS_ONLY = 0;
	public static final int NUMBERS_ONLY = 1;
	public static final int LETTERS_AND_NUMBERS_ONLY = 2;
	public static final int JAVA_IDENTIFIER = 3;
	public static final int NO_SPACES = 4;
	
	public static boolean isValid(char[] input, int type)
	{
		switch(type)
		{
		case LETTERS_ONLY:
			for(int index = 0; index < input.length; index++)
				if(!Character.isLetter(input[index]))
					return false;
			return true;
		
		case NUMBERS_ONLY:
			for(int index = 0; index < input.length; index++)
				if(!Character.isDigit(input[index]))
					return false;
			return true;
		
		case LETTERS_AND_NUMBERS_ONLY:
			for(int index = 0; index < input.length; index++)
				if(!Character.isLetterOrDigit(input[index]))
					return false;
			return true;
			
		case JAVA_IDENTIFIER:
			for(int index = 0; index < input.length; index++)
				if(!Character.isJavaIdentifierPart(input[index]))
					return false;
			return true;
			
		case NO_SPACES:
			for(int index = 0; index < input.length; index++)
				if(!Character.isSpaceChar(input[index]))
					return false;
			return true;
		
		default:
			throw new IllegalArgumentException();
		}
	}
}