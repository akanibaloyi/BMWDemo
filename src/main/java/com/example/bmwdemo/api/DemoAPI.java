package com.example.bmwdemo.api;

import com.example.bmwdemo.dao.CarDAO;
import com.example.bmwdemo.dto.AuthDTO;
import com.example.bmwdemo.dto.CarDTO;
import com.example.bmwdemo.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class DemoAPI {

    @Autowired
    CarDAO carDAO;
    @Autowired
    private AuthenticationManager authenticationManage;
    @Autowired
    JWTUtil jwtUtil;

    @RequestMapping(path = "ping", method = RequestMethod.GET)
    public String ping(){
        return "Ping!";
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public boolean createCar(CarDTO dto){
        return carDAO.createCar(dto);
    }

    @RequestMapping(path = "update",method = RequestMethod.PUT)
    public boolean updateCar(CarDTO dto){
        return carDAO.updateCar(dto);
    }

    @RequestMapping(path = "remove", method = RequestMethod.DELETE)
    public boolean removeCar(int id){
        return carDAO.removeCar(id);
    }

    @RequestMapping(path = "retrieve", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarDTO> retriveCars(){return carDAO.retrieveAllCars();}

    @RequestMapping(path = "auth", method = RequestMethod.POST)
    public String auth(AuthDTO authDTO) throws Exception {
        Authentication authenticate = authenticationManage.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtUtil.generateToken(authDTO.getUsername());
        }
        else{
            throw new Exception("Unauthorized");
        }

    }

}
