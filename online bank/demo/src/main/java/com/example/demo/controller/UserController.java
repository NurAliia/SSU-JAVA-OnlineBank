package com.example.demo.controller;

import com.example.demo.model.BankAccount;
import com.example.demo.model.BankAccountOperation;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BankAccountOperationService;
import com.example.demo.service.BankAccountService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.utils.OperationTypes;
import com.example.demo.utils.SpringRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;



/**
 * Created by NurAliya on 29.04.2018.*/



@Controller
@RequiredArgsConstructor
public class UserController {

    private final String loginPage = "login-form";
    private final String registrationPage = "registration";
    private final String redirectToLoginPage = "redirect:login";
    private final String successLoginPage = "success-login";
    private final String addBankAccountPage = "addBankAccount";
    private final String redirectToSucessLoginPage = "redirect:success-login";
    private final String changePersonalDataPage = "changePersonalData";
    private final String credirectToChangePersonalDataPage = "redirect:changepersonaldata";
    private final String deleteBankAccountPage = "deleteBankAccount";
    private final String moneyOperationPage = "moneyOperation";
    private final String rollbackTransactionPage = "rollbackTransaction";
    private final String redirectToRollbackTransactionPage = "redirect:rollbacktransaction";
    private final RoleRepository roles;

    private Long id;

    @Autowired
    private UserService userService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountOperationService bankAccountOperationService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView loginForm() {
        return new ModelAndView(loginPage);
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.POST)
    public ModelAndView loginFormPost(HttpServletRequest request) {
        String name = request.getParameter("j_username");
        User user = userService.getUserByUsername(name);
        if ( user != null) {
            id = user.getId();
            //////;
        }
        else {
            return new ModelAndView(loginPage);
        }

    }


