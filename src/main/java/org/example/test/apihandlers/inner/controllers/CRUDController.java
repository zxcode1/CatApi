package org.example.test.apihandlers.inner.controllers;

import lombok.RequiredArgsConstructor;
import org.example.test.entity.CatDTO;
import org.example.test.apihandlers.inner.services.CRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cats")
public class CRUDController {
    private final CRUDService crudService;

    @GetMapping("/{id}")
    public ResponseEntity<CatDTO> getCatByIdOld(@PathVariable Long id) {
        return crudService.getCatByIdOld(id);
    }

//    @PostMapping("/reg")
//    public ResponseEntity<String> register(@RequestBody Map<String, String> regRequest) {
//        crudService.register(regRequest);
//        return ResponseEntity.ok("Cat was registered successfully");
//    }

    @PutMapping("/updateLogin/{id}")
    public ResponseEntity<String> updateloginOld(@PathVariable Long id, @RequestBody Map<String, String> updateUserRequest) {
        crudService.updateLoginOld(id, updateUserRequest);
        return ResponseEntity.ok("Cat was updated successfully");
    }

    @PutMapping("/updateLogin/{id}")
    public ResponseEntity<String> updatepassOld(@PathVariable Long id, @RequestBody Map<String, String> updateUserRequest) {
        crudService.updatePasswordOld(id, updateUserRequest);
        return ResponseEntity.ok("Cat was updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOld(@PathVariable Long id) {
        crudService.deleteOld(id);
        return ResponseEntity.ok("Cat was deleted");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        crudService.delete(id);
        return ResponseEntity.ok("hell");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addArticle(@RequestHeader(name = "Authorization") String token, @RequestBody Map<String, String> request){
        crudService.addArticle(request);
        return ResponseEntity.ok("added");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getArticleById(@PathVariable Long id, @RequestHeader(name = "Authorization") String token){
        crudService.getArticleById(id);
        return ResponseEntity.ok("zxc");
    }

}




