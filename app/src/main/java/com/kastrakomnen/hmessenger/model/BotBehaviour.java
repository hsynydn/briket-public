package com.kastrakomnen.hmessenger.model;

public class BotBehaviour {
    /* Number of milliseconds on which new command will be generated */
    public int commandGenerationPeriod;

    /* Number of milliseconds to delay bot start-up */
    public int botStartDelay;

    public BotBehaviour(int commandGenerationPeriod, int botStartDelay){
        this.commandGenerationPeriod = commandGenerationPeriod;
        this.botStartDelay = botStartDelay;
    }
}
