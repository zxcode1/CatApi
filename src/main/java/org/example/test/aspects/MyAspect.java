package org.example.test.aspects;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.test.services.CatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MyAspect {
    private final CatService catService;
    private boolean executed = false;

    @Pointcut("execution(* org.example.test.apihandlers.inner.controllers.*.*(..))")
    public void controllerExecution() {
    }

//    @Before("controllerExecution() && args(token)")
//@Before("execution(public void org.example.test.controllers.CatController.secureEndpoint(String)) && args(token)")
    @Before("controllerExecution() && args(token)")
    public ResponseEntity<String> beforeControllerExecution(JoinPoint joinPoint, String token) {
        try {
            System.out.println("Контроллер был выполнен");
            System.out.println("Method secureEndpoint executed with token: " + token);
            String jwtToken = token.replace("Bearer ", "");

            boolean tokenExpired = catService.isTokenExpired(jwtToken);

            if (!tokenExpired) {
                String tokenContents = String.valueOf(catService.extractAllClaims(jwtToken));
                System.out.println("Received JWT token: " + jwtToken);
                System.out.println("Token contents: " + tokenContents);
                // {customClaim=customValue, sub=dan, iss=serv, iat=1708948038, exp=1708951638, role=user}

                return null;
            } else {
                // 401 Unauthorized
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);   // 401 Unauthorized
        } catch (Exception e) {
            System.out.println("Error processing token: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 500 Internal Server Error
        }
    }
}
