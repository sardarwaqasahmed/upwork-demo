package com.bulk.registration.validator;

import com.bulk.registration.model.Sim;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor

public class SimRecordValidator {
    private final Validator validator;

    public Sim validateRecord(Sim sim) {

        Set<ConstraintViolation<Sim>> violations = validator.validate(sim);
        if (!violations.isEmpty()) {
            violations.forEach(violation -> log.error("Validation failed for " + sim.getPhoneNumber() + " because of " + violation.getMessage()));
            return null;
        }

        return sim;
    }
}