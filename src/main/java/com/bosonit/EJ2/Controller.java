package com.bosonit.EJ2;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    public PersonaDTO addPersona(@RequestBody PersonaDTO personaDTO) throws Exception {
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

    @PutMapping("/update/{id}")
    public PersonaEnt updatePersona(@PathVariable Integer id, @RequestBody PersonaDTO personaDTO) throws Exception {
        PersonaEnt personaEnt = personaEntService.getPersonaByID(id);
        personaEntService.updatePerson(personaEnt,personaDTO);
        return personaEnt;
    }

    @GetMapping("/id/{id}")



    public PersonaDTO getPersonByID(@PathVariable Integer id) throws Exception {
        try {
            PersonaEnt personaEnt = personaEntService.getPersonaByID(id);
            PersonaDTO personaDTO = modelMapper.map(personaEnt,PersonaDTO.class);
            return personaDTO;
        } catch (Exception e) {
            throw new Exception("No se encuentra la persona");
        }
    }

    @GetMapping("/name/{nombre}")
    public List<PersonaDTO> getPersonByName(@PathVariable String nombre) throws Exception {
        List <PersonaEnt> personaEntList = personaEntService.getPersonByName(nombre);
        TypeToken<List<PersonaDTO>> typeToken = new TypeToken<>() {
        };
        List<PersonaDTO> personaDTOList = modelMapper.map(personaEntList, typeToken.getType());
        return personaDTOList;
    }

    @GetMapping("/all")
    public List<PersonaDTO> getAllPerson(){
        List <PersonaEnt> personaEntList = personaEntService.getAllPerson();
        TypeToken<List<PersonaDTO>> typeToken = new TypeToken<>() {
        };
        List<PersonaDTO> personaDTOList = modelMapper.map(personaEntList, typeToken.getType());
        return personaDTOList;
    }

}



