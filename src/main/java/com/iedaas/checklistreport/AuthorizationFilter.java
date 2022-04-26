package com.iedaas.checklistreport;

import com.iedaas.checklistreport.dto.UserDTO;
import com.iedaas.checklistreport.entity.Owner;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class AuthorizationFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    private static final String HEADER_STRING = "Authorization";

    @Value("${secretKey}")
    private String secret;

    public Owner authenticate(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        int userId;
        String userUid = null;
        String userImage = null;
        String fullName = null;
        String role = null;

        if(token==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Token Found");
        }
        Owner owner = new Owner();
        try {
            logger.info("Validating Jwt token :={}", token);
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody();
            userId = claims.get("user_id", Integer.class);
            userUid = claims.get("user_uid", String.class);
            fullName = claims.get("full_name", String.class);
            userImage = claims.get("image_url", String.class);
            role = claims.get("role", String.class);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token or token is expired");
        }
        owner = new Owner(userId, UUID.fromString(userUid), fullName, userImage, role);
        return owner;
    }
}
