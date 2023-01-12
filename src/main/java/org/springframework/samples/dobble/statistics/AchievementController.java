package org.springframework.samples.dobble.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameService;
import org.springframework.samples.dobble.game.GameUserService;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statistics/achievements")
public class AchievementController {

    private final String PERSONAL_LISTING_VIEW="/achievements/PersonalAchievementsListing";
    private final String ACHIEVEMENTS_LISTING_VIEW="/achievements/AchievementsListing";
    private final String ACHIEVEMENTS_FORM="/achievements/createOrUpdateAchievementForm";
    private final String OWNER_ACHIEVEMENTS_FORM="/achievements/createOrUpdateAchievementsOfUserForm";

    private AchievementService service;
    private UserService userService;
    private GameUserService gameUserService;
    private GameService gameService;

    @Autowired
    public AchievementController(AchievementService service, UserService userService, GameUserService gameUserService, GameService gameService){
        this.service=service;
        this.userService=userService;
        this.gameUserService=gameUserService;
        this.gameService=gameService;
    }

    @Transactional(readOnly = true)
    @GetMapping()
    public ModelAndView showAchievements(){
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_LISTING_VIEW);
        result.addObject("achievements", service.getAchievements());
        return result;
    }

    @Transactional()
    @GetMapping("/{id}/delete")
    public ModelAndView deleteAchievement(@PathVariable int id){
        service.deleteAchievementById(id);        
        return showAchievements();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}/edit")
    public ModelAndView editAchievement(@PathVariable int id){
        Achievement achievement=service.getById(id);        
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_FORM);
        result.addObject("achievement", achievement);         
        result.addObject("metrics",Arrays.asList(Metric.values()));        
        return result;
    }
 
    @Transactional
    @PostMapping("/{id}/edit")
    public ModelAndView saveAchievement(@PathVariable int id,@Valid Achievement achievement, BindingResult br){
        ModelAndView result=null;
        if(br.hasErrors()){
            result=new ModelAndView(ACHIEVEMENTS_FORM,br.getModel());            
            result.addObject("metrics",Arrays.asList(Metric.values()));        
            return result;
        }
        Achievement achievementToBeUpdated=service.getById(id);
        BeanUtils.copyProperties(achievement,achievementToBeUpdated,"id");
        service.save(achievementToBeUpdated);
        result=showAchievements();
        result.addObject("message", "The achievement was updated successfully");
        return result;        
    }

    @Transactional(readOnly = true)
    @GetMapping("/new")
    public ModelAndView createAchievement(){
        Achievement achievement=new Achievement();
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_FORM);        
        result.addObject("achievement",achievement);
        result.addObject("metrics",Arrays.asList(Metric.values()));        
        return result;
    }

    @Transactional
    @PostMapping("/new")
    public ModelAndView saveNewAchievement(@Valid Achievement achievement, BindingResult br){
        ModelAndView result=null;
        if(br.hasErrors()){
            result=new ModelAndView(ACHIEVEMENTS_FORM,br.getModel());            
            result.addObject("metrics",Arrays.asList(Metric.values()));        
            return result;
        }
        service.save(achievement);
        result=showAchievements();
        result.addObject("message", "The achievement was created successfully");
        return result;
    }

    @GetMapping("/byUser/{id}")
    public ModelAndView showPersonalAchievementsListing(@PathVariable String id){
        ModelAndView result=new ModelAndView(PERSONAL_LISTING_VIEW);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        Integer score = gameUserService.findTotalScoreByUsername(userId);
        if(score ==null) score = 0;
        Set<Achievement> achievements = new HashSet<Achievement>();
        for(Achievement a : service.getAchievements()){
            if(score>a.getThreshold()){
                User user = userService.findUser(userId);
                user.getAchievements();
                achievements.add(a);
                user.setAchievements(achievements);
                userService.saveUser(user);
            }
        }
        Integer victories = 0;
        for(Game g:gameService.findAllGames()){
            if(g.getWinner()!=null && g.getWinner().getUsername().compareTo(userId)==0){
                victories = victories + 1;
            }
        }
        result.addObject("achievements",service.getAchievementsByOwner(id));
        result.addObject("score", score);
        result.addObject("victories", victories);

        return result;
    }

    @GetMapping("/byUser/{id}/edit")
    public ModelAndView showUserAchievementsEditForm(@PathVariable("id")String userId){
        ModelAndView result=new ModelAndView(OWNER_ACHIEVEMENTS_FORM);
        User user=userService.findUser(userId);
        result.addObject("user", user);
        result.addObject("availableAchievements",service.getAchievements());
        return result;
    }

    @PostMapping("/byUser/{id}/edit")
    public ModelAndView updateUserAchievements(User user,BindingResult brresult,@PathVariable("id")String userId){        
        User userToBeUpdated=userService.findUser(userId);
        userToBeUpdated.getAchievements().clear();
        userService.saveUser(userToBeUpdated);
        return showPersonalAchievementsListing(userId);
    }

    @GetMapping("/me")
    public ModelAndView showCurrentUserAchievements(@AuthenticationPrincipal User user){
        ModelAndView result=null;
        if(user!=null){
            result=showPersonalAchievementsListing(user.getUsername());
        }else{
            result=new ModelAndView("welcome");
            result.addObject("message","You are not an User, thus you don't have achievements");
            result.addObject("messageType","warning");            
        }
        return result;
    }
    
}
