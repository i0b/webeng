package de.uulm.mi.web.http;

import java.io.ObjectInputStream.GetField;
import java.util.regex.Matcher;

import de.uulm.mi.web.Http;

/**
 * An enum of available HTTP versions.
 * 
 * @see also http://tools.ietf.org/html/rfc2616.html#section-3.1
 * 
 * @author Benjamin Erb
 * 
 */
public enum HttpVersion
{
	/**
	 * HTTP/1.0
	 */
	VERSION_1_0(1, 0),

	/**
	 * HTTP/1.1
	 */
	VERSION_1_1(1, 1);

	private final int major;
	private final int minor;

	private HttpVersion(int major, int minor)
	{
		this.major = major;
		this.minor = minor;
	}

	@Override
	public String toString()
	{
		return Http.HTTP + Http.PROTOCOL_DELIMITER + major + "." + minor;
	}

	/**
	 * Extracts the HTTP version from the request line.
	 * 
	 * @param requestLine HTTP request line
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static HttpVersion extractVersion(String requestLine) throws IllegalArgumentException
	{
		//TODO: Extract HTTP Version from request line (see http://tools.ietf.org/html/rfc2616.html#section-5.1).
		// Bianca, Leo
		String extractedHttpVersion = requestLine.split(" ")[2];
		// extractedHttpVersion is expected to be "HTTP/1.0" or "HTTP/1.1"
		if(extractedHttpVersion != null){
			 if (extractedHttpVersion.equals(HttpVersion.VERSION_1_0.toString()))
				 return HttpVersion.VERSION_1_0;
			 else if (extractedHttpVersion.equals(HttpVersion.VERSION_1_1.toString()))
				 return HttpVersion.VERSION_1_1;
			 else
				 throw new IllegalArgumentException();
		}else{
			throw new IllegalArgumentException();
		}
	}
}
