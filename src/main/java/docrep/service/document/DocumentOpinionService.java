package docrep.service.document;

import docrep.auth.JwtAuthenticatedUser;
import docrep.db.tables.daos.AccountDao;
import docrep.db.tables.daos.DocumentOpinionDao;
import docrep.db.tables.daos.PersonDao;
import docrep.db.tables.pojos.Account;
import docrep.db.tables.pojos.DocumentOpinion;
import docrep.db.tables.pojos.Person;
import docrep.service.document.dto.DocumentOpinionDTO;
import docrep.service.document.dto.DocumentToEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentOpinionService {

    @Autowired
    DocumentOpinionDao documentOpinionDao;

    @Autowired
    AccountDao accountDao;

    @Autowired
    PersonDao personDao;

@Transactional
    public void addOpinion(Authentication authentication, DocumentToEdit documentToEdit) {
        JwtAuthenticatedUser user = (JwtAuthenticatedUser) authentication.getPrincipal();
        Account account = accountDao.fetchOneByUsername(user.getUsername());
        DocumentOpinion documentOpinion = new DocumentOpinion();
        documentOpinion.setAddDate(new Timestamp(System.currentTimeMillis()));
        documentOpinion.setContent(documentToEdit.getOpinion());
        documentOpinion.setAccountId(account.getId());
        documentOpinion.setDocumentId(documentToEdit.getId());
        documentOpinionDao.insert(documentOpinion);
    }


    @Transactional
    public List<DocumentOpinionDTO> getOpinionByDocumentId(Integer documentId) {
        List<DocumentOpinion> documentOpinions = documentOpinionDao.fetchByDocumentId(documentId);
        return documentOpinions.stream()
                .map(documentOpinion -> {
                    return DocumentOpinionDTO.builder()
                    .addDate(documentOpinion.getAddDate())
                            .content(documentOpinion.getContent())
                            .id(documentOpinion.getId())
                            .user(getAccountName(documentOpinion.getAccountId()))
                .build();
    }).collect(Collectors.toList());
    }

    private String getAccountName(Integer accountId) {
      Account account = accountDao.fetchOneById(accountId);
        Person person = personDao.fetchOneById(account.getPersonId());
      return person.getFirstname()+" "+person.getLastname();
    }

}
