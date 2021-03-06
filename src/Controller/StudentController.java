package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 启用@Contoller注解 在springmvc配置文件中添加标签<context:component-scan>
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/stu")
public class StudentController {

	// 如果能访问second.jsp则启动注解成功
	@RequestMapping("/second.do")
	public ModelAndView useAnaotation() {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "启动注解成功");
		mView.setViewName("second");
		return mView;
	}

	// @requestMapping的value属性，可以映射多个url
	@RequestMapping(value = { "/second1.do", "/second2.do", "/second3.do" })
	public ModelAndView useAnaotation1() {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "多个请求映射一个方法");
		mView.setViewName("second");
		return mView;
	}

	/**
	 * @requestMappingd的第二个属性method, method = RequestMethod.GET 查 method =
	 * RequestMethod.POST 增 method = RequestMethod.PUT 改 method =
	 * RequestMethod.DELETE 删 一种rest风格，浏览器过来的请求只能get-get，post-post...
	 * 
	 * @return
	 */
	@RequestMapping(value = "/second4.do", method = RequestMethod.GET)
	public ModelAndView useRequestMapping() {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "rest风格");
		mView.setViewName("second");
		return mView;
	}

	// 只能支持post请求
	@RequestMapping(value = "/second41.do", method = RequestMethod.POST)
	public ModelAndView useRequestMapping2() {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "rest风格");
		mView.setViewName("second");
		return mView;
	}

	@GetMapping("/second5.do")
	public ModelAndView useRequestMapping1() {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "getmapping注解");
		mView.setViewName("second");
		return mView;
	}

	// 方法返回String
	@GetMapping("/second6.do")
	public String useString(HttpServletRequest request) {
		request.setAttribute("msg", "方法返回string类型");
		// 配置了视图解析器，所以返回的字符串会匹配视图
		return "second";
	}

	// 方法返回String，重定向
	@GetMapping("/second7.do")
	public String useString1(HttpServletRequest request,HttpServletResponse respo) {
		//respo.sendRedirect("second6.do");
		request.setAttribute("name", "shaokui");
		//second页面应该接收不到这个属性的，因为重定向
		//是将响应给浏览器浏览器重新发的请求，已经不是这个请求了
		return "redirect:second6.do";
	}
	// 转发
	@RequestMapping("/second8.do")
	public void forwardString(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("name", "shaokui");
		//转发：所以服务器直接将请求发出去的，所以还是原来的请求
		//整个过程请求只有一个，所以second页面能接受这个属性
		request.getRequestDispatcher("second6.do").forward(request, response);
	}
	@RequestMapping("/second9.do")
	public String forwardString1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("name", "张三");
		//转发：所以服务器直接将请求发出去的，所以还是原来的请求
		//整个过程请求只有一个，所以second页面能接受这个属性
		return "forward:/first.do";
		//request.getRequestDispatcher("second6.do").forward(request, response);
	}
	
	

}
