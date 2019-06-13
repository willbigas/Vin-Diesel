/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.uteis;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Will
 */
public class Validacao {

    public static String validaEntidade(Object object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            return constraintViolation.getMessage();
        }
        return null;
    }

}
