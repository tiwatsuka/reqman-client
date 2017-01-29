package org.macchinetta.reqman.app.request;

import org.macchinetta.reqman.domain.request.RequestService;
import org.macchinetta.reqman.domain.request.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/requests")
public class RequestController {

	@Autowired
	RequestService requestService;

	@Autowired
	VoteService voteService;

	@RequestMapping
	public String showList(Model model){
		model.addAttribute("requests", requestService.getAllRequests());
		return "request/list";
	}

	@RequestMapping("{id}")
	public String detail(@PathVariable long id, Model model){
		model.addAttribute("request", requestService.getRequest(id));
		model.addAttribute("votes", voteService.getVotesByRequestId(id));
		return "request/detail";
	}
}
