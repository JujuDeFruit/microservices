package ca.uqac.api.controller;

import ca.uqac.api.entity.Credentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.firebase.cloud.FirestoreClient;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authentication")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<Credentials> login(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
        List<QueryDocumentSnapshot> users =  FirestoreClient
                .getFirestore()
                .collection("users")
                .get()
                .get()
                .getDocuments();

        final Optional<QueryDocumentSnapshot> userOpt = users
                .stream()
                .filter(u -> u.get("email").equals(email))
                .findFirst();

        if(!userOpt.isPresent()) throw new Exception("Aucun utilisateur !");
        else {
            final QueryDocumentSnapshot user = userOpt.get();
            if (user.get("password").equals(password)) {
                // credentials.setId(user.);
                Credentials credentials = new Credentials();
                credentials.setId(user.getId());
                credentials.setPassword("");
                credentials.setEmail(email);
                return ResponseEntity.ok(credentials);
            } else throw new Exception("Le mot de passe et l'email ne correspondent pas !");
        }

    }
}
