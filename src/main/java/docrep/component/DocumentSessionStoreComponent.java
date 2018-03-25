package docrep.component;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
public class DocumentSessionStoreComponent {
    // Przenieśc na Redis lub inne mechanizm
    private static final HashMap<String, HashSet<Integer>> usersDocumentStore = new HashMap<>();

    public static HashMap<String, HashSet<Integer>> getUsersDocumentStore() {
        return usersDocumentStore;
    }
}
