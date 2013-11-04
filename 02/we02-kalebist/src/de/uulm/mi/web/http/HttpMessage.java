package de.uulm.mi.web.http;

import java.util.Map;

/**
 * An interface for basic HTTP message operations.
 * 
 * @author Benjamin Erb
 * 
 */
public interface HttpMessage
{
	/**
	 * Returns the used protocol version of this message.
	 * 
	 * @return
	 */
	HttpVersion getHttpVersion();
	void setHttpVersion(HttpVersion httpVersion);

	/**
	 * Returns a list of key-value header fields.
	 * 
	 * @return
	 */
	Map<String, String> getHeaders();
	void setHeaders(Map<String,String> headers);

	/**
	 * Returns the entity as a byte array or null, if no entity is available.
	 * 
	 * @return
	 */
	byte[] getEntity();
	void setEntity(byte[] entity);
}
