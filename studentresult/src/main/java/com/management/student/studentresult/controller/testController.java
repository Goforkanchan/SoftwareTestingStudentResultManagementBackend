//package com.management.student.studentresult.controller;
//
//import com.management.student.studentresult.dao.Auth;
//import com.management.student.studentresult.dao.Role;
//import com.management.student.studentresult.dao.User;
//import com.management.student.studentresult.service.AuthService;
//import com.management.student.studentresult.service.RoleService;
//import com.management.student.studentresult.service.UserService;
//import com.management.student.studentresult.vo.UserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.text.ParseException;
//import java.util.List;
//
//@RestController
//public class testController {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private AuthService authService;
//    @Autowired
//    private RoleService roleService;
//
//    @PostMapping("/addRole")
//    public Role addRole(@RequestBody Role role){
//
//        Role newrole = new Role(role.getName(), role.getCreatedBy(), role.getModifiedBy());
//        return roleService.saveRole(newrole);
//    }
//
////    @PostMapping("/addUser")
////    public UserDetails addUser(@RequestBody UserDetails userDetails) throws ParseException {
////
////        Auth auth = new Auth(userDetails.getEmail(), userDetails.getPassword());
////        authService.saveAuth(auth);
////        Role role = roleService.getRoleById(userDetails.getRoleId());
////        User user = new User(auth, role, userDetails.getRollNumber(), userDetails.getName(), userDetails.getAddress(), userDetails.getPhone(), userDetails.getDob());
////        userService.saveUser(user);
////
////        return userDetails;
////    }
//
//    @GetMapping("/allRoles")
//    public List<Role> allRoles(){
//        return roleService.getRoles(); 
//    }
//
//}
