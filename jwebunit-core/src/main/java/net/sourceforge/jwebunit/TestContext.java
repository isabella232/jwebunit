/******************************************************************************
 * jWebUnit project (http://jwebunit.sourceforge.net)                         *
 * Distributed open-source, see full license under LICENCE.txt                *
 ******************************************************************************/
package net.sourceforge.jwebunit;

import javax.servlet.http.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Establish context for tests (things such as locale, base url for the
 * application, cookies, authorization). The context can be accessed through
 * the {@link net.sourceforge.jwebunit.WebTestCase}or
 * {@link net.sourceforge.jwebunit.WebTester}.
 * 
 * @author Julien Henry
 * @author Wilkes Joiner
 * @author Jim Weaver
 */
public class TestContext {
	private String user;
	private String passwd;
    private String domain;
	private List<Cookie> cookies;
	private boolean hasBasicAuth = false;
    private boolean hasNTLMAuth = false;
	private Locale locale = Locale.getDefault();
	private String resourceBundleName;
	private String baseUrl = "http://localhost:8080";
	private String userAgent;
	private String proxyName;
	private int proxyPort = 80;

	/**
	 * Construct a test client context.
	 */
	public TestContext() {
		cookies = new ArrayList<Cookie>();
	}

	/**
	 * Set basic authentication information for the test context.
	 * 
	 * @param user
	 *            user name
	 * @param passwd
	 *            password
	 */
	public void setAuthorization(String user, String passwd) {
		this.user = user;
		this.passwd = passwd;
		hasBasicAuth = true;
	}

    /**
     * Set NTLM authentication information for the test context.
     * 
     * @param user
     *            user name
     * @param passwd
     *            password
     */
    public void setNTLMAuthorization(String user, String passwd, String domain) {
        this.user = user;
        this.passwd = passwd;
        this.domain = domain;
        hasNTLMAuth = true;
    }

    /**
	 * Add a cookie to the test context. These cookies are set on the
	 * WebConversation when an {@link HttpUnitDialog}is begun.
	 * 
	 * @param name
	 *            cookie name.
	 * @param value
	 *            cookie value.
	 */
	public void addCookie(String name, String value) {
		cookies.add(new Cookie(name, value));
	}

	/**
	 * Return true if a basic authentication has been set on the context via
	 * {@link #setAuthorization}.
	 */
	public boolean hasAuthorization() {
		return hasBasicAuth;
	}

    /**
     * Return true if a NTLM authentication has been set on the context via
     * {@link #setNTLMAuthorization}.
     */
    public boolean hasNTLMAuthorization() {
        return hasNTLMAuth;
    }

    /**
	 * Return true if one or more cookies have been added to the test context.
	 */
	public boolean hasCookies() {
		return cookies.size() > 0;
	}

	/**
	 * Return the authorized user for the test context.
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Return the user password.
	 */
	public String getPassword() {
		return passwd;
	}
    
    /**
     * Return the user domain.
     */
    public String getDomain() {
        return domain;
    }

	/**
	 * Return the cookies which have been added to the test context.
	 */
	public List getCookies() {
		return cookies;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public boolean hasUserAgent() {
		return userAgent != null;
	}

	/**
	 * Return the locale established for the test context. If the locale has
	 * not been explicitly set, Locale.getDefault() will be returned.
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Set the locale for the test context.
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Set a resource bundle to use for the test context (will be used to
	 * lookup expected values by key in WebTester).
	 * 
	 * @param name
	 *            path name of the resource bundle.
	 */
	public void setResourceBundleName(String name) {
		resourceBundleName = name;
	}

	/**
	 * Return the test context resource bundle for expected value lookups.
	 */
	public String getResourceBundleName() {
		return resourceBundleName;
	}

	/**
	 * Return the proxy server name Contributed by Jack Chen
	 */
	public String getProxyName() {
		return proxyName;
	}

	/**
	 * Set the proxy server name for the test context. Contributed by Jack Chen
	 */
	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}

	/**
	 * Return the proxy server port Contributed by Jack Chen
	 */
	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * Set the proxy server port for the test context. Contributed by Jack Chen
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * Return true if a proxy name is set {@link #setProxyName}. Contributed
	 * by Jack Chen
	 */
	public boolean hasProxy() {
		return proxyName != null && proxyName.trim().length() > 0;
	}

	/**
	 * Return the base URL for the test context. The default base URL is port
	 * 8080 on localhost.
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * Set the base url for the test context.
	 * 
	 * @param url
	 *            Base url value - A trailing "/" is appended if not provided.
	 */
	public void setBaseUrl(String url) {
		baseUrl = url.endsWith("/") ? url : url + "/";
	}

}
