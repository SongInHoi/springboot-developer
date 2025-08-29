package me.shinsunyoung.springbootdeveloper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @GetMapping("/quiz") // quiz 패스로 GET 요청이 오면 quiz()라는 메서드에서 요청을 처리합니다.
    public ResponseEntity<String> quiz(@RequestParam("code") int code) { // 요청 파리미터의 키가 code이면 int 자료형의 변수와 매핑되며, code 값에 따라 다른 응답을 보낸다.
        switch (code) {
            case 1:
                return ResponseEntity.created(null).body("Created!");
            case 2:
                return ResponseEntity.badRequest().body("Bad Request!");
            default:
                return ResponseEntity.ok().body("Ok!");
        }
    }

    @PostMapping("/quiz") // 이 메서드는 요청 값을 Code라는 객체로 매핑한 후애 value 값에 따라 다른 응답을 보냅니다.
    public ResponseEntity<String> quiz2(@RequestBody Code code) {

        switch (code.value()) {
            case 1:
                return ResponseEntity.status(403).body("Forbidden!");
            default:
                return ResponseEntity.ok().body("OK!");
        }
    }
    
    // 매핑할 객체로 사용하기 위해 선언한 레코드
    record Code(int value){} // 3

}
