import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;

public class ESAPIEncoder {
	public static void main(String[] args) throws EncodingException {
		String untrustedData = "Juan Ramon";
		
		System.out.println("*Encode for HTML context: " + ESAPI.encoder().encodeForHTML(untrustedData));
		System.out.println("**Encode for HTML attribute context: " + ESAPI.encoder().encodeForHTMLAttribute(untrustedData));
		System.out.println("***Encode for URL context: " + ESAPI.encoder().encodeForURL(untrustedData));		
	}
}
