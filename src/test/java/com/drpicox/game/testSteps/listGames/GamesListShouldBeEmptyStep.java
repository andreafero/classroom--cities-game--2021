package com.drpicox.game.testSteps.listGames;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class GamesListShouldBeEmptyStep extends AbstractPostLineStep {

    private final ListGamesTestView listGamesTestView;

    public GamesListShouldBeEmptyStep(ListGamesTestView listGamesTestView) {
        this.listGamesTestView = listGamesTestView;
    }

    @Override
    protected String getRegex() {
        return "Games list should be empty";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var games = listGamesTestView.getGames();
        assertThat(games).isEmpty();
    }
}
