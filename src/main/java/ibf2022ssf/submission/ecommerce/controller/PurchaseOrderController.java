package ibf2022ssf.submission.ecommerce.controller;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;


// import java.util.LinkedList;
// import java.util.List;

// import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
// import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022ssf.submission.ecommerce.model.Details;
import ibf2022ssf.submission.ecommerce.model.ItemOrder;
import ibf2022ssf.submission.ecommerce.model.Order;
import ibf2022ssf.submission.ecommerce.service.OrderService;
// import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path={"","/"})
public class PurchaseOrderController {
    
    @Autowired
    private OrderService oSvc;

    Order saveOrder = new Order();
   
    Double price = 0.0;
    Double totalCost;

    @GetMapping
    public String showLanding(Model model){
        Order o = new Order();
        model.addAttribute("order", o);
        return "index";
    }

    @GetMapping("/additem")
    public String addItem(Model model) {
        Order ohh = new Order();
        model.addAttribute("order", ohh);
        System.out.println(ohh);
        return "index";
    }

    @GetMapping(path="/shippingaddress")
    public String postSelection(@Valid Order order, BindingResult bResult, Model model){
        if(bResult.hasErrors()){
            return "index";
        }
        saveOrder = order;
        Details d = new Details();
        model.addAttribute("details", d);
        return "details";
    }

    @PostMapping(path="/item/order")
    public String postOrder(@Valid Details details, BindingResult bResult, Model model){
        if(bResult.hasErrors()){
            return "details";
            
        }
        // calculate total cost
        this.calculateCost(model, saveOrder.getItem(), saveOrder.getQuantity());

        ItemOrder io = new ItemOrder();

        io.setInvoiceId(generateID(8));
        io.setName(details.getName());
        io.setAddress(details.getAddress());
        io.setTotal(totalCost);
        

        oSvc.save(io);
        model.addAttribute("io", io);
        return "invoice";
    }

    public synchronized String generateID(int numOfChar){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < numOfChar){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0,numOfChar);
    }

    public void calculateCost(Model model, String item, Integer quantity){

        if (item.equalsIgnoreCase("apple") || item.equalsIgnoreCase("orange")){
            price = 0.5;
        } else if (item.equalsIgnoreCase("bread")|| item.equalsIgnoreCase("instant_noodles")){
            price = 2.50;
        } else if(item.equalsIgnoreCase("cheese")|| item.equalsIgnoreCase("mineralwater")){
            price = 1.50;
        } else if(item.equalsIgnoreCase("chicken")){
            price = 5.50;
        }
              //add quantity
        
        totalCost = (double)(price *quantity);
        
        System.out.println(totalCost);
    }
    // @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	// public String postMapping(@RequestBody MultiValueMap<String, String> form,
	// 		Model model, HttpSession session) {

	// 	String item = form.getFirst("item");
	// 	String quantity = form.getFirst("quantity");

	// 	List<String> order = (List<String>)session.getAttribute("order");
	// 	if (null == order) {
	// 		// If cart is null, then new session
	// 		order = new LinkedList<>();
	// 		session.setAttribute("order", order);
			
	// 	}

	// 	order.add(item);
    //     order.add(quantity);

	// 	model.addAttribute("order", order);
	// 	model.addAttribute("item", item);
    //     model.addAttribute("quantity", quantity);

	// 	return "index";
	// }
}
