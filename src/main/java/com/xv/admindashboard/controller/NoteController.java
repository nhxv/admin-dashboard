package com.xv.admindashboard.controller;

import com.xv.admindashboard.model.Note;
import com.xv.admindashboard.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable(name = "id") long noteId) throws Exception {
        return this.noteRepository.findById(noteId).orElseThrow(() -> new Exception("Note not found " + noteId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return this.noteRepository.save(note);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("notes/{id}")
    public Note updateNote(@PathVariable(name = "id") long noteId, @Valid @RequestBody Note noteUpdate) throws Exception {
        Note note = this.noteRepository.findById(noteId).orElseThrow(() -> new Exception("Note not found " + noteId));
        note.setTitle(noteUpdate.getTitle());
        note.setContent(noteUpdate.getContent());
        return this.noteRepository.save(note);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("notes/{id}")
    public Map<String, Boolean> deleteNote(@PathVariable(name = "id") long noteId) throws Exception {
        Note note = this.noteRepository.findById(noteId).orElseThrow(() -> new Exception("Note not found " + noteId));
        this.noteRepository.delete(note);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
