package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Affiche le formulaire d'inscription
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user"; // Nom de la vue pour le formulaire d'ajout
    }

    // Ajoute un nouvel utilisateur
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user"; // Renvoie au formulaire si des erreurs existent
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index"; // Nom de la vue pour afficher la liste des utilisateurs
    }

    // Affiche le formulaire de mise à jour
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user"; // Nom de la vue pour le formulaire de mise à jour
    }

    // Met à jour l'utilisateur
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user"; // Renvoie au formulaire si des erreurs existent
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index"; // Nom de la vue pour afficher la liste des utilisateurs
    }

    // Supprime un utilisateur
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "index"; // Nom de la vue pour afficher la liste des utilisateurs
    }
    // ajouter un utilisateur
    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
}}
