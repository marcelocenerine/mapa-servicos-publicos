package br.com.servicospublicos.view.helper;

import static org.apache.commons.collections.CollectionUtils.find;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.Predicate;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.servicospublicos.infra.util.CryptUtil;

@Component
@Scope("application")
public class FacebookAuthHelper {
	
	public static final String FACEBOOK_APP_ID = "670595456295135";
	private static final String FACEBOOK_APP_SECRET = "58a7d254211c33b9cc526a976712853c";
	
	@Autowired
	private ServletContext context;

	@PostConstruct
	public void init() {
		context.setAttribute("facebookAppId", FACEBOOK_APP_ID);
	}
	
	
	public boolean isFacebookIdValid(String facebookUserId, HttpServletRequest request) {
		Cookie fbCookie = findCookie(request);
		if (fbCookie == null) return false;
		return facebookUserId.equals(extractUserId(fbCookie));
	}
	
	private Cookie findCookie(HttpServletRequest request) {
		final String cookieId = String.format("fbsr_%s", FACEBOOK_APP_ID);
		Cookie cookie = (Cookie) find(Arrays.asList(request.getCookies()), new Predicate() {
			@Override
			public boolean evaluate(Object cookie) { return ((Cookie) cookie).getName().equals(cookieId); }
		});
		return isCookieValid(cookie) ? cookie : null;
	}
	
	private boolean isCookieValid(Cookie fbCookie) {
		String[] splited = fbCookie.getValue().split("\\.");
        String signature = splited[0];
        String encoded = splited[1];
        String mac = CryptUtil.calculateHMAC(encoded, FACEBOOK_APP_SECRET).replace("/", "_").replace("+", "-").replace("=", "");
        return mac.equals(signature);
	}
	
	private String extractUserId(Cookie fbCookie) {
		try {
			String encoded = fbCookie.getValue().split("\\.")[1];
			String json = decodeBase64(encoded);
			return (String) new JSONObject(json).get("user_id");
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
	
    private String decodeBase64(String encoded) {
        return new String(Base64.decodeBase64(encoded));
    }
	
	
	public String getAppId() {
		return FACEBOOK_APP_ID;
	}
	
	public String getAppSecret() {
		return FACEBOOK_APP_SECRET;
	}
}
