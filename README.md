# Multitasking Bot

### features:

- running JavaScript Code
- `[in progress]` mode selection via iterative menu
- `[in progress]` translation from one language to another
- `[in progress]` tracking location for sharing with someone


### config:

- JDK 11
- Language Level 8
- `org.telegram:telegrambots:4.4.0.1`
- `org.apache.maven.plugins:maven-shade-plugin:3.4.1`
- `org.apache.maven.plugins:maven-clean-plugin:3.2.0`

### If you want to run your own bot

- register your bot with https://t.me/BotFather
- run `git clone https://github.com/petuch03/simple-java-bot`
- run `cd simple-java-bot/src/main/resources`
- create `.env` file at `simple-java-bot/src/main/resources` with these variables
  - `BOT_USERNAME`
  - `BOT_TOKEN` 
- run `mvn package`
- run `docker build -t simple-java-bot .`
- run `docker run -p 8080:8080 simple-java-bot`
- enjoy your own bot!
- 