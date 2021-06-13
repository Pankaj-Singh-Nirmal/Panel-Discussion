package pl.panel.discussion.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.panel.discussion.model.ForumPostCommentsDTO;
import pl.panel.discussion.model.ForumPostDTO;
import pl.panel.discussion.model.UserDTO;
import pl.panel.discussion.service.ForumPostCommentsService;
import pl.panel.discussion.service.ForumPostService;
import pl.panel.discussion.service.UserService;

@Controller
@SessionAttributes("sessionUser")
@RequestMapping("user")
public class UserController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	ForumPostService forumPostService;
	
	@Autowired
	ForumPostCommentsService forumPostCommentsService;
	
	String postTitleId = "";
	
	@GetMapping("/forumPostList")
	public String loadForumPostListPage(ForumPostDTO forumPostDTO, Model model, 
										HttpSession session, UserDTO userDTO)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userId = auth.getName();
		
		List<UserDTO> user = userService.selectUser(userId);
		
		for(UserDTO u : user) 
		{
			userDTO.setUserId(userId);
			userDTO.setFirstName(u.getFirstName());
			userDTO.setLastName(u.getLastName());
		}
		
		session.setAttribute("sessionUser", userDTO);
		
		userDTO = (UserDTO) session.getAttribute("sessionUser");
		
		List<ForumPostDTO> forumPostList = forumPostService.getForumPostList();
		model.addAttribute("postList", forumPostList);
		
		return "viewForumPostList-page";
	}
	
	@GetMapping("/createForumPost")
	public String loadCreateForumPostPage(@ModelAttribute("newPost") ForumPostDTO forumPostDTO)
	{
		return "createForumPost-page";
	}
	
	@PostMapping("/processCreateForumPost")
	public String processCreateForumPostPage(@Valid @ModelAttribute("newPost") ForumPostDTO forumPostDTO,
											 BindingResult result, RedirectAttributes redirectAttributes,
											 HttpSession session)
	{
		if(result.hasErrors())
		{
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError err : allErrors) 
			{
				System.out.println(err);
			}
			
			return "createForumPost-page";
		}
		
		UserDTO userDTO = (UserDTO) session.getAttribute("sessionUser");
		
		forumPostDTO.setContent(forumPostDTO.getContent().replace("\n", "<br>"));
		forumPostDTO.setCreatorId(userDTO.getUserId());
		forumPostDTO.setCreatorName(userDTO.getFirstName()+" "+userDTO.getLastName());
		
		forumPostService.createForumPost(forumPostDTO);
		
		redirectAttributes.addFlashAttribute("postSuccess", "Post Success !");
		
		return "redirect:/user/forumPostList";
	}
	
	@GetMapping("/selectForumPost/{titleId}")
	public String loadSelectForumPostPage(@ModelAttribute("postComments") ForumPostCommentsDTO forumPostCommentsDTO,
										  @PathVariable("titleId") String titleId, Model model) 
	{
		postTitleId = titleId;
		forumPostCommentsService.forumPostAddAttributes(postTitleId, model);
		
		return "forumPostComments-page";
	}
	
	@PostMapping("/submitPostComment")
	public String submitPostCommentPage(@Valid @ModelAttribute("postComments") ForumPostCommentsDTO forumPostCommentsDTO,
									   BindingResult result, Model model, RedirectAttributes redirectAttributes,
									   HttpSession session)
	{
		if(result.hasErrors())
		{
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError err : allErrors) 
			{
				System.out.println(err);
			}
			
			forumPostCommentsService.forumPostAddAttributes(postTitleId, model);
			
			return "forumPostComments-page";
		}
		
		UserDTO userDTO = (UserDTO) session.getAttribute("sessionUser");
		
		forumPostCommentsDTO.setComment(forumPostCommentsDTO.getComment().replace("\n", "<br>"));
		forumPostCommentsDTO.setCreatorId(userDTO.getUserId());
		forumPostCommentsDTO.setCreatorName(userDTO.getFirstName()+" "+userDTO.getLastName());
		
		forumPostCommentsService.insertForumPostComment(forumPostCommentsDTO);
		
		String commentCount = forumPostCommentsService.getForumPostCommentCount(postTitleId);
		forumPostService.updateForumPostComment(commentCount, postTitleId);
		
		redirectAttributes.addFlashAttribute("forumPostDetails", forumPostCommentsDTO);
		
		return "redirect:/user/forumPostComments";
	}
	
	@GetMapping("/forumPostComments")
	public String loadforumPostCommentsPage(@ModelAttribute("postComments") ForumPostCommentsDTO forumPostCommentsDTO,
										   Model model)
	{
		forumPostCommentsService.forumPostAddAttributes(postTitleId, model);
		
		return "forumPostComments-page";
	}
}
