package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {


    @GetMapping("/hello-basic")
    public String helloBasic() {

        log.info("helloBasic");
        return "ok";
    }

    @GetMapping("/mapping-get-v1")
    public String mappingGetV1(){
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mappingGetV2");
        return "ok";
    }


    /**
     * Path Variable 사용
     * 변수명을 맞추면 생략 가능.
     * @param userId
     * @return
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId){
        log.info("mapping Path userid = {}", userId);
        return "ok";
    }

    /**
     * Path Variable 다중 매핑
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable Integer userId, @PathVariable Integer orderId){
        log.info("Mapping Path Userid = {}, Mapping Path OrderId = {}",userId,orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mapping Param");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mapping Header");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑
     */
    @PostMapping(value = "/mapping-type", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingType(){
        log.info("mapping Type");
        return "ok";
    }

    /**
     * Accept, Produce
     * @return
     */
    @PostMapping(value="/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduce(){
        log.info("mapping Produce");
        return "ok";
    }
}
