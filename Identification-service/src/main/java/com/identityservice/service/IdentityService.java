package com.identityservice.service;

import com.identityservice.model.Person;
import java.util.List;
import java.io.IOException;

public interface IdentityService {
    void process(List<Person> people, String validOutputFile, String invalidOutputFile) throws IOException;
}