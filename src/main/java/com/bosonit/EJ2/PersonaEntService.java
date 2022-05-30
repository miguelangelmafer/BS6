package com.bosonit.EJ2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaEntService {

    @Autowired
    PersonaRepository personaRepository;

    public PersonaEnt addPersona(PersonaEnt personaEnt) throws Exception{
        if(personaEnt.getUsuario().length()>10){throw new Exception("El usuario no debe tener más de 10 caracteres");}

        if(personaEnt.getUsuario() == null || personaEnt.getPassword() == null || personaEnt.getName() == null ||
        personaEnt.getCompany_email() == null || personaEnt.getPersona_email() == null || personaEnt.getCity() == null ||
        personaEnt.getActive() == null || personaEnt.getCreated_date() == null){throw new Exception("Alguno de los campos no puede ser nulo");}
                else{
        return personaRepository.save(personaEnt);}
    }

    public PersonaEnt getPersonaByID(Integer id) throws Exception
    {
        return personaRepository.findById(id).orElseThrow(()-> new Exception("Persona no encontrada"));
    }

    //getnombre

    public List <PersonaEnt> getPersonByName(String name){
        List <PersonaEnt> listPersona = personaRepository.findByName(name);
        return listPersona;
    }

    //getall

    public List<PersonaEnt> getAllPerson() {
        List<PersonaEnt> personaList = personaRepository.findAll();
        return personaList;
    }

    //delete

    public void deletePerson(PersonaEnt personaEnt){
        personaRepository.delete(personaEnt);
    }

    //update

    public PersonaEnt updatePerson(PersonaEnt personaOld, PersonaDTO personaNew) {

        personaOld.setPassword(personaNew.getPassword());
        personaOld.setName(personaNew.getName());
        personaOld.setSurname(personaNew.getSurname());
        personaOld.setCompany_email(personaNew.getCompany_email());
        personaOld.setPersona_email(personaNew.getPersona_email());
        personaOld.setCity(personaNew.getCity());
        personaOld.setActive(personaNew.getActive());
        personaOld.setCreated_date(personaNew.getCreated_date());
        personaOld.setImagen_url(personaNew.getImagen_url());
        personaOld.setTermination_date(personaNew.getTermination_date());
        personaOld.setUsuario(personaNew.getUsuario());
        personaOld.setId_persona(personaOld.getId_persona());

        return personaRepository.save(personaOld);
    }


}
