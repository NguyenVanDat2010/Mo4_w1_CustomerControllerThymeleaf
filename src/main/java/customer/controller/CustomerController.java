package customer.controller;

import customer.model.Customer;
import customer.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/")
    public String listCustomer(Model model){
        model.addAttribute("customers",customerService.findAll());
        return "customer/list";
    }

    /**    Tạo mới      */
    @GetMapping("customer/create")
    public String createCustomer(Model model){
        model.addAttribute("customer",new Customer());
        return "customer/create";
    }

    //Sử dụng redirect để gửi dữ liệu đi 1 lần và ko save lại khi reload lại trang
    @PostMapping("customer/save")
    public String saveCustomer(Customer customer, RedirectAttributes redirectAttributes){
        customer.setId((int) (Math.random()*1000));
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("success","Saved customer successfully!");
        return "redirect:/";
    }

    /**    Sửa customer      */
    @GetMapping("customer/{id}/edit")
    public String editCustomer(@PathVariable("id") int id, Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "customer/edit";
    }

    @PostMapping("customer/update")
    public String updateCustomer(Customer customer,RedirectAttributes redirectAttributes){
        customerService.update(customer.getId(),customer);
        redirectAttributes.addFlashAttribute("success", "Modified customer successfully!");
        return "redirect:/";
    }

    @GetMapping("customer/{id}/delete")
    public String deleteCustomerForm(@PathVariable("id") int id,Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "customer/delete";
    }

    @PostMapping("customer/delete")
    public String deleteCustomer(Customer customer, RedirectAttributes redirectAttributes){
        customerService.remove(customer.getId());
        redirectAttributes.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/";
    }

    @GetMapping("customer/{id}/view")
    public String viewCustomerForm(@PathVariable int id, Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "customer/view";
    }

}
