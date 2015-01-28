package ro.teamnet.bootstrap.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.teamnet.bootstrap.domain.DictionaryCollection;
import ro.teamnet.bootstrap.extend.AppPage;
import ro.teamnet.bootstrap.extend.AppPageable;
import ro.teamnet.bootstrap.repository.DictionaryCollectionRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

/**
 * REST controller for managing dictionary collections.
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryCollectionResource {
    private final Logger log = LoggerFactory.getLogger(DictionaryCollectionResource.class);

    @Inject
    private DictionaryCollectionRepository dictionaryCollectionRepository;

    /**
     * POST  /rest/collections -> Create a new dictionary collection.
     */
    @RequestMapping(value = "/rest/dictionaryCollections",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody DictionaryCollection dictionaryCollection) {
        log.debug("REST request to save DictionaryCollection : {}", dictionaryCollection);
        dictionaryCollectionRepository.save(dictionaryCollection);
    }


    /**
     * GET  /rest/dictionaryCollections -> get all the dictionary collections.
     */
    @RequestMapping(value = "/rest/dictionaryCollections",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public AppPage<DictionaryCollection> getAll(AppPageable appPageable) {
        log.debug("REST request to get all DictionaryCollections");
        return dictionaryCollectionRepository.findAll(appPageable);
    }

    /**
     * GET  /rest/dictionaryCollections/:id -> get the "id" dictionary collection.
     */
    @RequestMapping(value = "/rest/dictionaryCollections/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DictionaryCollection> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get DictionaryCollection : {}", id);
        DictionaryCollection dictionaryCollection = dictionaryCollectionRepository.findOne(id);
        if (dictionaryCollection == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dictionaryCollection, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/dictionaryCollections/:id -> delete the "id" dictionary collection.
     */
    @RequestMapping(value = "/rest/dictionaryCollections/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete DictionaryCollection : {}", id);
        dictionaryCollectionRepository.delete(id);
    }
}