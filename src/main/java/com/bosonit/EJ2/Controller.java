package com.bosonit.EJ2;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.tree.ExpandVetoException;
import java.util.List;


@RestController
@RequestMapping("/person")
public class Controller {

    @Autowired
    PersonaEntService personaEntService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public PersonaDTO addPersona(@RequestBody PersonaDTO personaDTO) {
        PersonaEnt personaEnt = personaEntService.addPersona(modelMapper.map(personaDTO, PersonaEnt.class));
        personaDTO.setId_persona(personaEnt.getId_persona());
        return personaDTO;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id) throws Exception {
        PersonaEnt personaEnt = personaEntService.getPersonaByID(id);
        personaEntService.deletePerson(personaEnt);
        return "Persona eliminada";
    }

    @PutMapping("/update/({id}")
    public String updatePersona(@PathVariable Integer id, @RequestBody PersonaDTO personaDTO) throws Exception {
        PersonaEnt personaEnt = personaEntService.getPersonaByID(id);
        personaEntService.updatePerson(personaEnt,personaDTO);
        return "Persona editada";
    }


    @GetMapping("/id/{id}")
    //REVISAR DTO
    public PersonaEnt getPersonByID(@PathVariable Integer id) throws Exception {
        try {
            PersonaEnt personaEnt = personaEntService.getPersonaByID(id);
            return personaEnt;
        } catch (Exception e) {
            throw new Exception("No se encuenta la persona");
        }
    }

    //REVISAR DTO
    @GetMapping("/name/{nombre}")
    public List<PersonaEnt> getPersonByName(@PathVariable String nombre) throws Exception {
        return personaEntService.getPersonByName(nombre);

    }
    @GetMapping("/all")
    public List<PersonaEnt> getAllPerson(){
        return personaEntService.getAllPerson();

    }


}



