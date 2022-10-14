package org.crud.wxw.controller;

import org.crud.wxw.model.Person;
import org.crud.wxw.service.BookService;
import org.crud.wxw.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("people")
public class PersonController {
    private final PersonService personService;
    private final BookService bookService;
    @Autowired
    public PersonController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getAll(Model model){
    model.addAttribute("people", personService.findAll());
        return "person/people";
    }
    @GetMapping("/{id}")
    public String getId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        model.addAttribute("books", bookService.getBooksByPersonId(id));
        return "person/person";
    }
    @GetMapping("/new")
    public String newPerson (@ModelAttribute ("person") Person person) {
        return "person/new";
    }
    @PostMapping
    public String create(@ModelAttribute ("person") Person person){
        personService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("person", personService.findById(id));
        return "person/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") Long id) {
        personService.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return "redirect:/people";
    }
}
