package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;


/**
 * 회원 목록 조회 : GET /users
 * 회원 등록 : POST /users
 * 회원 조회 : GET /users/{userId}
 * 회원 수정 : PATCH /users/{userId}
 * 회원 삭제 : DELETE /users/{userId}
 */

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String users(){
        return "Get Users";
    }

    @PostMapping
    public String addUser(){
        return "Add User";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable Integer userId){
        return "Find User = " + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable Integer userId){
        return "Update User = " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId){
        return "Delete User = " + userId;
    }
}
