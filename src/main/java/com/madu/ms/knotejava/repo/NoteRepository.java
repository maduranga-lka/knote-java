package com.madu.ms.knotejava.repo;

import com.madu.ms.knotejava.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {

}
