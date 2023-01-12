package org.springframework.samples.dobble.forum;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.comment.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForumService {
	@Autowired
	private ForumRepository parlamentarioRepo;
	

	@Transactional(readOnly = true)
	public Iterable<Forum> findAll() {
		return parlamentarioRepo.findAll();
	}
	
	@Transactional
	public void deleteById(long id) {
		parlamentarioRepo.deleteById(id);
	}

	@Transactional
	public void save(Forum parlamentario) {
		parlamentarioRepo.save(parlamentario);
	}

	@Transactional(readOnly = true)
	public Optional<Forum> findById(long id) {
		return parlamentarioRepo.findById(id);
	}

	@Transactional(readOnly = true)
	public Forum findByNombre(String nombre) {
		return parlamentarioRepo.findByName(nombre);
	}

	@Transactional
	public void resetComments() {
		Iterable<Forum> parlamentarios=parlamentarioRepo.findAll();
		List<Comment> commentsToBeRemoved=new ArrayList<>();
		for(Forum parlamentario:parlamentarios) {
			commentsToBeRemoved.clear();
			for(Comment organo:parlamentario.getComments())
				if(!organo.getText().startsWith("G.P."))
					commentsToBeRemoved.add(organo);
			parlamentario.getComments().removeAll(commentsToBeRemoved);
			parlamentarioRepo.save(parlamentario);
		}
		
	}
}
