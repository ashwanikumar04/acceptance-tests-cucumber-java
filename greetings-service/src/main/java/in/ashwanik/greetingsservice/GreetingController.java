package in.ashwanik.greetingsservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class GreetingController {
    @GetMapping(path = "/greet")
    public ResponseEntity<?> greet() {
        return ResponseEntity.ok().build();
    }
}
