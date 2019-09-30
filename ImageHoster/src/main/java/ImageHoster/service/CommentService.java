package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment getCommentByName(String title) {
        return commentRepository.findComment(title);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.createComment(comment);
    }


}
