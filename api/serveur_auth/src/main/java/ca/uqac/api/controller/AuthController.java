package ca.uqac.api.controller;

import ca.uqac.api.entity.Credentials;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.firebase.cloud.FirestoreClient;

import java.util.List;
import java.util.Objects;
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
                .filter(u -> Objects.equals(u.get("email"), email))
                .findFirst();

        if(!userOpt.isPresent()) throw new Exception("Aucun utilisateur !");
        else {
            final QueryDocumentSnapshot user = userOpt.get();
            if (Objects.equals(user.get("password"), password)) {
                Credentials credentials = new Credentials();
                credentials.setId(user.getId());
                credentials.setPassword("");
                credentials.setEmail(email);
                return ResponseEntity.ok(credentials);
            } else throw new Exception("Le mot de passe et l'email ne correspondent pas !");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Credentials> register(@RequestBody Credentials credentials) throws Exception {
        final CollectionReference userColl = FirestoreClient
                .getFirestore()
                .collection("users");

        List<QueryDocumentSnapshot> users =  userColl
                .get()
                .get()
                .getDocuments();

        final Optional<QueryDocumentSnapshot> userOpt = users.stream().filter(u -> u.get("email").equals(credentials.getEmail())).findFirst();

        if(userOpt.isPresent()) throw new Exception("L'utilisateur existe déjà !");

        final ApiFuture<DocumentReference> new_ = userColl.add(credentials);

        credentials.setId(new_.get().getId());

        return ResponseEntity.ok(credentials);
    }
}
