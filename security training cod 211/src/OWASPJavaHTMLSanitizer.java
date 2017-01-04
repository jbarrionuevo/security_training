import org.owasp.html.*;

public class OWASPJavaHTMLSanitizer {
	static String taintedHTML = "<b>JAVA HTML Sanitizer example:</b> <script>script_malicioso</script>";

	public static void main(String[] args) {
		PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
		
		String safeHTML = policy.sanitize(taintedHTML);
		System.out.println(safeHTML);
	}
}
