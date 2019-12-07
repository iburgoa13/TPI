package Util;

import java.io.BufferedReader;
import java.io.IOException;

import Exceptions.FileContentsException;

public class loadLineMethod {
	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";

	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList)
										throws IOException, FileContentsException{

		String[] words;
		String line = inStream.readLine().trim();

		// absence of the prefix is invalid
		if ( !line.startsWith(prefix + ":") )
			throw new FileContentsException(wrongPrefixMsg + prefix);

		// cut the prefix and the following colon off the line then trim it to get attribute contents
		String contentString = line.substring(prefix.length()+1).trim();

		// the attribute contents are not empty
		if (!contentString.equals("")) {

			if (!isList) {
				// split non-list attribute contents into words 
				//   using 1-or-more-white-spaces as separator
				words = contentString.split("\\s+");

				// a non-list attribute with contents of more than one word is invalid
				if (words.length != 1)
					throw new FileContentsException(lineTooLongMsg + prefix);

			} else
				// split list attribute contents into words
				//   using comma+0-or-more-white-spaces as separator
				words = contentString.split(",\\s*");

		// the attribute contents are empty
		} else {

			// a non-list attribute with empty contents is invalid
		    	if (!isList)
		    		throw new FileContentsException(lineTooShortMsg + prefix);

			// a list attribute with empty contents is valid; use a zero-length array to store its words
		    	words = new String[0];
		}

		return words;

	}

}
