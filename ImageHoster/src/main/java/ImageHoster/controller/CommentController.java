package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @Autowired
    ImageController imageController;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle,
                             @RequestParam("comment") String comment, HttpSession session, Model model) {

        System.out.println(comment);
        Comment commentObj = new Comment();

        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImageByIdAndTitle(imageId, imageTitle);
        commentObj.setUser(user);
        commentObj.setImage(image);
        commentObj.setText(comment);
        commentObj.setCreatedDate(LocalDate.now());

        commentService.createComment(commentObj);

        return imageController.showImage(imageId, imageTitle, model);
    }
}
