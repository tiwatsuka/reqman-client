package org.macchinetta.reqman.app.chart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chart")
public class ChartController {
	
	@RequestMapping
	public String showChart(){
		return "chart/chart";
	}
}