    @RequestMapping(value = "/error-login", method = RequestMethod.GET)
    public ModelAndView invalidLogin() {
        ModelAndView modelAndView = new ModelAndView(loginPage);
        modelAndView.addObject("error", true);
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView signup() {
        return new ModelAndView(registrationPage);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(HttpServletRequest request) {
        String name = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        String adr = request.getParameter("j_ard");
        String phone = request.getParameter("j_phone");

        userService.saveUser(name, password, adr, phone);
        return redirectToLoginPage;
    }

    @RequestMapping(value = "/success-login", method = RequestMethod.GET)
    public String successLogin(Model model) {
        String currentUsername = getCurrentUsername();
        model.addAttribute("username", currentUsername);
        model.addAttribute("bank_accounts", userService.getUserByUsername(currentUsername).getBankAccounts());
        model.addAttribute("isAdmin", isAdmin());
        return successLoginPage;
    }

    @RequestMapping(value = "/addbankaccount", method = RequestMethod.GET)
    public String newBankAccount(Model model) {
        String currentUsername = getCurrentUsername();
        model.addAttribute("username", currentUsername);
        model.addAttribute("isAdmin", isAdmin());
        return addBankAccountPage;
    }

    @RequestMapping(value = "/addbankaccount", method = RequestMethod.POST)
    public String addBankAccount(HttpServletRequest request) {
        Long money = Long.parseLong(request.getParameter("money"));
        Long index = bankAccountService.saveNewBankAccount(money);
        User currentUser = userService.getUserByUsername(getCurrentUsername());
        Set<BankAccount> bankAccounts = currentUser.getBankAccounts();
        bankAccounts.add(bankAccountService.getBankAccountById(index));
        currentUser.setBankAccounts(bankAccounts);
        userService.updateUser(currentUser);
        return redirectToSucessLoginPage;
    }

    @RequestMapping(value = "/changepersonaldata", method = RequestMethod.GET)
    public String changePersonalData(Model model) {
        String currentUsername = getCurrentUsername();
        model.addAttribute("username", currentUsername);
        model.addAttribute("isAdmin", isAdmin());
        User user = userService.getUserByUsername(currentUsername);
        model.addAttribute("address", user.getAddress());
        model.addAttribute("phone", user.getPhone());
        return changePersonalDataPage;
    }

    @RequestMapping(value = "/changepersonaldata", method = RequestMethod.POST)
    public String changeUserPersonalData(HttpServletRequest request) {
        String modifiedLogin = request.getParameter("new-login");
        String modifiedAddress = request.getParameter("new-address");
        String modifiedPhone = request.getParameter("new-phone");
        User currentUser = userService.getUserByUsername(getCurrentUsername());

        if (userService.getUserByUsername(modifiedLogin) == null) {
            if (!"".equals(modifiedLogin)) {
                currentUser.setName(modifiedLogin);
            }
            if (!"".equals(modifiedAddress)) {
                currentUser.setAddress(modifiedAddress);
            }
            if (!"".equals(modifiedPhone)) {
                currentUser.setPhone(modifiedPhone);
            }
            userService.updateUser(currentUser);
            return redirectToLoginPage;
        } else {
            return credirectToChangePersonalDataPage;
        }
    }

    @RequestMapping(value = "/deletebankaccount", method = RequestMethod.GET)
    public String deleteBankAccount(Model model) {
        String currentUsername = getCurrentUsername();
        model.addAttribute("username", currentUsername);
        model.addAttribute("isAdmin", isAdmin());
        model.addAttribute("bank_accounts", userService.getUserByUsername(currentUsername).getBankAccounts());
        return deleteBankAccountPage;
    }

    @RequestMapping(value = "/deletebankaccount", method = RequestMethod.POST)
    public String deleteUserBankAccount(HttpServletRequest request) {
        String[] checkboxValues = request.getParameterValues("checkbox_id");
        User user = userService.getUserByUsername(getCurrentUsername());
        Set<BankAccount> bankAccountSet = user.getBankAccounts();
        Set<BankAccount> accountsForDelete = new HashSet<>();
        for (String id : checkboxValues) {
            for (BankAccount account : bankAccountSet) {
                if (String.valueOf(account.getId()).equals(id)) {
                    accountsForDelete.add(account);
                }
            }
        }
        bankAccountSet.removeAll(accountsForDelete);
        user.setBankAccounts(bankAccountSet);
        userService.updateUser(user);
        return redirectToSucessLoginPage;
    }

    @RequestMapping(value = "/moneyoperation", method = RequestMethod.GET)
    public String moneyOperation(Model model) {
        String currentUsername = getCurrentUsername();
        model.addAttribute("bank_accounts", userService.getUserByUsername(currentUsername).getBankAccounts());
        model.addAttribute("username", currentUsername);
        model.addAttribute("isAdmin", isAdmin());
        return moneyOperationPage;
    }

    @RequestMapping(value = "/domoneyoperation", method = RequestMethod.POST)
    public String doMoneyOperation(HttpServletRequest request) {
        String[] fromBankAccountValues = request.getParameterValues("from_bank_account");
        String[] toBankAccountValues = request.getParameterValues("to_bank_account");
        Long moneyCount = Long.parseLong(request.getParameter("money_value"));

        if (!fromBankAccountValues[0].equals(toBankAccountValues[0])) {
            BankAccount from = bankAccountService.getBankAccountById(Long.parseLong(fromBankAccountValues[0]));
            BankAccount to = bankAccountService.getBankAccountById(Long.parseLong(toBankAccountValues[0]));
            if (to != null) {
                if (from.getValue() > moneyCount) {
                    moneyTransfer(from, to, moneyCount, OperationTypes.DEFAULT);
                } else {
                    return moneyOperationPage;
                }
            }
        } else {
            return moneyOperationPage;
        }
        return redirectToSucessLoginPage;
    }

    @RequestMapping(value = "/rollbacktransaction", method = RequestMethod.GET)
    public String rollbackOperation(Model model) {
        String currentUsername = getCurrentUsername();
        model.addAttribute("username", currentUsername);
        model.addAttribute("isAdmin", isAdmin());
        model.addAttribute("operations", bankAccountOperationService.getAllBankAccountOperations());
        return rollbackTransactionPage;
    }

    @RequestMapping(value = "/rollback", method = RequestMethod.POST)
    public String rollback(HttpServletRequest request) {
        String[] checkbox_id = request.getParameterValues("checkbox_id");

        for (String index : checkbox_id) {
            Long idx = Long.parseLong(index);
            BankAccountOperation operation = bankAccountOperationService.getBankAccountOperationById(idx);
            BankAccount from = operation.getSource();
            BankAccount to = bankAccountService.getBankAccountById(operation.getTarget());
            Long moneyCount = operation.getSum();
            if (to.getValue() > moneyCount) {
                moneyTransfer(to, from, moneyCount, OperationTypes.ROLLBACK);
            } else {
                return redirectToRollbackTransactionPage;
            }
        }
        return redirectToSucessLoginPage;
    }

    private void moneyTransfer(BankAccount fromAccount, BankAccount toAccount, Long moneyCount, OperationTypes marker) {
        fromAccount.setValue(fromAccount.getValue() - moneyCount);
        toAccount.setValue(toAccount.getValue() + moneyCount);
        bankAccountService.updateBankAccount(fromAccount);
        bankAccountService.updateBankAccount(toAccount);
        bankAccountOperationService.saveNewBankAccountOperation(fromAccount, toAccount, moneyCount, marker.toString());
    }

    private String getCurrentUsername() {
        return ;
    }

    private boolean isAdmin() {
        if (roles.findById())
            return true;
        else
            return false;
    }
}

