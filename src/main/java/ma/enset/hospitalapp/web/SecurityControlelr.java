package ma.enset.hospitalapp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class SecurityControlelr {

	@GetMapping("/error_page")
	public String not_Authorized() {
		return "<h2>page not authorized</h2>";
	}
}
