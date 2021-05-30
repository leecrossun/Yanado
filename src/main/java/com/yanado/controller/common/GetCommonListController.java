package com.yanado.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.dao.ProductDAO;
import com.yanado.dto.Common;
import com.yanado.dto.CommonDTO;
import com.yanado.dto.Criteria;
import com.yanado.dto.Product;
import com.yanado.service.CommonService;

@Controller
public class GetCommonListController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private ProductDAO productDAO;

	// 공동구매 리스트
	@RequestMapping("/common/list")
	public ModelAndView getList(@RequestParam(required = false, defaultValue="0") int page) {
		//int total = commonService.getCount();
		//Criteria c = new Criteria(page, total);
		
		List<Common> common = commonService.findAllCommon(page);
		List<CommonDTO> commonList = new ArrayList<CommonDTO>();
		
		for (Common com : common) {
			String productId = com.getProductId();
			//Product product = productDAO.getProductByProductId(productId);
			Product product = commonService.findProduct(productId);
			commonList.add(new CommonDTO(com, product));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/list");
		mav.addObject("commonList", commonList);

		return mav;
	}

}
