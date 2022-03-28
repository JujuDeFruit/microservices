package ca.uqac.api.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.cloud.FirestoreClient;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping("")
    public String getIndex() {
        try {
            Firestore firestore = FirestoreClient.getFirestore();
            final CollectionReference c = firestore.collection("items");
            final ApiFuture<QuerySnapshot> s = c.get();
            final QuerySnapshot q = s.get();
            List<QueryDocumentSnapshot> qd = q.getDocuments();

            StringBuilder data = new StringBuilder();
            qd.forEach(d -> data.append((String) d.getData().get("name")));
            return data.toString();
        } catch (Exception e) {
            return e.toString();
        }

    }
}
