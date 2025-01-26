package fi.haagahelia.bookstore.web;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.bookstore.domain.Book;

@Controller
public class BookController {
	@RequestMapping(value = "/index", method = RequestMethod.GET, params = { "name", "age" })
	public String sayHello(@RequestParam("name") String name, @RequestParam("age") int age, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "hello";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String addFriendNew(Model model) {
		model.addAttribute("book", new Book());
		return "book";
	}

	// @RequestMapping(value = "/index", method = RequestMethod.POST)
	// public String friendListNew(Friend friend, Model model) {
	// 	friendList.add(friend);
	// 	model.addAttribute("friendList", friendList);
	// 	return "friend";
	// }
}
