# FootballGwent
### https://youtu.be/-Q7ieOORKGw
## Description:
### Introduction:
Football Gwent is a football version of the card game "Gwent" from the popular video game "Witcher 3". In Gwent, each card has a fixed number of strength points, and when the card is played the strength points are added to your total score. The cards are classified as close combat, ranged combat, and siege combat. I used the basic idea of Gwent to develop Football Gwent, where each card is associated with a professional football player in the real world.

### Starting out:
All players start with 500 Million euros and can then build their squad as they see fit. Winning a game grants you 20 million and losing a game subtracts 10 million from you balance. A player can only start a game once they have eleven cards. Each card has six stats - Shooting, Pace, Dribbling, Passing, Defending, and Physical. Each stat corresponds to the real life ability of the player associated with that card.

### Playing the Game:
The game is played on a virtual football pitch which is divided into six strips - 3 for you, and 3 for your opponent. The strip closest to the center is for Attackers, the one furthest from the center for the Defenders, and the one between them for Midfielders (Much like close, ranged, and siege combat cards in Gwent!). For each strip you must first choose one of two stats associated with it. Below are the stats associated with each strip.

# |=================================|
# | 'Strip'  | 'Stat 1'  | 'Stat 2' |
# |----------|-----------|----------|
# | Attack   | Pace      | Shooting |
# | Midfield | Dribbling | Passing  |
# | Defence  | Defending | Physical |
# |=================================|

Once you choose the stats, they are locked in for the rest of the game, and you can then begin to play. You are free to play a card in whichever strip you choose, and the value of stat associated with that strip is added to your total score. For example, if I choose Dribbling as my midfield stat and play Lionel Messi, who has 94 dribbling, in the midfield, 94 will be added to your total score.

## Source Files:
Game.java: Class for the game entity stored in the database. Each Game contains a gameId, userId (id of the player), status(active or completed), userScore, cpuScore, and the chosen stats for each strip.

GamePlayer.java: Class for GamePlayer entity, i.e played cards. Contains the strip the card is played in and a userFlag to keep track of whether the card belongs to the player or cpu.

GamePlayerId.java: Id class for GamePlayer

Player.java: Class for the Player entity, i.e card. Contains all stat values and player names.

User.java: Class for the User entity. Contains userId, username, password, and bank balance.

UserPlayer.java: Class for UserPlayer entity, i.e owned cards. Contains userId and playerId (card id).

UserPlayerId.java: Id class for UserPlayer.

GamePlayerRepository.java: Repo class for GamePlayer.

GameRepository: Repo class for Game.

UserPlayerRepository: Repo class for UserPlayer.

UserRepository: Repo class for User.

login.html: login page template.

register.html: register page template.

home.html: home page template.

search.html: search for cards to buy. template for search page.

playerpage.html: template for player info page, where stats, name, and price are displayed.

game.html: template page for the actual game.

win.html: template for page displayed after winning a game.

draw.html: template for page displayed after drawing a game.

loss.html: template for page displayed after losing a game.

TrumpsController.java: Contains controller methods for all endpoints.

## Technology Stack:
Spring (Spring Data JPA): I used Spring, specifically Spring Data JPA, to easily interact with my database (Query generation and handling relationships between data).

Thymeleaf: I used Thymeleaf at the front end to incorporate java statements into the html templates.

MySQL: I used MySQL to store all the data about users, games, and cards.

Bootstrap: I used Bootstrap 5 for the front end as it provides a fairly easy to use toolset to quickly provide high-quality, professional looking design.
