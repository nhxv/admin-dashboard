package com.xv.admindashboard.controller;

import com.xv.admindashboard.model.Note;
import com.xv.admindashboard.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class NoteController {
    private NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return this.noteRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return this.noteRepository.save(note);
    }
}
