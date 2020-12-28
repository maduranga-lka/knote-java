package com.madu.ms.knotejava.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface KNoteService {
    void getAllNotes(Model model);

    void saveNote(String description, Model model);

    void uploadImage(MultipartFile file, String description, Model model) throws Exception;
}
