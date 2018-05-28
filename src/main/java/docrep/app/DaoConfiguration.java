package docrep.app;

import docrep.dao.document.DocumentDAO;
import docrep.db.tables.daos.*;
import docrep.profile.DbProfile;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan({"docrep.*"})
@EnableTransactionManagement
public class DaoConfiguration {

    @Autowired
    DSLContext dslContext;

    @Bean
    @DbProfile
    AccountDao accountDao() {
        return new AccountDao(dslContext.configuration());
    }

    @Bean
    @DbProfile
    ContactDao contactDao() {
        return new ContactDao(dslContext.configuration());
    }

    @Bean
    @DbProfile
    DocumentOpinionDao documentOpinionDao() {
        return new DocumentOpinionDao(dslContext.configuration());
    }

    @Bean
    @DbProfile
    PersonDao personDao() {
        return new PersonDao(dslContext.configuration());
    }

    @Bean
    @DbProfile
    DocumentDAO documentDao() {
        return new DocumentDAO(dslContext.configuration());
    }

    @Bean
    @DbProfile
    AddressDao addressDao() {
        return new AddressDao(dslContext.configuration());
    }

    @Bean
    @DbProfile
    StorageLocationDao storageLocationDao() {
        return new StorageLocationDao(dslContext.configuration());
    }

}
