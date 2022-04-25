package ca.uqac.api.controller;

import ca.uqac.api.entity.CVExperience;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/cv")
public class CVController {

    @GetMapping("/experience")
    public ResponseEntity<CVExperience> getHeader(@RequestParam("id") String id) throws Exception {
        Optional<QueryDocumentSnapshot> headerOpt = FirestoreClient
                .getFirestore()
                .collection("experience")
                .get()
                .get()
                .getDocuments()
                .stream().filter(d -> Objects.equals(d.get("id"), id))
                .findFirst();

        if (!headerOpt.isPresent()) {
            CVExperience cvexperience = new CVExperience();
            cvexperience.setId(id);
            return ResponseEntity.ok(cvexperience);
        }
	System.out.println(headerOpt.get().toObject(CVExperience.class).toString());
	System.out.println(id);
        return ResponseEntity.ok(headerOpt.get().toObject(CVExperience.class));
    }

    @PostMapping("/modifier")
    public ResponseEntity<?> postHeader(@RequestBody CVExperience cvexperience) throws Exception {
        Optional<QueryDocumentSnapshot> headerOpt = FirestoreClient
                .getFirestore()
                .collection("experience")
                .get()
                .get()
                .getDocuments()
                .stream().filter(d -> Objects.equals(d.get("id"), cvexperience.getId()))
                .findFirst();

        if (!headerOpt.isPresent()) {
            FirestoreClient
                    .getFirestore()
                    .collection("experience")
                    .add(cvexperience);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        final DocumentReference docRef = headerOpt.get().getReference();

        ObjectMapper mapper = new ObjectMapper();

        // Convert POJO to Map
        Map<String, Object> map = mapper.convertValue(cvexperience, new TypeReference<Map<String, Object>>() {});

        FirestoreClient
                .getFirestore()
                .document(docRef.getPath())
                .update(map);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
