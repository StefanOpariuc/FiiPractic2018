package com.fiipractic.health.paralel;

import com.fiipractic.health.entity.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stefan (Stefan.Opariuc@tss-yonder.com)
 * @since 29/04/2018
 **/
@RestController
@RequestMapping(value = "paralel")
public class ParalelApi {

    @Autowired
    private ParalelExcutor paralelExcutor;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void savePatient() throws Exception {
        paralelExcutor.execute();
    }

}
