package com.madu.ms.knotejava.service.impl;

import com.madu.ms.knotejava.entity.Note;
import com.madu.ms.knotejava.property.KnoteProperties;
import com.madu.ms.knotejava.repo.NoteRepository;
import com.madu.ms.knotejava.service.KNoteService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class KNoteServiceImpl implements KNoteService {

    @Autowired
    private NoteRepository noteRepository;
    private Parser parser = Parser.builder().build();
    private HtmlRenderer renderer = HtmlRenderer.builder().build();
    @Autowired
    private KnoteProperties properties;

    public void getAllNotes(Model model) {
        List<Note> notes = noteRepository.findAll();
        Collections.reverse(notes);
        model.addAttribute("notes", notes);
    }

    public void saveNote(String description, Model model) {
        if (description != null && !description.trim().isEmpty()) {
            //You need to translate markup to HTML
            Node document = parser.parse(description.trim());
            String html = renderer.render(document);
            noteRepository.save(new Note(null, html));
            //After publish you need to clean up the textarea
            model.addAttribute("description", "");
        }
    }

    public void uploadImage(MultipartFile file, String description, Model model) throws Exception {
        File uploadsDir = new File(properties.getUploadDir());
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir();
        }
        String fileId = UUID.randomUUID().toString() + "."
                + file.getOriginalFilename().split("\\.")[1];
        file.transferTo(new File(properties.getUploadDir() + fileId));
        model.addAttribute("description", description + " ![](/uploads/" + fileId + ")");
    }
}
