package com.bosonit.EJ2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaEntService {

    @Autowired
    PersonaRepository personaRepository;

    public PersonaEnt addPersona(PersonaEnt personaEnt){
        return personaRepository.save(personaEnt);
    }

    public PersonaEnt getPersonaByID(Integer id) throws Exception
    {
        return personaRepository.findById(id).orElseThrow(()-> new Exception("Persona no encontrada"));
    }

    //getall
    //delete
    //update

    //getid
    //getnombre
}
