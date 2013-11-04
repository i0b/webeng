package de.uulm.mi.web.http;

import java.lang.ProcessBuilder.Redirect;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * An enum of available HTTP status codes.
 * 
 * @see also http://tools.ietf.org/html/rfc2616.html#section-6.1.1
 * 
 * @author Benjamin Erb
 * 
 */
public enum HttpStatusCode
{
	CONTINUE(100, "Continue"),
	OK(200, "OK"),
	NOT_FOUND(404, "NOT FOUND"),
	// Bianca, Leo
	SWITCHING_PROTOCOLS(101, "Switching Protocols"),
	CREATED(201, "Created"),
	ACCEPTED(202, "Accepted"),
	NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
	NO_CONTENT(204, "No Content"),
	RESET_CONTENT(205, "Reset Content"),
	PARTIAL_CONTENT(206, "Partial Content"),
	//MULTIPLE_CHOICES(207, "Multiple Choices"),
	MULTIPLE_CHOICES(300, "Multiple Choices"),
	MOVED_PERMANENTLY(301, "Moved Permanently"),
	FOUND(302, "Found"),
	SEE_OTHER(303, "See other"),
	NOT_MODIFIED(304, "Not Modified"),
	USE_PROXY(305, "Use Proxy"),
	TEMPORARY_REDIRECTED(306, "Temporary Redirected"),
	TEMPORARY_REDIRECT(307, "Temporary Redirect"),
	UNAUTHORIZED(401, "Unauthorized"),
	PAYMENT_REQUIRED(402, "Payment required"),
	FORBIDDEN(403, "FORBIDDEN"),
	BAD_REQUEST(400, "Bad Request"),
	METHOD_NOT_ALLOWED(405, "Method not allowed"),
	NOT_ACCEPTABLE(406, "Not acceptable"),
	PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication required"),
	REQUEST_TIME_OUT(408, "Request Time-Out"),
	CONFLICT(409, "Conflict"),
	GONE(410, "Gone"),
	LENGTH_REQUIRED(411, "Length required"),
	PRECONDITION_FAILED(412, "Precondition failed"),
	REQUEST_ENTITY_TOO_LARGE(413, "Request Entity too large"),
	REQUEST_URI_TOO_LARGE(414, "Request-URI too large"),
	UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
	REQUEST_RANGE_NOT_SATISFIABLE(416, "Request Range not satisfiable"),
	EXPECTATION_FAILED(417, "Expectation failed"),
	INTERNAL_SERVER_ERROR (500, "Internal Server Error"),
	NOT_IMPLEMENTED(501, "Not implemented"),
	BAD_GATEWAY(502, "Bad Gateway"),
	SERVICE_UNAVAILABLE(503, "Service unavailable"),
	GATEWAY_TIME_OUT(504, "Gateway Time-out"),
	HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported");
	
	
	
	//TODO: Complete status code list (see http://tools.ietf.org/html/rfc2616.html#section-6.1.1)

	private final int code;
	private final String reasonPhrase;

	private static final Map<Integer, HttpStatusCode> codeLookupTable = new HashMap<Integer, HttpStatusCode>();
	static
	{
		for (HttpStatusCode s : EnumSet.allOf(HttpStatusCode.class))
		{
			codeLookupTable.put(s.getCode(), s);
		}
	}

	private HttpStatusCode(int code, String reasonPhrase)
	{
		this.code = code;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Returns the numerical code.
	 * 
	 * @return Code
	 */
	public int getCode()
	{
		return code;
	}

	/**
	 * Returns the verbatim code.
	 * 
	 * @return reasons phrase
	 */
	public String getReasonPhrase()
	{
		return reasonPhrase;
	}

	/**
	 * Gets the {@link HttpStatusCode} type by the code number.
	 * 
	 * @param code
	 *            numerical code representation (i.e. 200)
	 * @return assosciated status code
	 */
	public static HttpStatusCode getStatusCode(int code)
	{
		return codeLookupTable.get(code);
	}
}
