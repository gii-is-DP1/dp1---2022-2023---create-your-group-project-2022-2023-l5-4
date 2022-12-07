<style>

*{
    margin:0;
    padding: 0;
}
    .game-table{
        margin: 5px auto 5px auto;
        display: grid;
        height: 100%;
        aspect-ratio: 1;
        grid-template-columns: minmax(0,3fr) minmax(0, 4fr) minmax(0,3fr);
        grid-template-rows: repeat(2, minmax(0,2fr)) minmax(0, 6fr) repeat(2, minmax(0,3fr));
    }

    .player-wrapper{
        display: flex;
        justify-content: space-between;
        align-items: center;
        flex-direction: column;
            padding: 10px;
            margin: 10px;
    }

    #player-wrapper-top{
        grid-row: 1 / 3;
        grid-column: 2;
    } 
    #player-wrapper-left{
        grid-row: 2 / 5;
        grid-column: 1;
    }
    #player-wrapper-right{
        grid-row: 2 / 5;
        grid-column: 3;
    }

    #player-wrapper-bottom{
        grid-row: 4 / 6;
        grid-column: 2;
    }
    .player, .central-deck{
        max-width: 100%;
        max-height: 100%;
        aspect-ratio: 1;
        border: 5px solid lightgray;
        border-radius: 20px;
    }

    #main-player{
        border-color: gold;
    }

    #central-deck-wrapper{
        grid-row: 3 / 4;
        grid-column: 2;

    }

    .central-deck{
        border: none;
    }

    img{
        border-radius: 100%;
        width: 100%;
        height: 100%;
    }
    .timer{
        color: gray;
        position: fixed;
        right: 50px;
        top:10px;
       font-size: 50px;

    }

    .scoreboard{
        position: fixed;
        display: flex;
        flex: 1;
        margin:20px;
        min-width: 250px;
        height: 100%;
        max-height: 400px;
        border: 5px solid lightgray;
        border-radius: 20px;
    }
    .game{
        display: flex;
        flex: 1;
        height: 100%;
        flex-direction: row;
        font-family: cartoon-toy;
    }
</style>

<div class="game">
<span class="timer">15:32</span>
<div class="scoreboard">
    <p>Scoreboard:</p>
</div>
<div class="game-table">
    <div id="player-wrapper-top" class="player-wrapper top">
        <div class="player">
            <img src="/resources/images/sample-card.png"/>
        </div>
    </div>
    <div id="player-wrapper-left" class="player-wrapper">
        <div class="player">
            <img src="/resources/images/sample-card.png"/>
        </div>
        <div class="player">
            <img src="/resources/images/sample-card.png"/>
        </div>
    </div>
    <div id="player-wrapper-right" class="player-wrapper">
        <div class="player">
            <img src="/resources/images/sample-card.png"/>
        </div>
        <div class="player">
            <img src="/resources/images/sample-card.png"/>
        </div>
    </div>
    <div class ="player-wrapper" id="central-deck-wrapper">
        <div class="central-deck">
            <img src="/resources/images/sample-card.png"/>
        </div>
    </div>
    <div id="player-wrapper-bottom" class="player-wrapper">
        <div id="main-player" class="player">
            <img src="/resources/images/sample-card.png"/>
        </div>
        
    </div>
</div>
</div>