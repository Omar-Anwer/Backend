package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper ) {
        this.noteMapper = noteMapper;
    }

    public Integer createNote(Note note){
        return noteMapper.insert(note);
    }

    public void updateNote(Note note){
        noteMapper.update(note);
    }

    public void deleteNote(Integer noteId){
        noteMapper.delete(noteId);
    }

    public Note getNote(Integer noteId){
        return noteMapper.findById(noteId);
    }

    public List<Note> getAllNotes(){
        return noteMapper.findAll();
    }
    
    public List<Note> getAllUserNotes(Integer userId){
        return noteMapper.findAllUserNotes(userId);
    }
}
