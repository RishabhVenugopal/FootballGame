package com.example.football_game;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.football_game.Entities.User;
import com.example.football_game.Entities.UserPlayer;
import com.example.football_game.Entities.UserPlayerId;
import com.example.football_game.Entities.Game;
import com.example.football_game.Entities.GamePlayer;
import com.example.football_game.Entities.GamePlayerId;
import com.example.football_game.Entities.Player;
import com.example.football_game.Repositories.UserPlayerRepository;
import com.example.football_game.Repositories.UserRepository;
import com.example.football_game.Repositories.GamePlayerRepository;
import com.example.football_game.Repositories.GameRepository;
import com.example.football_game.Repositories.PlayerRepository;

import java.util.Optional;
import java.util.List;

@Controller
public class TrumpsController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPlayerRepository userPlayerRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @GetMapping(path = "/login")
    public String login(Model model){
        model.addAttribute("wrongflag", false);
        return "login";
    }
    @PostMapping(path = "/login")
    public String login(@RequestParam String UserName, @RequestParam String password, HttpSession session, Model model){
        Optional<User> user = userRepository.findByUserName(UserName);
        if(user.isPresent() && user.get().getPassword().equals(password)){
            session.setAttribute("user", user.get());
            return "redirect:/";
        }
        model.addAttribute("wrongflag", true);
        return "login";
    }
    @GetMapping(path = "/register")
    public String register(Model model){
        model.addAttribute("takenflag", false);
        return "register";
    }
    @PostMapping(path = "/register")
    public String register(@RequestParam String UserName, @RequestParam String password, HttpSession session, Model model){
        Optional<User> user = userRepository.findByUserName(UserName);
        if (user.isEmpty()) {
            User newUser = new User(UserName, password, 500);
            userRepository.save(newUser);
            session.setAttribute("user", newUser);
            return "redirect:/";
        }
        model.addAttribute("takenflag", true);
        return "register";
    }
    @GetMapping(path = "/")
    public String home(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        model.addAttribute("players", playerRepository.findAllById(userPlayerRepository.findPlayerIdsByUserId(user.getId())));
        model.addAttribute("user", user);
        return "home";
    }
    @PostMapping(path = "/")
    public String home(@RequestParam String action,
    @RequestParam(value = "boughtId", required = false) Integer boughtId,
    @RequestParam(value = "soldId", required = false) Integer soldId,
    HttpSession session, Model model){
        
        User user = (User)session.getAttribute("user");
        if ("bought".equals(action)){
            Player bought = playerRepository.findById(boughtId).get();
            UserPlayerId userPlayerId = new UserPlayerId(user, bought);
            if (user.getCash() < bought.getValue()){
                model.addAttribute("brokeFlag", true);
                model.addAttribute("ownedFlag", false);
            }
            else if (userPlayerRepository.findById(userPlayerId).isPresent()) {
                model.addAttribute("brokeFlag", false);
                model.addAttribute("ownedFlag", true);
            }
            else{
                UserPlayer userPlayer = new UserPlayer(userPlayerId);
                userPlayerRepository.save(userPlayer);
                user.setCash(user.getCash()-bought.getValue());
                userRepository.save(user);
                model.addAttribute("brokeFlag", false);
                model.addAttribute("ownedFlag", false);
            }
        }
        if(action.equals("sold")){
            Player sold = playerRepository.findById(soldId).get();
            user.setCash(user.getCash() + sold.getValue());
            userRepository.save(user);
            UserPlayerId userPlayerId = new UserPlayerId(user, sold);
            userPlayerRepository.delete(userPlayerRepository.findById(userPlayerId).get());
            model.addAttribute("brokeFlag", false);
            model.addAttribute("ownedFlag", false);
        }
        model.addAttribute("players", playerRepository.findAllById(userPlayerRepository.findPlayerIdsByUserId(user.getId())));
        model.addAttribute("user", user);
        return "home";
    }
    @GetMapping(path = "/search")
    public String search(Model model, HttpSession session){
        model.addAttribute("tableFlag", false);
        model.addAttribute("user", (User)session.getAttribute("user"));
        return "search";
    }

    @PostMapping(path = "/search")
    public String search(@RequestParam String searchQuery, Model model, HttpSession session){
        model.addAttribute("players", playerRepository.findByLongNameLike(searchQuery));
        model.addAttribute("user", (User)session.getAttribute("user"));
        model.addAttribute("tableFlag", true);
        return "search";
    }

    @RequestMapping(path = "/{id}")
    public String playerPage(@PathVariable("id") int id, Model model, HttpSession session){
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()){
            model.addAttribute("player", player.get());
            model.addAttribute("user", (User)session.getAttribute("user"));
            return "playerpage";
        }
        else{
            return "error";
        }
    }
    
    @GetMapping(path = "/game")
    public String game(Model model, HttpSession session){
        Game game = new Game(((User)session.getAttribute("user")).getId());
        if (session.getAttribute("activeGame") == null){
            gameRepository.save(game);
            session.setAttribute("activeGame", game);
            session.setAttribute("playerTurn", true);
        }
        model.addAttribute("players", playerRepository.findAllById(userPlayerRepository.findPlayerIdsByUserId(((User)session.getAttribute("user")).getId())));
        model.addAttribute("playerFlag", false);
        model.addAttribute("game", game);
        model.addAttribute("disableAttack", false);
        model.addAttribute("disableMidfield", false);
        model.addAttribute("disableDefence", false);
        model.addAttribute("playerTurn", true);

        return "game";
    }

    @PostMapping(path = "/game")
    public String game(Model model, HttpSession session, @RequestParam(required = false) String position, @RequestParam(required = false) Integer playerId, @RequestParam(required = false) String attackStat, @RequestParam(required = false) String midfieldStat, @RequestParam(required = false) String defenceStat){
        Game game = (Game)session.getAttribute("activeGame");
        List<GamePlayer> gamePlayers = gamePlayerRepository.findByIdGame(game);
        boolean foundFlag = false;
        if ((boolean)session.getAttribute("playerTurn")){
            if (playerId != null && position != null){
                Player player = playerRepository.findById(playerId).get();
                for (GamePlayer gamePlayer : gamePlayers) {
                    if (gamePlayer.getId().getPlayer().equals(player)){
                        foundFlag = true;
                    }
                }
                if (!foundFlag && game.getAttackStat() != null && game.getDefenceStat() != null && game.getMidfieldStat() != null){
                    GamePlayerId gamePlayerId = new GamePlayerId(game, player);
                    GamePlayer gamePlayer = new GamePlayer(gamePlayerId, position, true);
                    gamePlayerRepository.save(gamePlayer);
                    if (position.equals("attack")){
                        game.setUserScore(game.getUserScore() + player.getUsedStat(game.getAttackStat()));
                    }
                    else if (position.equals("midfield")){
                        game.setUserScore(game.getUserScore() + player.getUsedStat(game.getMidfieldStat()));
                    }
                    else if (position.equals("defence")){
                        game.setUserScore(game.getUserScore() + player.getUsedStat(game.getDefenceStat()));
                    }
                    session.setAttribute("playerTurn", false);
                }
            }
            if (game.getAttackStat() == null){
                model.addAttribute("disableAttack", false);
                if (attackStat != null){
                    game.setAttackStat(attackStat);
                    model.addAttribute("disableAttack", true);
                }
            }
            else{
                model.addAttribute("disableAttack", true);
            }

            if (game.getMidfieldStat() == null){
                model.addAttribute("disableMidfield", false);
                if (midfieldStat != null){
                    game.setMidfieldStat(midfieldStat);
                    model.addAttribute("disableMidfield", true);
                }
            }
            else{
                model.addAttribute("disableMidfield", true);
            }
            if (game.getDefenceStat() == null){
                model.addAttribute("disableDefence", false);
                if (defenceStat != null){
                    game.setDefenceStat(defenceStat);
                    model.addAttribute("disableDefence", true);
                }
            }
            else{
                model.addAttribute("disableDefence", true);
            }
        }
        else{
            boolean repeatFlag = false;
            do{
                Player player = playerRepository.getRandomPlayer();
                for (GamePlayer gamePlayer : gamePlayers) {
                    if (gamePlayer.getId().getPlayer().equals(player)){
                        repeatFlag = true;
                    }
                }
                if (!repeatFlag){
                    GamePlayerId gamePlayerId = new GamePlayerId(game, player);
                    GamePlayer gamePlayer = new GamePlayer();
                    gamePlayer.setId(gamePlayerId);
                    gamePlayer.setUserFlag(false);
                    if (player.getBestStat().equals("Pace") || player.getBestStat().equals("Shooting")){
                        gamePlayer.setPosition("attack");
                    }
                    if (player.getBestStat().equals("Passing") || player.getBestStat().equals("Dribbling")){
                        gamePlayer.setPosition("midfield");
                    }
                    if (player.getBestStat().equals("Physical") || player.getBestStat().equals("Defending")){
                        gamePlayer.setPosition("defence");
                    }
                    game.setCpuScore(game.getCpuScore() + player.getBestStatValue());
                    gamePlayerRepository.save(gamePlayer);
                }
            }while(repeatFlag);
            session.setAttribute("playerTurn", true);
        }
        gamePlayers = gamePlayerRepository.findByIdGame(game);
        model.addAttribute("game", game);
        if (gamePlayers.size() >= 22){
            game.setStatus("completed");
            gameRepository.save(game);
            User user = (User)session.getAttribute("user");
            if (game.getUserScore() > game.getCpuScore()){
                user.setCash(user.getCash() + 20);
                userRepository.save(user);
                return "win";
            }
            if (game.getUserScore() < game.getCpuScore()){
                if (user.getCash() >= 10){
                    user.setCash(user.getCash() - 10);
                }
                else{
                    user.setCash(0);
                }
                userRepository.save(user);
                return "loss";
            }
            else{
                return "draw";

            }
        }
        gameRepository.save(game);
        model.addAttribute("playerFlag", true);
        model.addAttribute("players", playerRepository.findAllById(userPlayerRepository.findPlayerIdsByUserId(((User)session.getAttribute("user")).getId())));
        model.addAttribute("gamePlayers", gamePlayers);
        model.addAttribute("foundFlag", foundFlag);
        model.addAttribute("playerTurn", (boolean)session.getAttribute("playerTurn"));
        return "game";
    }
}