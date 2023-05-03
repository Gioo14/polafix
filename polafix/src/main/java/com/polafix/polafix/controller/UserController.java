package com.polafix.polafix.controller;

import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.polafix.polafix.pojos.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SerieService serieService;

//----------------------------------------------USER-------------------------------------------------------------

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserById(@PathVariable String email) {
        return userService.getUserById(email);
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User user) {
        return userService.updateUser(email, user);
    }

    @DeleteMapping("/{email}")
    public boolean deleteUser(@PathVariable String email) {
        return userService.deleteUser(email);
    }

 //----------------------------------------------SERIE USER-------------------------------------------------------------

    @GetMapping("/{email}/started")
    public List<SerieUser> getUserStarted(@PathVariable String email) {
        User existingUser = userService.getUserById(email);
        if (existingUser != null) {
            return existingUser.getStarted();
        } else {
            return null;
        }
    }

    @GetMapping("/{email}/started/{title}")
    public SerieUser getSerieUserStarted(@PathVariable String email, @PathVariable List<SerieUser> list, @PathVariable String title) {
        User existingUser = userService.getUserById(email);
        if (existingUser != null) {
            for (SerieUser s : existingUser.getStarted()) {
                if(s.getTitle().equals(title))
                    return s;
            }
        }
        return null;
    }

    @GetMapping("/{email}/ended")
    public List<SerieUser> getUserEnded(@PathVariable String email) {
        User existingUser = userService.getUserById(email);
        if (existingUser != null) {
            return existingUser.getStarted();
        } else {
            return null;
        }
    }

    @GetMapping("/{email}/ended/{title}")
    public SerieUser getSerieUserEnded(@PathVariable String email, @PathVariable String title) {
        User existingUser = userService.getUserById(email);
        if (existingUser != null) {
            for (SerieUser s : existingUser.getEnded()) {
                if(s.getTitle().equals(title))
                    return s;
            }
        }
        return null;
    }

    @GetMapping("/{email}/inlist")
    public List<SerieUser> getUserInlist(@PathVariable String email) {
        User existingUser = userService.getUserById(email);
        if (existingUser != null) {
            return existingUser.getInlist();
        } else {
            return null;
        }
    }

    @GetMapping("/{email}/inlist/{title}")
    public SerieUser getSerieUserInlist(@PathVariable String email, @PathVariable String title) {
        User existingUser = userService.getUserById(email);
        if (existingUser != null) {
            for (SerieUser s : existingUser.getInlist()) {
                if(s.getTitle().equals(title))
                    return s;
            }
        }
        return null;
    }

//-----------------------------------------------BALANCES-------------------------------------------------------
    @GetMapping("/{email}/balances")
    public Balance getBalances(@PathVariable String email) {
        User existingUser = userService.getUserById(email);
        if (existingUser != null) {
            return existingUser.getLastBalance(existingUser.getBalances());
        } else {
            return null;
        }
    }

    @GetMapping("/{email}/balances/{year}/{month}")
    public Balance getBalances(@PathVariable String email, @PathVariable int year, @PathVariable Month month) {
        User existingUser = userService.getUserById(email);
        if (existingUser != null) {
            return existingUser.getHistoryBalance(month, year);
        } else {
            return null;
        }
    }

//----------------------------------------------AGREGAR SERIE----------------------------------------------------

@PutMapping("/{email}/inlist")
public User agregarSerie(@PathVariable String email, @RequestParam Long id){
    Serie serie = serieService.getSerieById(id);
    User existingUser = userService.getUserById(email);
        if (existingUser != null) {
            existingUser.addSerie(serie);
            return userService.saveUser(existingUser);
        } else {
            return null;
    }
}

}

