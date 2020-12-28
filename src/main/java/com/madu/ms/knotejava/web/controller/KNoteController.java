package com.madu.ms.knotejava.web.controller;

import com.madu.ms.knotejava.entity.Note;
import com.madu.ms.knotejava.repo.NoteRepository;
import com.madu.ms.knotejava.service.KNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/k-note")
public class KNoteController {

    @Autowired
    private KNoteService kNoteService;

    @GetMapping
    public String index(Model model) {
        kNoteService.getAllNotes(model);
        return "index";
    }

    @PostMapping("/note")
    public String saveNotes(@RequestParam("image") MultipartFile file,
                            @RequestParam String description,
                            @RequestParam(required = false) String publish,
                            @RequestParam(required = false) String upload,
                            Model model) throws Exception {

        if (publish != null && publish.equals("Publish")) {
            kNoteService.saveNote(description, model);
            kNoteService.getAllNotes(model);
            return "redirect:/";
        }
        if (upload != null && upload.equals("Upload")) {
            if (file != null && file.getOriginalFilename() != null &&
                    !file.getOriginalFilename().isEmpty()) {
                kNoteService.uploadImage(file, description, model);
            }
            kNoteService.getAllNotes(model);
            return "index";
        }
        return "index";
    }
}
