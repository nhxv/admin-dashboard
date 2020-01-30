package com.xv.admindashboard.repository;

import com.xv.admindashboard.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
