# nfa-bot

A discord bot for the THPS community in NFA (No Fun Allowed)

### To run the bot:

```
./gradlew clean run --args '<discordBotToken>'
```

* You must replace `<discordBotToken>` with a real discord token.

### To run the tests:

```
./gradlew clean test
```

### To build an executable JAR file:

```
./gradlew clean build
```

```
java -jar discord/build/libs/NFA_Bot_Discord.jar
```

### Current features:

#### Ping pong

If you say "Ping!", the bot will say "Pong!". This is purely an experimental feature to get some of the architecture set up.

#### Tetris

If you say "tetris", the bot will say "Boom, tetris for <username>!". This is just a meme that will be switched off when the real bot is deployed.

#### THUG Pro Plus

If you say "!thugproplus", the bot will give you a download link for the latest version of the mod.
