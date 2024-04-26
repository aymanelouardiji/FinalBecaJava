package com.ntt.finalProject.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


import com.ntt.finalProject.model.Film;

import jakarta.persistence.EntityManager;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{
    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration
            (RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod [] unsupportedActions = {HttpMethod.POST,HttpMethod.PUT,HttpMethod.PATCH,HttpMethod.DELETE};

        config.getExposureConfiguration()
                .forDomainType(Film.class)
                .withItemExposure((metadata, httpdMethods)
                        -> httpdMethods.disable(unsupportedActions))
                .withCollectionExposure((metadata, httpdMethods)
                        -> httpdMethods.disable(unsupportedActions));

        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream()
                .map(e -> e.getJavaType()).collect(Collectors.toList()).toArray(Class[]::new));
        // call an internal helper method
        exposeIds(config);

        //config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(e -> e.getJavaType()).collect(Collectors.toList()).toArray(Class[]::new));
        //config.exposeIdsFor(AbstractModel.class);
        //config.exposeIdsFor(Nationalite.class);
    }


    private void exposeIds(RepositoryRestConfiguration config) {

        // expose entity ids //

        // - get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // - create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // - get the entity types for the entities
        for (EntityType tempEntityType : 	  entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // - expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        System.out.println("*** Liste entityClasses ******");
        for(Class cl : entityClasses) {
            System.out.println(cl.getName());
        }
        System.out.println("*** Liste domainTypes ******");
        for(Class cl : domainTypes) {
            System.out.println(cl.getName());
        }
        config.exposeIdsFor(domainTypes);

        HttpMethod [] unsupportedActions = {HttpMethod.POST,HttpMethod.PATCH, HttpMethod.PUT,HttpMethod.DELETE};
//        for(Class cl : domainTypes) {
//            if(cl != Rating.class ){
//                config.getExposureConfiguration()
//                        .forDomainType(cl)
//                        .withItemExposure((metadata, httpdMethods)
//                                -> httpdMethods.disable(unsupportedActions))
//                        .withCollectionExposure((metadata, httpdMethods)
//                                -> httpdMethods.disable(unsupportedActions));
//            }
//
//        }


    }



}
