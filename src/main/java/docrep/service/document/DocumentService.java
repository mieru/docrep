package docrep.service.document;

import docrep.auth.JwtAuthenticatedUser;
import docrep.component.DocumentSessionStoreComponent;
import docrep.component.ValidatorComponent;
import docrep.dao.document.DocumentDAO;
import docrep.db.tables.daos.AccountDao;
import docrep.db.tables.daos.PersonDao;
import docrep.db.tables.pojos.Account;
import docrep.db.tables.pojos.Document;
import docrep.db.tables.pojos.Person;
import docrep.db.tables.pojos.StorageLocation;
import docrep.service.document.dto.DocumentDTO;
import docrep.service.document.dto.DocumentSearchDTO;
import docrep.service.document.dto.DocumentToAdd;
import docrep.service.document.mapper.DocumentMapper;
import docrep.service.storagelocation.StorageLocationService;
import docrep.service.storagelocation.dto.CompleteStorageLocationStructureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    ValidatorComponent validatorComponent;
    @Autowired
    DocumentDAO documentDAO;
    @Autowired
    AccountDao accountDao;
    @Autowired
    PersonDao personDao;
    @Autowired
    StorageLocationService storageLocationService;


    public Collection<DocumentDTO> searchDocuments(DocumentSearchDTO documentSearchDTO) throws Exception {
        validatorComponent.valid(documentSearchDTO);
        if (documentSearchDTO.areAllFieldsNull()) throw new RuntimeException("All search fields are null.");
        return searchAll(documentSearchDTO);
    }

    public Collection<DocumentDTO> searchAll(DocumentSearchDTO documentSearchDTO) throws Exception {
        return documentDAO.search(documentSearchDTO).stream()
                .map(document -> {
                    Account account = null;
                    Person person = null;
                    CompleteStorageLocationStructureDTO storageLocation = null;
                    try {
                        account = accountDao.fetchOneById(document.getOwnerId());
                        person = personDao.fetchOneById(account.getPersonId());
                        storageLocation = storageLocationService.getCompleteStructureStorageLocationById(document.getStorageLocationId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return DocumentMapper.mapDocumentToDocumentDTO(document, person, storageLocation);
                })
                .collect(Collectors.toList());
    }

    public void addToClipboard(Authentication authentication, DocumentDTO documentDTO) {
        JwtAuthenticatedUser user = (JwtAuthenticatedUser) authentication.getPrincipal();
        HashSet<Integer> documentClipboard = DocumentSessionStoreComponent.getUsersDocumentStore().get(user.getName());
        if (documentClipboard == null) {
            documentClipboard = new HashSet<>();
        }
        documentClipboard.add(documentDTO.getId());
        DocumentSessionStoreComponent.getUsersDocumentStore().put(user.getName(), documentClipboard);
    }

    @Transactional
    public void takeDocument(Authentication authentication, DocumentDTO documentDTO) {
        JwtAuthenticatedUser user = (JwtAuthenticatedUser) authentication.getPrincipal();
        Account account = accountDao.fetchOneByUsername(user.getUsername());
        StorageLocation storageLocation = storageLocationService.findOrAddStorageLocationByAccount(account);
        Document document = documentDAO.fetchOneById(documentDTO.getId());
        document.setEditDate(new Timestamp(System.currentTimeMillis()));
        document.setOwnerId(account.getId());
        document.setStorageLocationId(storageLocation.getId());
        documentDAO.update(document);
    }

    public Collection<DocumentDTO> searchFuzzy(String searchPhrase) {
        if (searchPhrase == null) throw new RuntimeException("searchPhrase is null.");

        return documentDAO.searchFuzzy(searchPhrase).stream()
                .map(document -> {
                    Account account = null;
                    Person person = null;
                    CompleteStorageLocationStructureDTO storageLocation = null;
                    try {
                        account = accountDao.fetchOneById(document.getOwnerId());
                        person = personDao.fetchOneById(account.getPersonId());
                        storageLocation = storageLocationService.getCompleteStructureStorageLocationById(document.getStorageLocationId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return DocumentMapper.mapDocumentToDocumentDTO(document, person, storageLocation);
                })
                .collect(Collectors.toList());
    }

    public String genBarcode() {
        Integer nextDocId = documentDAO.maxDocumentId() + 1;
        String nextDocIdStr = nextDocId.toString();
        String barcode = "";
        for (int i = 0; i < 13 - nextDocIdStr.length(); i++) {
            barcode += "0";
        }
        barcode += nextDocIdStr;
        return barcode;
    }
    @Transactional
    public Integer addDocument(Authentication authentication, DocumentToAdd documentToAdd) {
        JwtAuthenticatedUser user = (JwtAuthenticatedUser) authentication.getPrincipal();
        Account account = accountDao.fetchOneByUsername(user.getUsername());
        StorageLocation storageLocation = storageLocationService.findOrAddStorageLocationByAccount(account);
        Document document = new Document();
        document.setEditDate(new Timestamp(System.currentTimeMillis()));
        document.setOwnerId(account.getId());
        document.setStorageLocationId(storageLocation.getId());
        document.setRegisterDate(new Timestamp(System.currentTimeMillis()));
        document.setBarcode(documentToAdd.getBarcode());
        document.setDescription(documentToAdd.getDescription());
        document.setNumber(documentToAdd.getNumber());
        document.setTitle(documentToAdd.getTitle());

        documentDAO.insert(document);
        return document.getId();
    }
}
