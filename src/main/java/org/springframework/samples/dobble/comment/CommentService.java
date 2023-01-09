package org.springframework.samples.dobble.comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommentService {

	@Autowired
	private CommentRepository organoRepo;
		

	
	@Transactional
	public void initializeComments() throws Exception {
	
	}
	
	@Transactional(readOnly = true)
	public Optional<Comment> findById(Long idOrgano) {
		return organoRepo.findById(idOrgano);
	}

	@Transactional(readOnly = true)
	public Iterable<Comment> findAll() {
		return organoRepo.findAll();
	}

	@Transactional
	public void save(Comment organo) {		
		organoRepo.save(organo);
	}
	
	@Transactional
	public void delete(long id){
		organoRepo.deleteById(id);
	}

}
