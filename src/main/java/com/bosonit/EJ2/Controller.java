package com.bosonit.EJ2;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
public class Controller {

    @Autowired
    PersonaEntService personaEntService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public PersonaDTO addPersona(@RequestBody PersonaDTO personaDTO){
        PersonaEnt personaEnt = personaEntService.addPersona(modelMapper.map(personaDTO,PersonaEnt.class));
        personaDTO.setId_persona(personaDTO.getId_persona());
                return personaDTO;
    }

    @GetMapping("/{id}")
    public PersonaEnt getPersonByID(@PathVariable Integer id) throws Exception {
        try {
            PersonaEnt personaEnt = personaEntService.getPersonaByID(id);
            return personaEnt;
        } catch (Exception e) {
            throw new Exception("No se encuenta la persona");
        }
    }

//    @GetMapping("/all")
//    public String getAllPerson(){
//        return "Todas las personas";
//    }
//
//    @GetMapping("/{nombre}")
//    public String getAllPersonByname(@PathVariable String nombre_persona){
//        return "Persona por nombre";
//    }

}
