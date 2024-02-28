//package org.example.test.controllers;
//
//@RestController
//@RequestMapping("/api/secure")
//public class SecureResourceController {
//
//    @Autowired
//    private SecureResourceService secureResourceService;
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/profile")
//    public ResponseEntity<UserProfile> getUserProfile() {
//        UserProfile userProfile = secureResourceService.getUserProfile();
//        return ResponseEntity.ok(userProfile);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/admin-only")
//    public ResponseEntity<String> getAdminResource() {
//        return ResponseEntity.ok("This is an admin-only resource");
//    }
//}
//
